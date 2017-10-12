package cash.springMvc.xx.httpMessageConverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午9:49:59
 * 自定义的  messageConverter
 *
 * 处理自定义一个媒体类型  application/x-wisely
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObject> {


	public MyMessageConverter() {
		// TODO Auto-generated constructor stub
		super( new MediaType("application","x-wisely",Charset.forName("UTF-8")));
	}

	/**
	 * 重写  readInternal 方法
	 * 数据由"-" 分割,并转成 DemoObject
	 */
	@Override
	protected DemoObject readInternal(Class<? extends DemoObject> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		String temp=StreamUtils.copyToString(inputMessage.getBody(),Charset.forName("UTF-8"));
		String[] tempArr=temp.split("-");

		return new DemoObject(new Long(tempArr[0]), tempArr[1]);
	}

	/**
	 * isAssignableFrom 判断类是否相同,或者是父类或者接口
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DemoObject.class.isAssignableFrom(clazz);
	}

	/**
	 * 重写 writeInternal 方法
	 * 处理输出数据
	 */
	@Override
	protected void writeInternal(DemoObject obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		String out="hello:"+obj.getId()+"-"+obj.getName();
		outputMessage.getBody().write(out.getBytes());
	}

}
