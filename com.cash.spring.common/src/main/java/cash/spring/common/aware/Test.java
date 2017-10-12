package cash.spring.common.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(DoConfig.class);
		AwareService awareService=ac.getBean(AwareService.class);
		awareService.output();
		ac.close();
		
	}

}
