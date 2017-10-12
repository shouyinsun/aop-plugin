package cash.spring.common.initAndDestory;

import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����7:34:39
 * @decription bean �� init �� destory ,ʹ��@Bean �� initMethod �� destoryMethod 
 * �൱�� xml�����õ� init-method �� destory-method
 */

public class BeanWayService {
	
	public void init(){
		System.out.println("init BeanWayService");
	}

	public BeanWayService() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("��ʼ��  BeanWayService");
	}
	
	public void destory(){
		System.out.println("destory BeanWayService");
	}
	
}
