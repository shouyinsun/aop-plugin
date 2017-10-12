package cash.spring.common.applicationEvent;

import org.springframework.context.ApplicationEvent;
/**
 * 
 * @author cash
 * @date 2017��4��16�� ����8:18:24
 * @decription �Զ����¼�
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
