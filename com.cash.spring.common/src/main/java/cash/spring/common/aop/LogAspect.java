package cash.spring.common.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����5:42:29
 * @decription ��־����  �����Զ���ע������ �� ���ڷ�����������
 */
@Aspect
@Component
public class LogAspect {
	
	//����һ�������
	@Pointcut("@annotation(cash.spring.common.aop.Action)")
	public void annotationPointCut(){
		
	}
	
	@After("annotationPointCut()")
	public void after(JoinPoint jp){
		MethodSignature signature=(MethodSignature) jp.getSignature();
		Method method=signature.getMethod();
		Action a=method.getAnnotation(Action.class);
		System.out.println("ע��ʽ����:"+a.name());
	}
	
	
	@Before("execution(* cash.spring.common.aop.MethodService..*(..))")
	//@Before("execution(* cash.spring.common.aop.MethodService.*(..))")
	public void before(JoinPoint jp){
		MethodSignature signature=(MethodSignature) jp.getSignature();
		Method method=signature.getMethod();
		System.out.println("������������:"+method.getName());
	}
}
