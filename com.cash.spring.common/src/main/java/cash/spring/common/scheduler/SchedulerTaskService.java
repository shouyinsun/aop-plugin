package cash.spring.common.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xuchao
 * 2017��4��22�� ����11:25:59
 * 
 * @Scheduled ע�����������Ƕ�ʱ����  ����cron fixRate fixDelay
 */

@Service
public class SchedulerTaskService {
	
	private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate=5000)
	public void fixRate(){
		System.out.println("ÿ������ִ��һ�Σ� "+dateFormat.format(new Date()));
	}
	
	@Scheduled(fixedDelay=3000)
	public void fixDelay(){
		System.out.println("�ӳ�����ִ�У� "+dateFormat.format(new Date()));
	}
	
	@Scheduled(cron="0/10 * * * * *")
	public void cron(){
		System.out.println("cron,���ʽ,ÿʮ��ִ��һ�Σ� "+dateFormat.format(new Date()));
	}
	
	

}
