package cn.zg.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.zg.entity.dataExchange.ResultJson;

/**
 * @ClassName: FileUploadController
 * @Description: 文件上传控制器
 * @author zhugang
 * @date 2018年8月21日
 */
@RestController
@PropertySource("classpath:configProperties/fileDownUpload.properties")
@RequestMapping("/upload")
public class FileUploadController {
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	//文件上传保存地址
	@Value("${file.upload.path}")
	public String fileUploadPath;
	
	//文件保存后缀
	@Value("${file.upload.fileSuffer}")
	public String fileSuffer;
	
	/**   
	 * @Title: getAndSaveFile   
	 * @Description: 接受和保存上传的文件   
	 * @param: @param file
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResultJson<String> getAndSaveFile(@RequestParam("fileName") MultipartFile file) {				
		if(file.isEmpty()) {
			logger.debug("上传的文件为空");
			return new ResultJson<String>("上传的文件为空");
		}
		
		/*
		 * 获取文件信息，保存文件
		 */
		String fileName = file.getOriginalFilename();
		int fileSize = (int)file.getSize();
		System.out.println("文件大小为：" + fileSize);
		File newFile = new File("/html/htmlCreate" + fileName);
		if(!newFile.getParentFile().exists()) {   //判断父级文件目录是否存在
			newFile.getParentFile().mkdirs();
		}		
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException | IOException e) {			
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultJson<String>(1, e.getMessage());
		}
		return new ResultJson<String>(0, "上传的文件成功");
	}
	
	/**   
	 * @Title: getStringSaveAsFile   
	 * @Description: 接收字符串，保存入文件   
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@RequestMapping(value = "/string", method = RequestMethod.POST)
	public ResultJson<String> getStringSaveAsFile(
			@RequestParam("wholePageCode") @NotNull String wholePageCode,
			@RequestParam("pathName") String pathName){
		/*
		 * 新建文件
		 */
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String nowDate = sf.format(new Date());
		File file = new File(fileUploadPath + File.separator + pathName + 
				File.separator + pathName + "_" + nowDate + "." + fileSuffer);
			
		/*
		 * 保存文件
		 */
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.println(wholePageCode);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultJson<String>(1, e.getMessage());
		}		
		return new ResultJson<String>(0, "上传文件成功");
	}
}
