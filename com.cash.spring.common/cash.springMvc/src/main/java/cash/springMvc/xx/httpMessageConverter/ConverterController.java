package cash.springMvc.xx.httpMessageConverter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午10:16:40
 *
 * @RequestBody 和  @ResponseBody 根据媒体类型,选择合适的MessageConverter
 */
@Controller
public class ConverterController {
	@RequestMapping(value="/convert",produces={"application/x-wisely"})
	public @ResponseBody DemoObject converter(@RequestBody DemoObject obj){
		return obj;
	}
}
