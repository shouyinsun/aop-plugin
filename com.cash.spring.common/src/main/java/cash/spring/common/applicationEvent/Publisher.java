package cash.spring.common.applicationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午8:19:49
 * @decription 自定义 事件发布类
 */
@Component
public class Publisher {
	@Autowired
	ApplicationContext  applicationContext;
	
	
	public void publish(String msg){
		applicationContext.publishEvent(new MyEvent(this, msg));//this
	}
}
