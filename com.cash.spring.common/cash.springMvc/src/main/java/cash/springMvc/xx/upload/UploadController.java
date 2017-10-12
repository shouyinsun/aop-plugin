package cash.springMvc.xx.upload;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author xuchao
 * 2017年4月23日 下午9:27:32
 *
 * 文件上传
 */
@Controller
public class UploadController {
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public @ResponseBody String upload(MultipartFile file){
		try {
			FileUtils.writeByteArrayToFile(new File("c:/upload/"+file.getOriginalFilename()), file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "not ok";
		}

		return "ok";
	}
}
