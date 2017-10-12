package com.cash.spring.aop.plugin.plugin;

import com.cash.spring.aop.plugin.plugin.annotation.Plugable;
import com.cash.spring.aop.plugin.plugin.meta.BeanPluginMeta;
import com.cash.spring.aop.plugin.plugin.meta.HttpPluginMeta;
import com.cash.spring.aop.plugin.plugin.meta.PluginMeta;
import com.cash.spring.aop.plugin.utils.ReflectionUtils;
import com.cash.spring.aop.plugin.utils.SystemContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 插件代理类
 * author cash
 * create 2017-10-10-19:43
 **/

public class PluginProxy implements ApplicationContextAware {

    //日志记录
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    //插件信息提供器
    private PluginProvider provider;
    //spring容器
    private ApplicationContext applicationContext;

    public Object execute(ProceedingJoinPoint jp) throws Throwable {
		/* 代理目标方法的参数 */
        Object target = jp.getTarget();
        //代理对象的类名
        Class<?> targetClass = target.getClass();
        Signature signature = jp.getSignature();
        Object[] args = jp.getArgs();
        Class[] parameterTypes;
        if (signature instanceof MethodSignature) {
            parameterTypes = ((MethodSignature) signature).getParameterTypes();
        } else {
            parameterTypes = getParameterTypes(args);
        }

        String methodName = signature.getName();
        String methodSignature = ReflectionUtils.buildMethodSignature(targetClass, methodName, parameterTypes);
        if (logger.isDebugEnabled()) {
            logger.debug("executing " + Plugable.class.getSimpleName() + " method:" + methodSignature);
        }
        //优先执行replace的plugin
        PluginMeta replace = provider.getPluginMeta(SystemContext.getCompanyId(), methodSignature, Execution.REPLACE);
        if (replace != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("find " + Execution.REPLACE.name() + " plugin:" + replace);
            }
            return replace(jp, replace);
        }
        //如果有before的plugin
        PluginMeta before = provider.getPluginMeta(SystemContext.getCompanyId(), methodSignature, Execution.BEFORE);
        if (before != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("find " + Execution.BEFORE.name() + " plugin:" + before);
            }
            before(jp, before);
        }
        Object result = jp.proceed(args);
        PluginMeta after = provider.getPluginMeta(SystemContext.getCompanyId(), methodSignature, Execution.AFTER);
        if (after != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("find " + Execution.AFTER.name() + " plugin:" + after);
            }
            //把前面返回的结果传递给after
            result=after(result, jp, after);
        }
        return result;
    }

    private Object before(ProceedingJoinPoint jp, PluginMeta plugin) throws Throwable {

        Object[] args = jp.getArgs();
        if (plugin instanceof HttpPluginMeta) {
            // TODO 待完善
        }
        //bean 插件实现
        else if (plugin instanceof BeanPluginMeta) {
            BeanPluginMeta beanPluginMeta = (BeanPluginMeta) plugin;
            Object proxy = beanPluginMeta.getPluginObject();
            if (proxy == null) {
                proxy = createProxy(beanPluginMeta);
                beanPluginMeta.setPluginObject(proxy);
            }
            Method method = beanPluginMeta.getMethod();
            Object ret;
            try {
                ret = method.invoke(proxy, args);
                return ret;
            } catch (InvocationTargetException t) {
                if (plugin.isProceedingOnError()) {
                    logger.error("error executing plugin:" + plugin + ",the cause is:", t.getTargetException());
                    return null;
                } else {
                    throw t.getTargetException();
                }
            }
        }
        throw new RuntimeException("unknown plugin protocol :" + plugin);
    }


    private Object replace(ProceedingJoinPoint jp, PluginMeta plugin) throws Throwable {
        Object[] args = jp.getArgs();
        Class[] argTypes;
        Signature signature = jp.getSignature();
        if (signature instanceof MethodSignature) {
            argTypes = ((MethodSignature) signature).getParameterTypes();
        } else {
            argTypes = getParameterTypes(args);
        }
        if (plugin instanceof HttpPluginMeta) {
            // TODO  待完善
        }
        //内代码的插件实现
        else if (plugin instanceof BeanPluginMeta) {
            BeanPluginMeta beanPluginMeta = (BeanPluginMeta) plugin;
            Object proxy = beanPluginMeta.getPluginObject();
            if (proxy == null) {
                proxy = createProxy(beanPluginMeta);
                beanPluginMeta.setPluginObject(proxy);
            }
            Method method = beanPluginMeta.getMethod();
            Object ret;
            try {
                ret = method.invoke(proxy, args);
                return ret;
            } catch (InvocationTargetException t) {
                if (plugin.isProceedingOnError()) {
                    logger.error("error executing plugin:" + plugin + ",the cause is:", t.getTargetException());
                    return null;
                } else {
                    throw t.getTargetException();
                }
            }
        }
        throw new RuntimeException("unknown plugin protocol :" + plugin);
    }

    private Object createProxy(BeanPluginMeta beanPluginMeta) {
        try {
            beanPluginMeta.getLock().lock();
            if (beanPluginMeta.getPluginObject() != null) {
                return beanPluginMeta.getPluginObject();
            }
            Object proxy;
            if (beanPluginMeta.isSpringBean()) {
                proxy = getSpringBean(beanPluginMeta.getBeanName());
            } else {
                proxy = Class.forName(beanPluginMeta.getBeanClass()).newInstance();
            }
            if (proxy == null) {
                throw new RuntimeException("beanPluginMeta plugin:" + beanPluginMeta + "'s object is null");
            }
            return proxy;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("error create plugin:" + beanPluginMeta);
        } finally {
            beanPluginMeta.getLock().unlock();
        }
    }

    private Object after(Object result, ProceedingJoinPoint jp, PluginMeta plugin) throws Throwable {
        Object[] args = jp.getArgs();
        Class[] argTypes;
        Signature signature = jp.getSignature();
        boolean isVoid = false;
        if (signature instanceof MethodSignature) {
            argTypes = ((MethodSignature) signature).getParameterTypes();
            Class returnType = ((MethodSignature) signature).getReturnType();
            if (returnType.getSimpleName().equals("void")) {
                isVoid = true;
            }
        } else {
            argTypes = getParameterTypes(args);
        }
        if (plugin instanceof HttpPluginMeta) {
            // TODO: 2017/3/17 尚未实现
        }
        //代码的插件实现
        else if (plugin instanceof BeanPluginMeta) {
            BeanPluginMeta beanPluginMeta = (BeanPluginMeta) plugin;
            Object proxy = beanPluginMeta.getPluginObject();
            if (proxy == null) {
                proxy = createProxy(beanPluginMeta);
                beanPluginMeta.setPluginObject(proxy);
            }
            if (proxy == null) {
                throw new RuntimeException("beanPluginMeta plugin:" + beanPluginMeta + "'s object is null");
            }
            Method method = beanPluginMeta.getMethod();
            try {
                if (isVoid) {
                    return method.invoke(proxy, args);
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                //判断实际参数类型和方法的参数类型是否一致
                boolean isSameType = isSameParamterType(argTypes, parameterTypes);
                //如果实际参数和插件定义方法的参数类型一致,将方法输入参数传给插件方法执行
                if (isSameType) {
                    result= method.invoke(proxy, args);
                    return result;
                }
                //如果不一致,将原始方法的执行结果,作为插件方法的第一个参数,传给插件方法,将其他的输入参数向后偏移1个次序
                else {
                    Object[] newArgs = new Object[args.length + 1];
                    newArgs[0] = result;
                    if (args.length > 0) {
                        System.arraycopy(args, 0, newArgs, 1, args.length);
                    }
                    result= method.invoke(proxy, newArgs);
                    return result;
                }
            } catch (InvocationTargetException iv) {
                throw iv.getTargetException();
            }
        }
        throw new RuntimeException("unknown plugin protocol :" + plugin);
    }

    private boolean isSameParamterType(Class[] argTypes, Class[] parameterTypes) {
        boolean isSameType = true;
        if (argTypes.length != parameterTypes.length) {
            isSameType = false;
        } else if (argTypes.length == 0) {
            isSameType = true;
        } else {
            for (int i = 0; i < argTypes.length; i++) {
                if (!argTypes[i].getName().equals(parameterTypes[i].getName())) {
                    isSameType = false;
                    break;
                }
            }
        }
        return isSameType;
    }


    private Class[] getParameterTypes(Object[] args) {
        if (args == null) {
            return new Class<?>[0];
        }
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    private <T> T getSpringBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    private <T> T getSpringBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public void setProvider(PluginProvider provider) {
        this.provider = provider;
    }
}
