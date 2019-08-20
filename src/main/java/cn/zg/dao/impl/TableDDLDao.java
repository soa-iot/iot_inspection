package cn.zg.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @ClassName: TableDDLDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhugang
 * @date 2019年1月7日
 */
@Repository
@Transactional
public class TableDDLDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: findDDL   
	 * @Description: 查询表格的DDL语句 
	 * @param: @param tableName
	 * @param: @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String, Object>> findDDL( String tableName ) {
		String sql = "select dbms_metadata.get_ddl('TABLE','" + tableName.trim() + "') ddl from dual";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 
	 * @Title: findData   
	 * @Description: 查询表格中所有的数据   
	 * @param: @param tableName
	 * @param: @return      
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String, Object>> findData( String tableName ) {
		String sql = "select * from " + tableName;
		return jdbcTemplate.queryForList( sql );
	}
	
	/**
	 * 
	 * @Title: findTablesName   
	 * @Description: 查找配置所有表  
	 * @param: @return      
	 * @return: List<String>
	 */
	public List<Map<String, Object>> findTablesName(){
		String sql = " select table_name from mobile_table";		
		return jdbcTemplate.queryForList( sql );
	}
	
	/**   
	 * @Title: findInspectionNames   
	 * @Description: 获取所有的方案name 
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String, Object>> findInspectionNames(){
		String sql = " select distinct(inspectionName) from CZ_INSPECTION_HEADCONFIG "
				+ " group by inspectionName "; 
		return jdbcTemplate.queryForList( sql );
	}
	
	/**   
	 * @Title: findInspectionIdsNames   
	 * @Description: 获取所有的方案信息     
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String, Object>> findInspectionIdsNames( List<String> list ){
		String condition = "";
		for( int i = 0; i < list.size(); i++ ) {
			if( list.get(i) == null || list.get(i).isEmpty() ) {
				continue;
			}
			if( i == 0  ) {
				condition = "'" + list.get(i) + "'";
			}else {
				condition = "'" + list.get(i) + "'" + "," + condition;
			}						
		}
		System.out.println("condition:" + condition );
		String sql = " select SCHEME_ID,SCHEME_NAME from CZ_INSPECTION_SCHEME "
				+ " where SCHEME_ID in (" + condition + ")"; 
		return jdbcTemplate.queryForList( sql );
	}
}
