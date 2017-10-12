package cash.spring.common.condition;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * 
 * @author xuchao
 * 2017年4月22日 下午11:49:03
 * 
 * @Conditional 注解控制bean的创建行为,利用这个可以进行自动化配置
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
