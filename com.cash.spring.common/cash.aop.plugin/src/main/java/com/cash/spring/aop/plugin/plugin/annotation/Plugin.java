package com.cash.spring.aop.plugin.plugin.annotation;

import com.cash.spring.aop.plugin.plugin.Execution;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *插件注解
 *author cash
 *create 2017/10/9-20:10
**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Plugin {

    /**
     * 被插件化类名称
     * @return
     */
    Class target();

    /**
     * 被插件化 方法名
     * 为空 则与@plugin注解方法名相同
     * @return
     */
    String method() default "";

    /**
     * 方法参数,存在方法重载,需要根据参数区分
     * @return
     */
    Class[] args() default {};

    /**
     * before类型时,异常是否继续执行
     * 非before时,直接抛出异常
     * @return
     */
    boolean proceedingOnError() default  false;


    /**
     * 适用的companyId
     * 为空,适用于所有companyId
     * @return
     */
    long[] companyId() default {};


    /**
     * 执行方法
     * @return
     */
    Execution execution() default Execution.REPLACE;
}
