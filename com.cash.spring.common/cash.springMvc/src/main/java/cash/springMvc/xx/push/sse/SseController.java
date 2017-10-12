package cash.springMvc.xx.push.sse;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午11:03:00
 * SSE server send event 服务端发送事件
 */
@Controller
public class SseController {
	/***
	 *
	 * 输出的媒体类型是text/event-stream,是sse的支持
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/push",produces="text/event-stream")
	public @ResponseBody String push(HttpServletResponse response) throws UnsupportedEncodingException{
		Random r=new Random();
		try {
			//间隔5秒向浏览器推送消息
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/event-stream, charset=UTF-8");
		return "xxxxx"+r.nextInt();
	}
}
