package cash.spring.common.applicationEvent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(DoConfig.class);
		Publisher publish=ac.getBean(Publisher.class);
		publish.publish("this is for you,cava!");
		ac.close();

	}

}
