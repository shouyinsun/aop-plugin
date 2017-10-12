package cash.spring.common.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	/**
	 * 
	 * @author cash
	 * @date 2017��4��16�� ����6:14:02
	 * @decription  AnnotationConfigApplicationContext spring ����    ���� ��������Ϊ����
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
