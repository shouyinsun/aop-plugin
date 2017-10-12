package cash.springMvc.xx;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午2:16:46
 *
 * WebApplicationInitializer 是 Spring提供的配置Servlet3.0+配置的接口
 * 可以实现替代web.xml的位置
 * 实现此接口会自动被SpringServletContainerInitializer获取到
 */
public class WebInitializer implements WebApplicationInitializer {



	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext ac=new
				AnnotationConfigWebApplicationContext();
		//注册配置类,并与当前的servletContext关联
		//ac.register(MyMvcConfig.class);
		ac.register(MyMvcConfig2.class);
		ac.setServletContext(servletContext);
		//注册SpringMVC的 dispatcherServlet
		Dynamic  servlet=servletContext.addServlet("dispatcher", new DispatcherServlet(ac));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		//开启异步方法支持
		servlet.setAsyncSupported(true);

	}

}
