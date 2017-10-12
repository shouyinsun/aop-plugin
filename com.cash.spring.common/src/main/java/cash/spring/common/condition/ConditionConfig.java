package cash.spring.common.condition;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * 
 * @author xuchao
 * 2017��4��22�� ����11:49:03
 * 
 * @Conditional ע�����bean�Ĵ�����Ϊ,����������Խ����Զ�������
 */
@Configurable
public class ConditionConfig {

	@Bean
	@Conditional(WindowCondition.class)
	public ListCmdService windowService(){
		return new WindowListCmdService();
	}
	
	
	@Bean
	@Conditional(LinuxCondition.class)
	public ListCmdService linuxService(){
		return new LinuxListCmdService();
	}
}
