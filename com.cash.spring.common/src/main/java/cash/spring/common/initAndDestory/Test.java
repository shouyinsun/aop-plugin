package cash.spring.common.initAndDestory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(DoConfig.class);
		ac.getBean(BeanWayService.class);
		ac.getBean(PrePostWayService.class);
		ac.close();

	}

}
