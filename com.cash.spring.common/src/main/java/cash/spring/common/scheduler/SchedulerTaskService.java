package cash.spring.common.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xuchao
 * 2017年4月22日 下午11:25:59
 * 
 * @Scheduled 注解申明方法是定时任务  包含cron fixRate fixDelay
 */

@Service
public class SchedulerTaskService {
	
	private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate=5000)
	public void fixRate(){
		System.out.println("每隔五秒执行一次： "+dateFormat.format(new Date()));
	}
	
	@Scheduled(fixedDelay=3000)
	public void fixDelay(){
		System.out.println("延迟三秒执行： "+dateFormat.format(new Date()));
	}
	
	@Scheduled(cron="0/10 * * * * *")
	public void cron(){
		System.out.println("cron,表达式,每十秒执行一次： "+dateFormat.format(new Date()));
	}
	
	

}
