package cn.zg.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.MovingEquipmentValue;
import cn.zg.entity.serviceEntity.QueryCondition;

@Mapper
public interface MovingEquipmentValueMapper {
    int insert(MovingEquipmentValue record);

    int insertSelective(MovingEquipmentValue record);
    
    /**
     * 根据条件查询数据
     * @return
     */
    List<MovingEquipmentValue> findByCondition(QueryCondition condition);
    
    /**
     * 根据条件获取实际数据条数
     */
    Map<String, BigDecimal> countByCondition(QueryCondition condition);
    
    
    
}