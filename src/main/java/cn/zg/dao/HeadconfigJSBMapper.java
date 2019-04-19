package cn.zg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.daoEntity.HeadConfigJSB;

@Mapper
public interface HeadconfigJSBMapper {
	
	/**   
	 * @Title: findAll   
	 * @Description:  查询维修方案-静设备、工艺管道 表头
	 * @return: List<HeadConfigJSB>        
	 */  
	public List<HeadConfigJSB> findAll(@Param("planId") String planId );
	
	/**   
	 * @Title: selectPlanId   
	 * @Description: 查询此表头配置表中所有的方案  
	 * @return: List<String>        
	 */  
	public List<Map<String, Object>> selectPlanId();
}
