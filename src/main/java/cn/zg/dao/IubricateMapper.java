package cn.zg.dao;

import java.util.List;
import java.util.Map;

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
public interface IubricateMapper {
	
	/**
	 * 获取已完成的润滑数据
	 * @param schemeId
	 * @param recordDay 时间
	 * @return
	 */
	public List<Map<String,Object>> selectDayBycontent( String schemeId,String recordDay );
	/**
	 * 获取已完成的润滑数据
	 * @param schemeId
	 * @param recordDay 时间
	 * @return
	 */
	public List<Map<String,Object>> selectDayByIubricat( String schemeId,String recordDay );
	
	/**
	 * 查询所有油品
	 * @param year 年份
	 * @return
	 */
	public List<Map<String,Object>> selectLubeList();

	/**
	 * 获取润滑月度记录
	 * @param year 年份
	 * @return
	 */
	public List<Map<String,Object>> selectMonthlyResult( String year);

	
	
	
	/**
	 * 获取设备润滑油记录表
	 * @param schemeId
	 * @param recordDay 时间
	 * @return
	 */
	public List<Map<String,Object>> selectEquLubricatio( String eqbit,String eqname,String beginTime,String finishTime );

}
