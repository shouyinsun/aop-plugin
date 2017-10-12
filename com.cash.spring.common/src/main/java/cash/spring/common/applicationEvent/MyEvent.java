package cash.spring.common.applicationEvent;

import org.springframework.context.ApplicationEvent;
/**
 * 
 * @author cash
 * @date 2017年4月16日 下午8:18:24
 * @decription 自定义事件
 */
public class MyEvent extends ApplicationEvent {
	
	private String msg;	

	public MyEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
