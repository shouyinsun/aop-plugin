package cash.spring.common.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cash
 * @date 2017��4��16�� ����6:59:28
 * @decription ����bean 
 */
@Service
public class OtherService {
	
	@Value("�����������")
	public String another;

	public String getAnother() {
		return another;
	}

	public void setAnother(String another) {
		this.another = another;
	}
	
	
}
