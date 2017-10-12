package cash.springMvc.xx;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午2:21:40
 * 一个简单控制器
 */
@Controller
public class SimpleController {
	@RequestMapping("/index")
	public String hello(){
		return "index";
	}

	@RequestMapping("/index.html")
	public String hello2(){
		return "index2";
	}
}
