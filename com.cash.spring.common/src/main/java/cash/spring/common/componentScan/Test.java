package cash.spring.common.componentScan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cash.spring.common.componentScan.controller.ControllerTest;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午5:08:47
 * @decription AnnotationConfigApplicationContext spring 容器    接收 配置类作为参数
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext afac=new AnnotationConfigApplicationContext(DoConfig.class);
		ControllerTest ct=afac.getBean(ControllerTest.class);
		ct.test();
	}

}
