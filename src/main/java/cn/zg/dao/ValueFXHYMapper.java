package cn.zg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.HeadConfigJSB;
import cn.zg.entity.daoEntity.ValueFXHY;
import cn.zg.entity.daoEntity.ValueJSB;

/**
 * @ClassName: ValueJSBMapper
 * @Description:  查询维修方案-静设备、工艺管道dao层
 * @author zhugang
 * @date 2019年4月18日
 */
@Mapper
public interface ValueFXHYMapper {
	
	/**   
	 * @Title: findByUnitPnameTime   
	 * @Description:  根据巡检单元、巡检项目、时间 查询方案数据 
	 * @return: List<ValueFXHY>        
	 */  
	public List<ValueFXHY> findByUnitPnameTime( String stime, String etime, String uname, String pname );
	
	/**   
	 * @Title: insert   
	 * @Description:  插入数据 
	 * @return: int        
	 */  
	public int insert( ValueFXHY v );
	
}
