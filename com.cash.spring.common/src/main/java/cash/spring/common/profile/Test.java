package cash.spring.common.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
		ac.getEnvironment().setActiveProfiles("pro");//先将容器的profile设置值
		ac.register(ProfileConfig.class);//注册bean类
		ac.refresh();//刷新容器
		Service s=ac.getBean(Service.class);
		
		System.out.println(s.getContent());

	}

}
