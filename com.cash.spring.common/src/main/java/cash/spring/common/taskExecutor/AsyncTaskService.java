package cash.spring.common.taskExecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xuchao
 * 2017��4��22�� ����11:08:55
 * 
 * ����ִ����
 * 
 * @Async ע�������������Ǹ��첽����,���ע��������,�������������з��������첽��
 * ���������Զ�ע�뵽ʹ�� ThreadPoolTaskExecutor �� taskExecutor
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
