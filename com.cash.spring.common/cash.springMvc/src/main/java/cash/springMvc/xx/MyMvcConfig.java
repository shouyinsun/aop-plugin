package cash.springMvc.xx;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


/**
 *
 * @author xuchao
 * 2017年4月23日 下午2:02:02
 *
 * 配置一个viewResolver
 * @EnableWebMvc 注解会默认开启一些配置
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan("cash.springMvc.xx")
public class MyMvcConfig {

	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver=new
				InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;

	}

}
