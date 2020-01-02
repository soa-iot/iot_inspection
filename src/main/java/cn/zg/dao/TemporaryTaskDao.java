package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.PageInfo;
import cn.zg.entity.task.TaskFileManagement;
import cn.zg.entity.task.TaskQueryCondition;
import cn.zg.entity.task.TemporaryTask;

/**
 * 临时任务持久层接口
 * @author Jiang, Hang
 * @date 2019-12-25
 */

@Mapper
public interface TemporaryTaskDao {
	
	/**
	 * 根据任务ID查询临时任务信息
	 */
	TemporaryTask selectTaskInfo(String taskID);
	
	/**
	 * 条件查询临时任务列表
	 * @param con - 查询条件对象
	 * @param page - 当前页数
	 * @param limit - 每页条数
	 * @return
	 */
	List<TemporaryTask> selectTaskList(
			@Param("con") TaskQueryCondition con, 
			@Param("page") Integer page, 
			@Param("limit") Integer limit);
	
	/**
	 * 统计条件查询临时任务的数据量
	 * @param con - 查询条件
	 * @return
	 */
	PageInfo<TemporaryTask> selectTaskCount(@Param("con") TaskQueryCondition con);
	
	/**
	 * 添加临时任务
	 * @param task - 临时任务对象
	 * @return
	 */
	Integer insertTaskInfo(TemporaryTask task);
	
	/**
	 * 添加临时任务文件信息
	 * @param file - 临时任务文件信息
	 * @return
	 */
	Integer insertTaskFile(TaskFileManagement file);
	
	/**
	 * 查询临时任务相关的文件
	 * @param taskID - 临时任务ID
	 * @return
	 */
	List<TaskFileManagement> selectTaskFileList(String taskID);
}
