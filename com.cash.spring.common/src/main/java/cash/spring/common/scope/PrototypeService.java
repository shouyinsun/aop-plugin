package cash.spring.common.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午6:28:34
 * @decription scope 为 prototype ,每次调用都新建一个bean的实例
 */
@Service
@Scope("prototype")
public class PrototypeService {

}
