package cash.spring.common.initAndDestory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午7:39:12
 * @decription 使用注解 @PostConstruct 和 @PreDestroy
 * 
 * 依赖  jsr250-api
 */


public class PrePostWayService {
	
	@PostConstruct
	public void init(){
		System.out.println("init PrePostWayService");
	}
	


	public PrePostWayService() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("初始化  PrePostWayService");
	}
	
	
	@PreDestroy
	public void destory(){
		System.out.println("destory PrePostWayService");
	}
	
}
