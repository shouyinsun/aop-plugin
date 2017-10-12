package cash.spring.common.scheduler;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author xuchao
 * 2017��4��22�� ����11:21:49
 * 
 * @EnableScheduling ע�⿪���Լƻ������֧��
 */
@Configurable
@ComponentScan("cash.spring.common.scheduler")
@EnableScheduling
public class TaskSchedulerConfig {
	
}
