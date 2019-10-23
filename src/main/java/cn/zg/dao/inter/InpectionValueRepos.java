package cn.zg.dao.inter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.zg.entity.daoEntity.InpectionValue;

/**
 * @ClassName: InpectionValueRepos
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Bru.Lo
 * @date 2019年10月23日
 */
public interface InpectionValueRepos  extends JpaRepository< InpectionValue , String >{

	/**   
	 * @Title: findByPlanIdAndRecordTime   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param planId
	 * @param: @param date1
	 * @param: @return      
	 * @return: List<InpectionValue>        
	 */  
	@Query( nativeQuery = true,
			value = " select * from CZ_TASK_INSPECTION_VALUE "
					+ "  where PLAN_ID = :planId "
					+ "  and RECORD_TIME like to_date(:str,'YYYY-MM-DD')"
					+ "  Order BY RECORD_TIME ")
	public ArrayList<InpectionValue> findByPlanidAndTime( 
			@Param( "planId" ) String planId, @Param( "str" ) String str );
	
	
	/**   
	 * @Title: findTaskStateByPlanidAndTime   
	 * @Description:  查看任务状态 
	 * @return: List<Map<String,Object>>        
	 */  
	@Query( nativeQuery = true,
			value = " select distinct( REMARK2) ,state,completor from CZ_TASK_INSPECTION_VALUE "
					+ "  where PLAN_ID = :planId "
					+ "  and RECORD_TIME like to_date(:str,'YYYY-MM-DD')")
	public List<Map<String,Object>> findTaskStateByPlanidAndTime( 
			@Param( "planId" ) String planId, @Param( "str" ) String str );
	
	/**   
	 * @Title: findTaskLastDotimeByPlanidAndTime   
	 * @Description:  查看任务各时间点最后执行时间 
	 * @return: List<Map<String,Object>>        
	 */  
	@Query( nativeQuery = true,
			value = " select max(RECORD_TIME) as MAXTIME,REMARK2 from CZ_TASK_INSPECTION_VALUE "
					+ "  where PLAN_ID = :planId "
					+ "  and RECORD_TIME like to_date(:str,'YYYY-MM-DD')"
					+ "  group by REMARK2  "
			)
	public List<Map<String,Object>> findTaskLastDotimeByPlanidAndTime( 
			@Param( "planId" ) String planId, @Param( "str" ) String str );
}
