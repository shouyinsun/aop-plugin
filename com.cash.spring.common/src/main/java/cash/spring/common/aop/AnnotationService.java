package cash.spring.common.aop;

import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午5:38:23
 * @decription 基于注解拦截的service
 */
@Service
public class AnnotationService {
	@Action(name="注解式拦截")
	public void add(){
		
	}
}
