package cash.spring.common.initAndDestory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("cash.spring.common.initAndDestory")
public class DoConfig {
	@Bean(initMethod="init" ,destroyMethod="destory")
	BeanWayService beanWayService(){
		return new BeanWayService();
	}
	
	@Bean
	PrePostWayService prePostWayService(){
		return new PrePostWayService();
	}
}
