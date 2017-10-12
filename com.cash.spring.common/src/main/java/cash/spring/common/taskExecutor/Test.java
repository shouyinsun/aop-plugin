package cash.spring.common.taskExecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		AsyncTaskService asyncTaskService=ac.getBean(AsyncTaskService.class);
		for(int i=0;i<10;i++){//线程池,不是顺序的
			asyncTaskService.doSometing(i);
			asyncTaskService.addOne(i);
		}
		ac.close();

	}

}
