package cn.zg.service.inter;

import java.util.List;

import cn.zg.entity.PageInfo;
import cn.zg.entity.task.TaskFileManagement;
import cn.zg.entity.task.TaskQueryCondition;
import cn.zg.entity.task.TemporaryTask;

/**
 * 临时任务业务层接口
 * @author Jiang, Hang
 * @date 2019-12-25
 *
 */
public interface TemporaryTaskSI {
	
	/**
	 * 条件查询临时任务列表
	 * @param con - 查询条件对象
	 * @param page - 当前页数
	 * @param limit - 每页条数
	 * @return
	 */
	PageInfo<TemporaryTask> getTaskList(
			TaskQueryCondition con, Integer page, Integer limit);
	
	/**
	 * 添加临时任务记录
	 * @param task - 临时任务对象
	 * @param file - 临时任务文件对象
	 * @return
	 */
	boolean setTaskInfo(TemporaryTask task, List<TaskFileManagement> files);
	
	/**
	 * 查询临时任务相关的文件
	 * @param taskID - 临时任务ID
	 * @return
	 */
	List<TaskFileManagement> getTaskFileList(String taskID);
	
	/**
	 * 根据任务ID获取临时任务信息
	 * @param taskID - 临时任务ID
	 */
	TemporaryTask getTaskInfo(String taskID);

}
