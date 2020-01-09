package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zg.dao.TemporaryTaskDao;
import cn.zg.entity.PageInfo;
import cn.zg.entity.task.TaskFileManagement;
import cn.zg.entity.task.TaskQueryCondition;
import cn.zg.entity.task.TemporaryTask;
import cn.zg.service.inter.TemporaryTaskSI;
import lombok.extern.slf4j.Slf4j;

/**
 * 临时任务持久层实现类
 * @author Jiang, Hang
 * @date 2019-12-25
 */
@Service
@Slf4j
public class TemporaryTaskS implements TemporaryTaskSI {
	
	@Autowired
	private TemporaryTaskDao taskDao;
	
	/**
	 * 条件查询临时任务列表
	 * @param con - 查询条件对象
	 * @param page - 当前页数
	 * @param limit - 每页条数
	 * @return
	 */
	@Override
	public PageInfo<TemporaryTask> getTaskList(TaskQueryCondition con, Integer page, Integer limit) {
		log.info("-----开始条件查询临时任务列表-----");
		try {
			List<TemporaryTask> list = null;
			PageInfo<TemporaryTask> info = taskDao.selectTaskCount(con);
			if(info != null && info.getCount() != 0) {
				list = taskDao.selectTaskList(con, page, limit);
			}
			
			if(list != null && list.size() != 0) {
				Calendar curr = Calendar.getInstance();
				curr.set(Calendar.HOUR_OF_DAY, 0);
				curr.set(Calendar.MINUTE, 0);
				curr.set(Calendar.SECOND, 0);
				curr.set(Calendar.MILLISECOND, 0);
				for(TemporaryTask task : list) {
					if("UNFINISHED".equals(task.getTaskState())) {
						Date date = task.getRequireFinishTime();
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);

						if(curr.compareTo(cal) > 0) {
							task.setTaskState("OVERFINISHED");
						}
					}
				}
			}
			
			log.info("-----条件查询临时任务列表成功-----");
			info.setList(list);
			return info;
		}catch (Exception e) {
			log.info("-----条件查询临时任务列表发生错误-----");
			log.info("{}", e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添加临时任务记录
	 * @param task - 临时任务对象
	 * @return
	 */
	@Override
	@Transactional
	public boolean setTaskInfo(TemporaryTask task, List<TaskFileManagement> files) {
		log.info("-----开始添加临时任务记录-----");
		try {
			Integer taskType = task.getTaskType();
			if(taskType != null && taskType == 1) {
				String[] names = task.getExecutePerson().split(",");
				for(String name : names) {
					task.setExecutePerson(name);
					taskDao.insertTaskInfo(task);
					log.info("-----taskID={}", task.getTaskID());
					for(TaskFileManagement file : files) {
						file.setTaskID(task.getTaskID());
						taskDao.insertTaskFile(file);
					}
				}
			}else {
				taskDao.insertTaskInfo(task);
				log.info("-----taskID={}", task.getTaskID());
				for(TaskFileManagement file : files) {
					file.setTaskID(task.getTaskID());
					taskDao.insertTaskFile(file);
				}
			}
			
			log.info("-----添加临时任务记录成功-----");
			return true;
		}catch (Exception e) {
			log.info("-----添加临时任务记录发生错误-----");
			log.info("{}", e);
			throw new RuntimeException("添加临时任务记录发生错误");
		}
	}
	
	/**
	 * 查询临时任务相关的文件
	 * @param taskID - 临时任务ID
	 * @return
	 */
	@Override
	public List<TaskFileManagement> getTaskFileList(String taskID) {
		log.info("-----开始查询临时任务相关的文件-----");
		try {
			List<TaskFileManagement> list = taskDao.selectTaskFileList(taskID);
			if(list == null) {
				list = new ArrayList<>();
			}
			log.info("-----查询临时任务相关的文件成功-----");
			return list;
		}catch (Exception e) {
			log.info("-----查询临时任务相关的文件发生错误-----");
			log.info("{}", e);
			return null;
		}
	}
	
	/**
	 * 根据任务ID获取临时任务信息
	 * @param taskID - 临时任务ID
	 */
	@Override
	public TemporaryTask getTaskInfo(String taskID) {
		log.info("-----开始根据任务ID获取临时任务信息-----");
		try {
			TemporaryTask task = taskDao.selectTaskInfo(taskID);
			if(task == null) {
				log.info("-----该临时任务不存在-----");
			}
			log.info("-----根据任务ID获取临时任务信息成功-----");
			return task;
		}catch (Exception e) {
			log.info("-----根据任务ID获取临时任务信息发生错误-----");
			log.info("{}", e);
			return null;
		}
	}
	
	/**
	 * 更新临时任务信息
	 * @param task - 临时任务对象
	 * @param files - 上传的文件
	 * @return
	 */
	@Override
	@Transactional
	public Boolean updateTaskInfo(TemporaryTask task, List<TaskFileManagement> files) {
		log.info("-----开始更新临时任务信息-----");
		try {
			taskDao.updateTaskInfo(task);
			if(files != null) {
				for(TaskFileManagement file : files) {
					taskDao.insertTaskFile(file);
				}
			}
			log.info("-----更新临时任务信息成功-----");
			return true;
		}catch (Exception e) {
			log.info("-----更新临时任务信息发生错误-----");
			log.info("{}", e);
			throw new RuntimeException("更新临时任务信息发生异常");
		}
	}
}
