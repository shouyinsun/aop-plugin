package cash.springMvc.xx.push.servlet3;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
	private DeferredResult<String> deferredResult;
	
	public DeferredResult<String> getAsyncUpdate(){
		deferredResult=new DeferredResult<String>();
		return deferredResult;
	}
	
	
	@Scheduled(fixedDelay=5000)
	public void refresh(){
		if(null!=deferredResult){
			deferredResult.setResult(System.currentTimeMillis()+"");
		}
	}
	
}
