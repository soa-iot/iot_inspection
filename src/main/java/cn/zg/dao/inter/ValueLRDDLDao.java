package cn.zg.dao.inter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.zg.entity.daoEntity.HeadConfigJSB;
import cn.zg.entity.daoEntity.ValueJSB;

/**
 * @ClassName: ValueLRMapper
 * @Description:  问题跟踪dao层
 * @author Bru.Lo
 * @date 2019年10月23日
 */
@Repository
@Transactional
public class ValueLRDDLDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**   
	 * @Title: findInspectionNames   
	 * @Description: 获取所有的方案name 
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String, Object>> findInspectionNames(String userId){
		String sql = "select NAME from IOT_USER_ROLE i1,IOT_USER_USER_ROLE i2 where I1.ROLID=I2.ROLID AND i1.REMARK2='paid' AND  I2.USERID='"+userId+"'"; 
		return jdbcTemplate.queryForList( sql );
	}
	
	/**   
	 * @Title: findInspectionIdsNames   
	 * @Description: 获取所有的方案信息     
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String, Object>> findInspectionIdsNames( List<String> list ){
		String condition = " UPPER(SCHEME_NAME) LIKE ";
		for( int i = 0; i < list.size(); i++ ) {
			if( list.get(i) == null || list.get(i).isEmpty() ) {
				continue;
			}
			if( i == 0  ) {
				condition += " UPPER('" + list.get(i) + "%')";
			}else {
				condition += " OR UPPER(SCHEME_NAME) LIKE UPPER('" + list.get(i) + "%')";
			}						
		}
		System.out.println("condition:" + condition );
		String sql = "SELECT DISTINCT SCHEME_ID,SCHEME_NAME FROM CZ_INSPECTION_SCHEME WHERE"+condition+"ORDER BY SCHEME_NAME"; 
		return jdbcTemplate.queryForList( sql );
	}
}


