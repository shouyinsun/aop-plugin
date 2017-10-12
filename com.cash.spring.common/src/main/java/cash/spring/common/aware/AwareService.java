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
 * 2017��4��22�� ����8:26:09
 * ʹ��spring aware��service
 * 
 * 
 * spring �ṩ��aware
 * BeanNameAware ���������bean������
 * BeanFactoryAware ��õ�ǰ��beanFactory
 * ApplicationContextAware ��ǰ��applicate context
 * MessageSourceAware  ���message source
 * ApplicationEventPublisherAwaer Ӧ���¼�������
 * ResourceLoaderAware ��Դ������,�ɻ���ⲿ��Դ�ļ�
 * 
 * ApplicationContext�����˸��ӿ�,����beanһ���̳�ApplicationContextAware�Ϳ��Ի��spring���������з���
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
			System.out.println("Resource���ص������ǣ�"+IOUtils.toString(resource.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
