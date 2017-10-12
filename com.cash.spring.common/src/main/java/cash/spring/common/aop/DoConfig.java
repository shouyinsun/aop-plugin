package cash.spring.common.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午5:55:35
 * @decription 配置类  @EnableAspectJAutoProxy 注解开启Spring 对 AspectJ代理的支持
 */
@Configuration
@ComponentScan("cash.spring.common.aop")
@EnableAspectJAutoProxy
public class DoConfig {
	
}
