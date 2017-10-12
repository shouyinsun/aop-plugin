package cash.spring.common.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
/**
 * 
 * @author cash
 * @date 2017年4月16日 下午8:01:48
 * @decription Profile 实现在不同环境下,实例不同的bean   
 * 设置Enviroment 的 activeProfiles
 */

@Configuration
public class ProfileConfig {
	
	@Bean
	@Profile("dev")
	public Service doDev(){
		return new Service("from dev profile");
	}
	
	@Bean
	@Profile("pro")
	public Service doPro(){
		return new Service("from pro profile");
	}
}
