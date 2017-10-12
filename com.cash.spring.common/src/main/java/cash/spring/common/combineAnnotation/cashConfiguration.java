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
 * 2017��4��23�� ����12:17:07
 * 
 * ����ע�⵽���ע���ϵ�ע��--->Ԫע��
 * ��ע���ע�� --->���ע��
 * 
 * @Configuration ��  @ComponentScan ע��,��ϳ� ע��@cashConfiguration
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

@Configuration
@ComponentScan
public @interface cashConfiguration {
	
	String[] value() default {};

}
