package cash.spring.common.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
		ac.getEnvironment().setActiveProfiles("pro");//�Ƚ�������profile����ֵ
		ac.register(ProfileConfig.class);//ע��bean��
		ac.refresh();//ˢ������
		Service s=ac.getBean(Service.class);
		
		System.out.println(s.getContent());

	}

}
