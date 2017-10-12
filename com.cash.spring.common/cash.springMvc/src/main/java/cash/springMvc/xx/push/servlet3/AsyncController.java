package cash.springMvc.xx.push.servlet3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author xuchao
 * 2017年4月23日 下午11:40:28
 *
 */
import org.springframework.web.context.request.async.DeferredResult;
@Controller
public class AsyncController {
	@Autowired
	PushService pushService;

	@RequestMapping("/defer")
	@ResponseBody
	public DeferredResult<String> deferredCall(){
		return pushService.getAsyncUpdate();
	}
}
