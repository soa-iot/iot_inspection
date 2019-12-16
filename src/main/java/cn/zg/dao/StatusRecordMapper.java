package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.daoEntity.StatusRecord;

@Mapper
public interface StatusRecordMapper {

	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description: 查看任务状态   
	 * @return: List<StatusRecord>        
	 */  
	public List<StatusRecord> findTaskStateByPlanidAndTime(
			@Param("planId") String planId
			,@Param("time") String time );
	
	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description: 查看任务状态   
	 * @return: List<StatusRecord>        
	 */  
	public List<StatusRecord> findTaskStateByPlanidAndMonth(
			@Param("planId") String planId
			,@Param("time") String time );
}
