package cn.springmvc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Map<String, MultipartFile> fileMap = request.getFileMap();
        String fileName = null;
        String path = null;
        String imgurl=null;
		for (MultipartFile multipartFile : fileMap.values()) {
			// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			/*String realPath = request.getSession().getServletContext()
					.getRealPath("\\%TOMCAT_HOME%\\webapps\\SunDay\\WEB-INF\\upload\\");
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(
					realPath, multipartFile.getOriginalFilename()));
			imgurl = realPath+"/"+multipartFile.getOriginalFilename();*/
			
			
			 fileName = multipartFile.getOriginalFilename();
		     path = "E:\\workspace\\SunDay\\src\\main\\webapp\\upload\\" + fileName;
		     
		     File localFile = new File(path);
		     //写文件到本地
		     //multipartFile.transferTo(localFile);

		}

		return fileName;
	}

}
