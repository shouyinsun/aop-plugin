package cash.spring.common.taskExecutor;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * 
 * @author xuchao
 * 2017年4月22日 下午10:12:52
 * 
 * spring 通过 taskExecutor实现多线程并发
 * 使用  ThreadPoolTaskExecutor实现基于线程池的 taskExecutor
 * 
 * @EnableAsync 开启异步任务支持
 */
@Configurable
@ComponentScan("cash.spring.common.taskExecutor")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {

	public Executor getAsyncExecutor() {
		// TODO Auto-generated method stub
		ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		executor.initialize();
		return executor;
	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
