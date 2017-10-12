package cash.spring.common.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����5:55:35
 * @decription ������  @EnableAspectJAutoProxy ע�⿪��Spring �� AspectJ�����֧��
 */
@Configuration
@ComponentScan("cash.spring.common.aop")
@EnableAspectJAutoProxy
public class DoConfig {
	
}
