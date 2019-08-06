package cn.zg.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.StatusRecordMapper;
import cn.zg.entity.daoEntity.StatusRecord;
import cn.zg.service.inter.StatusRecordS;

@Service
public class StatusRecordSI implements StatusRecordS{
private static Logger logger = LoggerFactory.getLogger( StatusRecordSI.class );
	
	@Autowired
	private StatusRecordMapper statusRecordMapper;
	
	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description:  查看巡检任务状态 
	 * @return: List<StatusRecord>        
	 */  
	@Override
	public List<StatusRecord> findTaskStateByPlanidAndTime( String planId, String time){
		try {
			List<StatusRecord> statusRecords = statusRecordMapper.findTaskStateByPlanidAndTime(planId, time);
			logger.info( "------查看巡检任务状态-------");
			statusRecords.forEach( s-> logger.info( s.toString() ) );
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
}
