package cash.spring.common.taskExecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xuchao
 * 2017年4月22日 下午11:08:55
 * 
 * 任务执行类
 * 
 * @Async 注解表明这个方法是个异步方法,如果注解在类上,表明这个类的所有方法都是异步的
 * 而方法被自动注入到使用 ThreadPoolTaskExecutor 的 taskExecutor
 */
@Service
public class AsyncTaskService {
	@Async
	public void doSometing(Integer i){
		System.out.println("doSomething: "+i);
	}
	
	@Async
	public void addOne(Integer i){
		System.out.println("addOne: "+(i+1));
	}

}
