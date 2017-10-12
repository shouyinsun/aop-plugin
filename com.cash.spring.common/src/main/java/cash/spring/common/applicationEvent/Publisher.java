package cash.spring.common.applicationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����8:19:49
 * @decription �Զ��� �¼�������
 */
@Component
public class Publisher {
	@Autowired
	ApplicationContext  applicationContext;
	
	
	public void publish(String msg){
		applicationContext.publishEvent(new MyEvent(this, msg));//this
	}
}
