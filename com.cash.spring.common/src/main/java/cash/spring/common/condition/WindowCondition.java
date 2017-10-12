package cash.spring.common.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 
 * @author xuchao
 * 2017��4��22�� ����11:44:28
 * 
 * windows ϵͳ�����ж�
 */
public class WindowCondition implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// TODO Auto-generated method stub
		
		return context.getEnvironment().getProperty("os.name").contains("Windows");
	}

}
