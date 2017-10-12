package com.cash.spring.aop.plugin.plugin.provider;

import com.cash.spring.aop.plugin.plugin.Execution;
import com.cash.spring.aop.plugin.plugin.PluginProvider;
import com.cash.spring.aop.plugin.plugin.annotation.Plugin;
import com.cash.spring.aop.plugin.plugin.meta.BeanPluginMeta;
import com.cash.spring.aop.plugin.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 注解化插件
 * author cash
 * create 2017-10-10-15:54
 **/

public class AnnotationPluginProvider extends  ConfigurablePluginProvider implements ApplicationContextAware{
    private Logger logger = LoggerFactory.getLogger(AnnotationPluginProvider.class);

    private static final String RESOURCE_PATTERN="/**/*.class";

    private ResourcePatternResolver resourcePatternResolver=new  PathMatchingResourcePatternResolver();

    private String[] packagesToScan;

    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        for(String pkg:this.packagesToScan){
            String pattern=ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+
                    ClassUtils.convertClassNameToResourcePath(pkg)+RESOURCE_PATTERN;
            Resource[] resources=this.resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory=new CachingMetadataReaderFactory(this.resourcePatternResolver);
            for(Resource resource:resources){
                if(resource.isReadable()){
                    MetadataReader reader=readerFactory.getMetadataReader(resource);
                    String className=reader.getClassMetadata().getClassName();
                    AnnotationMetadata annotationMetadata=reader.getAnnotationMetadata();
                    if(reader.getClassMetadata().isInterface()){
                        continue;
                    }
                    if(!annotationMetadata.hasAnnotation(Plugin.class.getName())&& !annotationMetadata.hasAnnotatedMethods(Plugin.class.getName())){
                        continue;
                    }
                    Class<?> clazz=Class.forName(className);
                    Map<String,?> beansOfType=applicationContext.getBeansOfType(clazz);

                    if ( (beansOfType == null || beansOfType.size() ==0 ) && clazz.getInterfaces().length > 0 ){
                        beansOfType = applicationContext.getBeansOfType(clazz.getInterfaces()[0]);
                    }

                    boolean isSpringBean=true;
                    String beanName=null;
                    if(beansOfType == null || 0==beansOfType.size()){
                        isSpringBean=false;
                    }else{
                        for (String s : beansOfType.keySet()) {
                            Object proxy = beansOfType.get(s);
                            if (AopUtils.getTargetClass(proxy).getName().equals(className) ){
                                beanName = s;
                            }
                        }
                        if (StringUtils.isEmpty(beanName)){
                            //s没有找到beanName,随机给一个
                            for (String s : beansOfType.keySet()) {
                                beanName = s;
                            }
                        }

                    }
                    Method[] methods=clazz.getDeclaredMethods();
                    if(null==methods || methods.length==0){
                        continue;
                    }
                    Plugin classAnnotation=clazz.getAnnotation(Plugin.class);
                    //class上有annotation,class的所有方法加入插件配置
                    if(null!=classAnnotation){
                        for(Method method:methods){
                            createPlugin(className,isSpringBean,beanName,classAnnotation,method);
                        }

                    }else{
                        for(Method method:methods){
                            //有annotation的方法加入插件配置
                            Plugin methodAnnotation=method.getAnnotation(Plugin.class);
                            if(null!=methodAnnotation){
                                createPlugin(className,isSpringBean,beanName,methodAnnotation,method);
                            }
                        }
                    }
                }
            }
        }
    }

    private void createPlugin(String className, boolean isSpringBean, String beanName, Plugin plugin, Method method) {
        Class target;
        long[] companyIds;
        Execution execution;
        String methodName;
        Plugin methodAnnotation=method.getAnnotation(Plugin.class);
        boolean proceedingOnError;
        if(null!=methodAnnotation){
            target=methodAnnotation.target();
            companyIds=methodAnnotation.companyId();
            execution=methodAnnotation.execution();
            methodName=methodAnnotation.method();
            proceedingOnError=methodAnnotation.proceedingOnError();

        }else if(null!=plugin){
            target=plugin.target();
            companyIds=plugin.companyId();
            execution=plugin.execution();
            methodName=plugin.method();
            proceedingOnError=plugin.proceedingOnError();
        }else{
            logger.error("method:" + method.getName() + " has no annotation,will not add to plugin config");
            return;
        }
        //如果annotation中没有设置method属性,使用当前method的name
        if(StringUtils.isEmpty(methodName)){
            methodName=method.getName();
        }
        String signature;
        //REPLACE 判断参数是否一致
        if(execution.equals(Execution.REPLACE)){
            Method targetMethod= ReflectionUtils.getDeclaredMethod(target, methodName, method.getParameterTypes());
            if (targetMethod == null) {
                logger.error("target:" + target.getName() + "has not method:" + methodName + ",will not plugin");
                return;
            }
            signature=ReflectionUtils.buildMethodSignature(target, methodName, targetMethod.getParameterTypes());
        }else{
            Method targetMethod;
            if(null!=methodAnnotation && methodAnnotation.args().length > 0){
                targetMethod = ReflectionUtils.getDeclaredMethod(target, methodName, methodAnnotation.args());
            }else{
                targetMethod = ReflectionUtils.getDeclaredMethod(target, methodName);
            }
            if (targetMethod == null) {
                logger.error("target:" + target.getName() + "has not method:" + methodName + ",will not plugin");
                return;
            }
            signature = ReflectionUtils.buildMethodSignature(target, methodName, targetMethod.getParameterTypes());
        }
        if (companyIds.length > 0) {
            for (long companyId : companyIds) {
                BeanPluginMeta pluginMeta = new BeanPluginMeta();
                pluginMeta.setCompanyId(companyId);
                pluginMeta.setSignature(signature);
                pluginMeta.setExecution(execution);
                pluginMeta.setSpringBean(isSpringBean);
                pluginMeta.setBeanName(beanName);
                pluginMeta.setBeanClass(className);
                pluginMeta.setMethod(method);
                pluginMeta.setProceedingOnError(proceedingOnError);
                addPlugin(pluginMeta);
            }
        } else {
            BeanPluginMeta pluginMeta = new BeanPluginMeta();
            pluginMeta.setSignature(signature);
            pluginMeta.setExecution(execution);
            pluginMeta.setSpringBean(isSpringBean);
            pluginMeta.setBeanName(beanName);
            pluginMeta.setBeanClass(className);
            pluginMeta.setCompanyId(PluginProvider.ALL_COMPANY);
            pluginMeta.setMethod(method);
            pluginMeta.setProceedingOnError(proceedingOnError);
            addPlugin(pluginMeta);
        }

    }

    public String[] getPackagesToScan() {
        return packagesToScan;
    }

    public void setPackagesToScan(String[] packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        super.setApplicationContext(applicationContext);
        this.applicationContext=applicationContext;
    }
}
