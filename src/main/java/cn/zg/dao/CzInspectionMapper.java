package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.Point;

/**
 * @ClassName: ValueJSBMapper
 * @Description:  查询维修方案-静设备、工艺管道dao层
 * @author zhugang
 * @date 2019年4月18日
 */
@Mapper
public interface CzInspectionMapper {
	
	public Integer insertPoint(@Param("point")Point point);
	
	public List<Point> selectTaskId(String planName);

}
