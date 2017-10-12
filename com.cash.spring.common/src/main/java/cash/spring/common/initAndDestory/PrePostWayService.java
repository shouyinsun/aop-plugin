package cash.spring.common.initAndDestory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����7:39:12
 * @decription ʹ��ע�� @PostConstruct �� @PreDestroy
 * 
 * ����  jsr250-api
 */


public class PrePostWayService {
	
	@PostConstruct
	public void init(){
		System.out.println("init PrePostWayService");
	}
	


	public PrePostWayService() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("��ʼ��  PrePostWayService");
	}
	
	
	@PreDestroy
	public void destory(){
		System.out.println("destory PrePostWayService");
	}
	
}
