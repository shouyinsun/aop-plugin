package cash.spring.common.combineAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author xuchao
 * 2017年4月23日 上午12:17:07
 * 
 * 可以注解到别的注解上的注解--->元注解
 * 被注解的注解 --->组合注解
 * 
 * @Configuration 和  @ComponentScan 注解,组合成 注解@cashConfiguration
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

@Configuration
@ComponentScan
public @interface cashConfiguration {
	
	String[] value() default {};

}
