package cn.zg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.zg.entity.PageInfo;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.entity.task.TaskFileManagement;
import cn.zg.entity.task.TaskQueryCondition;
import cn.zg.entity.task.TemporaryTask;
import cn.zg.service.impl.TemporaryTaskS;
import cn.zg.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 临时任务控制层
 * @author Jiang, Hang
 * @date 2019-12-25
 *
 */
@RestController
@RequestMapping("/temporarytask")
@Slf4j
public class TemporaryTaskC {
	
	@Autowired
	private TemporaryTaskS taskS;
	
	/**
	 * 条件查询临时任务列表
	 * @param con - 查询条件对象
	 * @param page - 当前页数
	 * @param limit - 每页条数
	 * @return
	 */
	@RequestMapping("/show/list")
	public ResultJson<PageInfo<TemporaryTask>> showTemporaryList(
			TaskQueryCondition con, Integer page, Integer limit){
		log.info("-----进入接口TemporaryTaskC...showTemporaryList-----");
		log.info("-----查询条件con={}", con);
		log.info("-----当前页数page={}", page);
		log.info("-----页面条数limit={}", limit);
		
		//调用业务层执行操作
		PageInfo<TemporaryTask> taskList = taskS.getTaskList(con, page, limit);
		
		if(taskList != null) {
			return new ResultJson<>(ResultJson.SUCCESS, "查询临时任务列表成功", taskList);
		}
		return new ResultJson<>(ResultJson.ERROR, "查询临时任务列表失败", null);
	}
	
	@Value("${task.file.path}")
	private String filePath;
	
