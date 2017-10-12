package cash.spring.common.applicationEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 
 * @author cash
 * @date 2017年4月16日 下午8:18:48
 * @decription 自定义事件监听器
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

	public void onApplicationEvent(MyEvent event) {
		// TODO Auto-generated method stub
		String msg=event.getMsg();
		System.out.println("msg is:"+msg);
		
	}

}
