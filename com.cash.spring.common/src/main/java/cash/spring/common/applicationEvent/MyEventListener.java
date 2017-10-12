package cash.spring.common.applicationEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 
 * @author cash
 * @date 2017��4��16�� ����8:18:48
 * @decription �Զ����¼�������
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

	public void onApplicationEvent(MyEvent event) {
		// TODO Auto-generated method stub
		String msg=event.getMsg();
		System.out.println("msg is:"+msg);
		
	}

}
