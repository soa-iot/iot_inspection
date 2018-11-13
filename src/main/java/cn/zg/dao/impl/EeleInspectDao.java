package cn.zg.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.zg.entity.daoEntity.InpectionValue;

@Repository
@Transactional
public class EeleInspectDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
		
	public int saveEeleInspectAll( List<InpectionValue> inpectValues ) {
		System.out.println( namedParameterJdbcTemplate );
		String sql = "insert into CZ_TASK_INSPECTION_VALUE "
				+ "   (ivid,plan_id,record_time,position_num,value,unit,remark1,remark2) "
				+ "		values  "
				+ "	  (SEQUENCE_CTIV.nextval,:plan_id,:record_time,"
				+ "		:position_num,:value,:unit,:remark1,:remark2)";
		SqlParameterSource[] beanSources = 
				SqlParameterSourceUtils.createBatch( inpectValues.toArray() );

		int[] i = namedParameterJdbcTemplate.batchUpdate( sql, beanSources);		
		System.out.println( "i:" + i[0]);
		return i[0];
	}
}
