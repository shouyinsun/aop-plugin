package cash.spring.common.aware;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xuchao
 * 2017年4月22日 下午8:26:09
 * 使用spring aware的service
 * 
 * 
 * spring 提供的aware
 * BeanNameAware 获得容器中bean的名称
 * BeanFactoryAware 获得当前的beanFactory
 * ApplicationContextAware 当前的applicate context
 * MessageSourceAware  获得message source
 * ApplicationEventPublisherAwaer 应用事件发布器
 * ResourceLoaderAware 资源加载器,可获得外部资源文件
 * 
 * ApplicationContext集成了各接口,所以bean一旦继承ApplicationContextAware就可以获得spring容器的所有服务
 * 
 */
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {
	private String beanname;
	private ResourceLoader loader;
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		this.loader=resourceLoader;
	}

	public void setBeanName(String beanname) {
		// TODO Auto-generated method stub
		this.beanname=beanname;

	}
	
	public void output(){
		System.out.println("beanname is:"+beanname);
		Resource resource=loader.getResource("classpath:cash/spring/common/aware/a.text");
		try {
			System.out.println("Resource加载的内容是："+IOUtils.toString(resource.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
