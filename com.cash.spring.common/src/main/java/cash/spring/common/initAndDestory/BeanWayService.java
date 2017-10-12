package cash.spring.common.initAndDestory;

import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午7:34:39
 * @decription bean 的 init 和 destory ,使用@Bean 的 initMethod 和 destoryMethod 
 * 相当于 xml中配置的 init-method 和 destory-method
 */

public class BeanWayService {
	
	public void init(){
		System.out.println("init BeanWayService");
	}

	public BeanWayService() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("初始化  BeanWayService");
	}
	
	public void destory(){
		System.out.println("destory BeanWayService");
	}
	
}
