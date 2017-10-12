package com.cash.spring.aop.plugin.plugin.meta;

import com.cash.spring.aop.plugin.plugin.PluginProtocol;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * bean协议
 * author cash
 * create 2017-10-09-20:36
 **/

public class BeanPluginMeta extends PluginMeta {

    //对象是否是spring的bean 默认是,是的话直接从容器获取,不是就反射得到
    private boolean isSpringBean = true;
    //另外一个实现的ioc容器的 beanId
    private String beanName;
    //另一个对象类型，如果不是spring的bean将反射创建此类型对应的bean,然后调用方法
    private String beanClass;
    //插件对象
    private Object pluginObject;
    //实现的对象对应的方法
    private Method method;
    //object's lock
    private Lock lock = new ReentrantLock();

    @Override
    public String getProtocol() {
        return PluginProtocol.bean.name();
    }

    public boolean isSpringBean() {
        return isSpringBean;
    }

    public void setSpringBean(boolean springBean) {
        isSpringBean = springBean;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public Object getPluginObject() {
        return pluginObject;
    }

    public void setPluginObject(Object pluginObject) {
        this.pluginObject = pluginObject;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
