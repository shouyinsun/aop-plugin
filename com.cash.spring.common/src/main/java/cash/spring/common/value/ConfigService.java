package cash.spring.common.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:cash/spring/common/value/config.properties")
public class ConfigService {
	/***
	 * spring el ±Ì¥Ô Ω
	 */
	@Value("I'm fucking hate you")
	private String normal;
	
	@Value("#{systemProperties['os.name']}")
	private String osName;
	
	@Value("#{ T(java.lang.Math).random()*100.0}")
	private double randomNo;
	
	@Value("#{OtherService.another}")
	private String another;
	
	@Value("${username}")
	private String username;
	
	public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public void output(){
		System.out.println(normal);
		System.out.println(osName);
		System.out.println(randomNo);
		System.out.println(another);
		System.out.println(username);
	}
	
}
