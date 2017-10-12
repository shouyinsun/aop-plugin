package cash.spring.common.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	/**
	 * 
	 * @author cash
	 * @date 2017年4月16日 下午6:14:02
	 * @decription  AnnotationConfigApplicationContext spring 容器    接收 配置类作为参数
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(DoConfig.class);
		AnnotationService annotationService=ac.getBean(AnnotationService.class);
		MethodService methodService=ac.getBean(MethodService.class);
		annotationService.add();
		methodService.add();
		ac.close();

	}

}
