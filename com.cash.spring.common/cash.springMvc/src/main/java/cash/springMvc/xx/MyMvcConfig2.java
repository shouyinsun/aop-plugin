package cash.springMvc.xx;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cash.springMvc.xx.httpMessageConverter.MyMessageConverter;

/**
 *
 * @author xuchao 2017年4月23日 下午4:24:00
 *
 *继承 WebMvcConfigurerAdapter,重写其方法,能够对SpringMvc进行配置
 */
@EnableWebMvc
@Configuration
@ComponentScan("cash.springMvc.xx")
@EnableScheduling
public class MyMvcConfig2 extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;

	}

	@Bean
	public TimeConsumeInterceptor TimeConsumeInterceptor() {
		return new TimeConsumeInterceptor();
	}

	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(1000000);
		return commonsMultipartResolver;
	}

	/**
	 * 重载方法,对springMvc进行配置
	 *
	 * 静态资源的映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry resourcehandlerregistry) {
		// resourcehandlerregistry 是增加文件放置的路径 addResourceHandler 是对外暴露的访问路径
		resourcehandlerregistry.addResourceHandler("/fxxk/img/**").addResourceLocations("classpath:/resource/img/");
		resourcehandlerregistry.addResourceHandler("/fxxk/js/**").addResourceLocations("classpath:/resource/js/");

	}

	/**
	 * 重载 addInterceptors
	 *
	 * 注册自定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry interceptorregistry) {
		interceptorregistry.addInterceptor(TimeConsumeInterceptor());
	}

	/**
	 * 重写 addViewControllers 简单的页面跳转
	 */
	public void addViewControllers(ViewControllerRegistry viewcontrollerregistry) {
		viewcontrollerregistry.addViewController("/index2").setViewName("/index2");
		viewcontrollerregistry.addViewController("/toUpload").setViewName("/upload");
		viewcontrollerregistry.addViewController("/toConverter").setViewName("/converter");
		viewcontrollerregistry.addViewController("/sse").setViewName("/sse");
		viewcontrollerregistry.addViewController("/async").setViewName("/async");

	}

	/**
	 * Sping Mvc中如果路径带. 默认是会忽略的  xx.yy 只会访问 xx
	 * 重写  configurePathMatch 设置新的路径匹配规则
	 *
	 *@FIXME 不起作用  index.html没有忽略后缀
	 */
	public void configurePathMatch(PathMatchConfigurer pathmatchconfigurer){
		pathmatchconfigurer.setUseSuffixPatternMatch(false);
	}

	/**
	 * 配置自定义的 HttpMessageConverter 有两个方法
	 * configureMessageConverters:会覆盖掉Spring MVC 默认注册的HttpMessageConverter
	 * extendMessageConverters:仅添加一个自定义的HttpMessageConverter
	 */
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
		converters.add(converter());
	}

	@Bean
	public MyMessageConverter converter(){
		return new MyMessageConverter();
	}

}
