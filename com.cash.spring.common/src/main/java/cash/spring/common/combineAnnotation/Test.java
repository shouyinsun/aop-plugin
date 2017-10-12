package cash.spring.common.combineAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(DoConfig.class);
		Service service=ac.getBean(Service.class);
		service.output();
		ac.close();

	}

}
