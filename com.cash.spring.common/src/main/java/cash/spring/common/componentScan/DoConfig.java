package cash.spring.common.componentScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午4:28:54
 * @decription @Configuration 表明当前类是一个配置类   @ComponentScan 自动扫描包路径下的使用申明式注解的类：@Controller @Service @Component @Repository 
 * 并且注册成bean
 * 
 */

@Configuration
@ComponentScan("cash.spring.common.componentScan")
public class DoConfig {

}
