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
 * @date 2017年4月16日 下午5:42:29
 * @decription 日志切面  基于自定义注解拦截 和 基于方法调用拦截
 */
@Aspect
@Component
public class LogAspect {
	
	//定义一个切入点
	@Pointcut("@annotation(cash.spring.common.aop.Action)")
	public void annotationPointCut(){
		
	}
	
	@After("annotationPointCut()")
	public void after(JoinPoint jp){
		MethodSignature signature=(MethodSignature) jp.getSignature();
		Method method=signature.getMethod();
		Action a=method.getAnnotation(Action.class);
		System.out.println("注解式拦截:"+a.name());
	}
	
	
	@Before("execution(* cash.spring.common.aop.MethodService..*(..))")
	//@Before("execution(* cash.spring.common.aop.MethodService.*(..))")
	public void before(JoinPoint jp){
		MethodSignature signature=(MethodSignature) jp.getSignature();
		Method method=signature.getMethod();
		System.out.println("方法规则拦截:"+method.getName());
	}
}
