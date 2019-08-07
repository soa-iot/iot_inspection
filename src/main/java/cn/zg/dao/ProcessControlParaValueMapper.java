package cn.zg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.ProcessControlParaValue;
import cn.zg.entity.serviceEntity.QueryCondition;

@Mapper
public interface ProcessControlParaValueMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessControlParaValue record);

    int insertSelective(ProcessControlParaValue record);

    ProcessControlParaValue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProcessControlParaValue record);

    int updateByPrimaryKey(ProcessControlParaValue record);
    
    /**
     * 根据条件查询数据
     * @param condition
     * @return
     */
    List<Map<String,Object>> selectByCondition(QueryCondition condition);

	/**
	 * @param data
	 * @return
	 */
	Integer deleteRecord(List<Map<String, String>> data);

	/**
	 * @param data
	 * @return
	 */
	Integer addRecore(List<Map<String, String>> data);
}