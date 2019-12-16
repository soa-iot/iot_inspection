package cn.zg.service.inter;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.StatusRecord;

@Service
public interface StatusRecordS {

	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description:  查看巡检任务状态  
	 * @return: List<StatusRecord>        
	 */  
	List<StatusRecord> findTaskStateByPlanidAndTime(String planId, String time);
	
	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description:  查看巡检任务状态  
	 * @return: List<StatusRecord>        
	 */  
	List<StatusRecord> findTaskStateByPlanidAndMonth(String planId, String time);
	
}
