package cash.spring.common.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����6:28:34
 * @decription scope Ϊ prototype ,ÿ�ε��ö��½�һ��bean��ʵ��
 */
@Service
@Scope("prototype")
public class PrototypeService {

}
