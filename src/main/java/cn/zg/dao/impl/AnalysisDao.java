package cn.zg.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnalysisDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**   
	 * @Title: selectPointType   
	 * @Description:   获取分析方案的巡检单元种类
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String, Object>> selectPointType( String name ) {
		String sql = " select distinct(CHECKPOSITON) from CZ_INSPECTION_HEADCONFIG "
				+ "  where INSPECTIONNAME = '" + name + "' ";
		return jdbcTemplate.queryForList( sql );
	}
	
	/**   
	 * @Title: selectPointType   
	 * @Description:    获取分析方案的巡检项目种类
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String, Object>> selectProjectType( String name, String pointName ) {
		String sql = " select distinct(PROJECTNAME1) from CZ_INSPECTION_HEADCONFIG "
				+ "  where INSPECTIONNAME = '" + name + "' and CHECKPOSITON='" + pointName + "' ";
		return jdbcTemplate.queryForList( sql );
	}
}
