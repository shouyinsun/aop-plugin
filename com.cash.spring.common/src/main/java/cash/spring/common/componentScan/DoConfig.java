package cash.spring.common.componentScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����4:28:54
 * @decription @Configuration ������ǰ����һ��������   @ComponentScan �Զ�ɨ���·���µ�ʹ������ʽע����ࣺ@Controller @Service @Component @Repository 
 * ����ע���bean
 * 
 */

@Configuration
@ComponentScan("cash.spring.common.componentScan")
public class DoConfig {

}
