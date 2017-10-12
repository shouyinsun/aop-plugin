package cash.spring.common.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
/**
 * 
 * @author cash
 * @date 2017��4��16�� ����8:01:48
 * @decription Profile ʵ���ڲ�ͬ������,ʵ����ͬ��bean   
 * ����Enviroment �� activeProfiles
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
