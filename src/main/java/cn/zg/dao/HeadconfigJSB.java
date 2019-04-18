package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.HeadConfigJSB;

@Mapper
public interface HeadconfigJSB {
	
	/**   
	 * @Title: findAll   
	 * @Description:  查询维修方案-静设备、工艺管道 表头
	 * @return: List<HeadConfigJSB>        
	 */  
	public List<HeadConfigJSB> findAll( String planId );
}
