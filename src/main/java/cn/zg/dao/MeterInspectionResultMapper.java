package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.MeterInspectionResult;
import cn.zg.entity.serviceEntity.QueryCondition;

@Mapper
public interface MeterInspectionResultMapper {
	
	/**
	 * 根据方案名称和记录时间获取仪表巡检记录
	 * @param condition
	 * @return
	 */
	List<MeterInspectionResult> findRecordBySchemeAndDate(QueryCondition condition );
  
}