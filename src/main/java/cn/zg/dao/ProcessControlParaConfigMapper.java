package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.ProcessControlParaConfig;
import cn.zg.entity.serviceEntity.QueryCondition;

@Mapper
public interface ProcessControlParaConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessControlParaConfig record);

    int insertSelective(ProcessControlParaConfig record);

    ProcessControlParaConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProcessControlParaConfig record);

    int updateByPrimaryKey(ProcessControlParaConfig record);

	/**
	 * 根据条件查询数据
	 * @param condition
	 * @return
	 */
	List<ProcessControlParaConfig> selectByCondition(QueryCondition condition);
}