	/**
	 * 
	 * @param task
	 * @param request
	 * @return
	 */
	@RequestMapping("/task/create")
	public ResultJson<Boolean> createTemporaryTask(@NotNull TemporaryTask task, HttpServletRequest request){
		log.info("-----进入接口TemporaryTaskC...createTemporaryTask-----");
		log.info("-----临时任务：{}", task);
		
		List<TaskFileManagement> fileList = new ArrayList<>();
		
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
				request.getSession().getServletContext());
		//检查form中是否有enctype="multipart/form-data"
		if(multipartResolver.isMultipart(request)){	
			try {
				
				//将request变成多部分request
				MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
				//获取multiRequest 中所有的文件名
				Iterator iter=multiRequest.getFileNames();
				while(iter.hasNext()){
					//一次遍历所有文件
					MultipartFile file=multiRequest.getFile(iter.next().toString());
					log.info("-----临时任务上传文件原名称: {}", file.getOriginalFilename());
					if(file!=null){
						String uuid = UUID.randomUUID().toString();
						String filename = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
						log.info("-----文件重新命名: {}", filename);
						File dirParent = CommonUtil.imageSaved(filePath, null);
						File fileCreate = new File(dirParent, filename);
						//保存文件至硬盘
						file.transferTo(fileCreate);
						//生成虚拟的文件路径
						String filePath = fileCreate.getAbsolutePath().replace("\\", "/");
						TaskFileManagement fileMan = new TaskFileManagement();
						fileMan.setFileClass(0);
						fileMan.setFileOriginalName(file.getOriginalFilename());
						fileMan.setFilePath(filePath);
						fileMan.setUploadPerson(task.getCreatePerson());
						fileMan.setUploadTime(new Date());
						fileList.add(fileMan);
					}
				}
			}catch (Exception e) {
				log.info("-----文件保存发生错误-----");
				log.info("{}", e);
				return new ResultJson<Boolean>(ResultJson.ERROR, "文件保存发生异常", false);
			}
		}
		task.setTaskState("UNFINISHED");
		try {
			taskS.setTaskInfo(task, fileList);
			return new ResultJson<>(ResultJson.SUCCESS, "添加临时任务记录成功", true);
		}catch (Exception e) {
			return new ResultJson<>(ResultJson.ERROR, "添加临时任务记录失败", false);
		}

	}
	
	/**
	 * 查询临时任务相关的文件列表
	 * @param taskID - 临时任务ID
	 * @return
	 */
	@RequestMapping("/file/showlist")
	public ResultJson<List<TaskFileManagement>> showTaskFileList(@RequestParam String taskID){
		log.info("-----进入接口TemporaryTaskC...showTaskFileList-----");
		log.info("-----临时任务ID：{}", taskID);
		
		List<TaskFileManagement> list = taskS.getTaskFileList(taskID);
		
		if(list != null) {
			return new ResultJson<>(ResultJson.SUCCESS, "查询临时任务相关的文件成功", list);
		}
		return new ResultJson<>(ResultJson.ERROR, "查询临时任务相关的文件失败", null);
	}
	
	/**
	 * 临时任务文件下载
	 * @param fileName - 文件名
	 * @param filePath - 文件路径
	 * @return
	 */
	@RequestMapping("/file/download")
	public ResponseEntity<byte[]> downloadTaskFile(
			@RequestParam String fileName, @RequestParam String filePath){
		log.info("-----进入接口TemporaryTaskC...downloadTaskFile-----");
		log.info("-----临时任务文件名：{}", fileName);
		log.info("-----临时任务文件路径：{}", filePath);
		
		File file = new File(filePath);
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] body = new byte[(int)file.length()];    
				fis.read(body);  
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				header.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
				HttpHeaders headers = new HttpHeaders();
				headers.setContentLength(file.length());
				
				return new ResponseEntity<byte[]>(body, header, HttpStatus.OK);
			}catch (UnsupportedEncodingException e) {
				log.info("-----文件名转码失败-----");
				log.info("{}", e);
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}catch (Exception e) {
				log.info("-----获取文件失败-----");
				log.info("{}", e);
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			 
		}
		log.info("-----文件不存在-----");
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 获取临时任务详情
	 * @param taskID
	 * @return
	 */
	@RequestMapping("/task/detail")
	public ResultJson<TemporaryTask> showTaskInfo(@RequestParam String taskID){
		log.info("-----进入接口TemporaryTaskC...showTaskInfo-----");
		log.info("-----临时任务ID：{}", taskID);
		
		TemporaryTask taskInfo = taskS.getTaskInfo(taskID);
		
		if(taskInfo != null) {
			return new ResultJson<>(ResultJson.SUCCESS, "查询临时任务详情成功", taskInfo);
		}
		return new ResultJson<>(ResultJson.ERROR, "查询临时任务详情失败", null);
	}
	
	/**
	 * 完成临时任务
	 * @param task - 临时任务对象
	 * @return
	 */
	@RequestMapping("/finish/task")
	public ResultJson<Boolean> finishTask(@NotNull TemporaryTask task, HttpServletRequest request){
		log.info("-----进入接口TemporaryTaskC...finishTask-----");
		log.info("-----临时任务信息：{}", task);
		
		//先检查任务是否已完成
		TemporaryTask taskInfo = taskS.getTaskInfo(task.getTaskID());
		
		if(taskInfo == null) {
			return new ResultJson<>(ResultJson.ERROR, "临时任务"+task.getTaskID()+"不存在", false);
		}else {
			if("FINISHED".equals(taskInfo.getTaskState())) {
				return new ResultJson<>(ResultJson.ERROR, "临时任务"+task.getTaskID()+"状态已经完成，不能再次完成", false);
			}
		}
		
		List<TaskFileManagement> fileList = new ArrayList<>();
		
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
				request.getSession().getServletContext());
		//检查form中是否有enctype="multipart/form-data"
		if(multipartResolver.isMultipart(request)){	
			try {
				
				//将request变成多部分request
				MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
				//获取multiRequest 中所有的文件名
				Iterator iter=multiRequest.getFileNames();
				while(iter.hasNext()){
					//一次遍历所有文件
					MultipartFile file=multiRequest.getFile(iter.next().toString());
					log.info("-----临时任务上传文件原名称: {}", file.getOriginalFilename());
					if(file!=null){
						String uuid = UUID.randomUUID().toString();
						String filename = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
						log.info("-----文件重新命名: {}", filename);
						File dirParent = CommonUtil.imageSaved(filePath, null);
						File fileCreate = new File(dirParent, filename);
						//保存文件至硬盘
						file.transferTo(fileCreate);
						//生成虚拟的文件路径
						String filePath = fileCreate.getAbsolutePath().replace("\\", "/");
						TaskFileManagement fileMan = new TaskFileManagement();
						fileMan.setFileClass(1);
						fileMan.setFileOriginalName(file.getOriginalFilename());
						fileMan.setFilePath(filePath);
						fileMan.setTaskID(task.getTaskID());
						fileMan.setUploadPerson(task.getExecutePerson());
						fileMan.setUploadTime(new Date());
						fileList.add(fileMan);
					}
				}
			}catch (Exception e) {
				log.info("-----文件保存发生错误-----");
				log.info("{}", e);
				return new ResultJson<Boolean>(ResultJson.ERROR, "文件保存发生异常", false);
			}
		}
		task.setExecutePerson(null);
		task.setTaskState("FINISHED");
		task.setActualFinishTime(new Date());
		try {
			Boolean result = taskS.updateTaskInfo(task, fileList);
			return new ResultJson<>(ResultJson.SUCCESS, "完成临时任务成功", result);
		}catch (Exception e) {
			log.info("{}", e);
			return new ResultJson<>(ResultJson.ERROR, "完成临时任务失败", false);
		}

	}
}
