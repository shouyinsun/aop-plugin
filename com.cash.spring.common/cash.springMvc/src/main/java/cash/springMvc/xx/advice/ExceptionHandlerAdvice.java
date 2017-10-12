package cash.springMvc.xx.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午6:02:25
 * @ControllerAdvice 可以将控制器的全局配置放置在同一位置
 * 注解了@Controller的类可使用 @ExceptionHandler  @ModelAttribute  @InitBinder
 *
 * 定制的ControllerAdvice
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
	/**
	 *
	 * xuchao
	 * @param e
	 * @param request
	 * @return
	 *
	 * @ExceptionHandler  全局处理控制器里的异常
	 * @ModelAttribute 绑定键值对到model,在RequestMapping能看到
	 * @InitBinder 自动绑定参数到请求到Model
	 */
	@ExceptionHandler(value=Exception.class)
	public ModelAndView exception(Exception e,WebRequest request){
		ModelAndView modelAndView=new
				ModelAndView("error");
		modelAndView.addObject("errorMessage", e.getMessage());
		return modelAndView;
	}

	@ModelAttribute
	public void addAttrbutes(Model model){
		model.addAttribute("msg", "额外信息");
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		//忽略request参数的id
		webDataBinder.setDisallowedFields("id");
	}
}
