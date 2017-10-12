package cash.spring.common.scheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
		
		//ac.close();

	}

}
