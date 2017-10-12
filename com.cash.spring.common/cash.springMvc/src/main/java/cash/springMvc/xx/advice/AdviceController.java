package cash.springMvc.xx.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午6:22:00
 *
 */
@Controller
public class AdviceController {
	@RequestMapping("/advice")
	public String doSomething(Vo vo,@ModelAttribute("msg") String msg){
		throw new IllegalArgumentException("参数有误！");
	}
}
