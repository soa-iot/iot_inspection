package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.InspectionScheme;
import cn.zg.entity.serviceEntity.QueryCondition;

@Mapper
public interface InspectionSchemeMapper {
	int deleteByPrimaryKey(String schemeId);

	int insert(InspectionScheme record);

	int insertSelective(InspectionScheme record);

	InspectionScheme selectByPrimaryKey(String schemeId);

	int updateByPrimaryKeySelective(InspectionScheme record);

	int updateByPrimaryKey(InspectionScheme record);

	/**
	 * 根据条件查询方案信息
	 * 
	 * @param condition
	 * @return
	 */
	List<InspectionScheme> findByCondition(QueryCondition condition);
}