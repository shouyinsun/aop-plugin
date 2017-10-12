package cash.spring.common.value;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(doConfig.class);
		ConfigService cs=ac.getBean(ConfigService.class);
		cs.output();		
	}

}
