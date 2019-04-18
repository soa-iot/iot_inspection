package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.HeadConfigJSB;
import cn.zg.entity.daoEntity.ValueJSB;

/**
 * @ClassName: ValueJSBMapper
 * @Description:  查询维修方案-静设备、工艺管道dao层
 * @author zhugang
 * @date 2019年4月18日
 */
@Mapper
public interface ValueJSBMapper {
	
	/**   
	 * @Title: findByPlanIdTime   
	 * @Description:  查询维修方案-静设备、工艺管道值  
	 * @return: List<ValueJSB>        
	 */  
	public List<ValueJSB> findByPlanIdTime(  String planId, String time  );
}
