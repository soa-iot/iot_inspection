package cn.zg.dao.inter;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.zg.entity.daoEntity.InpectionValue;

/**
 * @ClassName: InpectionValueRepos
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhugang
 * @date 2018年11月13日
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
	public List<InpectionValue> findByPlanidAndTime( 
			@Param( "planId" ) String planId, @Param( "str" ) String str );
}
