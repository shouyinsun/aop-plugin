package cash.spring.common.scheduler;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author xuchao
 * 2017年4月22日 下午11:21:49
 * 
 * @EnableScheduling 注解开启对计划任务的支持
 */
@Configurable
@ComponentScan("cash.spring.common.scheduler")
@EnableScheduling
public class TaskSchedulerConfig {
	
}
