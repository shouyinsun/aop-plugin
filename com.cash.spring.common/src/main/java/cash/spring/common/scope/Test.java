package cash.spring.common.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 
 * @author cash
 * @date 2017年4月16日 下午6:35:09
 * @decription bean 的scope 默认为singleton,全容器只有一个实例
 *             bean 的scope 为 prototype,每次调用都会新建一个实例
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
