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
 * 2017��4��22�� ����10:12:52
 * 
 * spring ͨ�� taskExecutorʵ�ֶ��̲߳���
 * ʹ��  ThreadPoolTaskExecutorʵ�ֻ����̳߳ص� taskExecutor
 * 
 * @EnableAsync �����첽����֧��
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
