package cash.spring.common.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xuchao
 * 2017年4月22日 下午11:58:28
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(ConditionConfig.class);
		ListCmdService listCmdService=ac.getBean(ListCmdService.class);
		listCmdService.showCmd();
		ac.close();

	}

}
