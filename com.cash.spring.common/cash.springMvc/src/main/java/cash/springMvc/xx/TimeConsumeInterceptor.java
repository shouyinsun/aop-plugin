package cash.springMvc.xx;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author xuchao 2017年4月23日 下午4:43:49
 *
 * 方法耗时统计  拦截器
 */
public class TimeConsumeInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime=System.currentTimeMillis();
		Something s=new Something();
		s.setTraceId(startTime+UUID.randomUUID().toString());
		ThreadLocalUtils.set(s);
		System.out.println(s.getTraceId()+" begin");
		request.setAttribute("startTime", startTime);
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj,
						   ModelAndView modelandview) throws Exception {
		long startTime=(Long) httpservletrequest.getAttribute("startTime");
		httpservletrequest.removeAttribute("startTime");
		long endTime=System.currentTimeMillis();
		System.out.println(ThreadLocalUtils.getTraceId()+" end,此次请求的耗时为："+(endTime-startTime)+" ms");
	}

}
