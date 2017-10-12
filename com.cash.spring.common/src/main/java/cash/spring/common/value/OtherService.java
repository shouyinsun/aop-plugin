package cash.spring.common.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017年4月16日 下午6:59:28
 * @decription 其他bean 
 */
@Service
public class OtherService {
	
	@Value("其他类的属性")
	public String another;

	public String getAnother() {
		return another;
	}

	public void setAnother(String another) {
		this.another = another;
	}
	
	
}
