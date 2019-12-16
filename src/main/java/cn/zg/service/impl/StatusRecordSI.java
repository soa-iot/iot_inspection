package cn.zg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
			
			//格式化时间
			for( StatusRecord s : statusRecords) {
				Date record_TIME = s.getRECORD_TIME();
				if( record_TIME != null ) s.setREMARK3( sdf.format( record_TIME ));
			}
			
			logger.info( "------查看巡检任务状态-------");
			statusRecords.forEach( s-> logger.info( s.toString() ) );
			return statusRecords;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description:  查看巡检任务状态 
	 * @return: List<StatusRecord>        
	 */  
	@Override
	public List<StatusRecord> findTaskStateByPlanidAndMonth( String planId, String time){
		try {
			List<StatusRecord> statusRecords = statusRecordMapper.findTaskStateByPlanidAndMonth(planId, time);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
			
			//格式化时间
			for( StatusRecord s : statusRecords) {
				Date record_TIME = s.getRECORD_TIME();
				if( record_TIME != null ) s.setREMARK3( sdf.format( record_TIME ));
			}
			
			logger.info( "------查看巡检任务状态-------");
			statusRecords.forEach( s-> logger.info( s.toString() ) );
			return statusRecords;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	//findTaskStateByPlanidAndMonth
	
}
