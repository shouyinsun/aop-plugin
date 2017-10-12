package cash.spring.common.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 
 * @author cash
 * @date 2017��4��16�� ����6:35:09
 * @decription bean ��scope Ĭ��Ϊsingleton,ȫ����ֻ��һ��ʵ��
 *             bean ��scope Ϊ prototype,ÿ�ε��ö����½�һ��ʵ��
 */

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(DoConfig.class);
		SingletonService s1=ac.getBean(SingletonService.class);
		SingletonService s2=ac.getBean(SingletonService.class);
		
		PrototypeService p1=ac.getBean(PrototypeService.class);
		PrototypeService p2=ac.getBean(PrototypeService.class);
		
		System.out.println("scope is singleton,equal:"+(s1==(s2)));
		
		System.out.println("scope is prototype,equal:"+(p1==(p2)));
		
		
		

	}

}
