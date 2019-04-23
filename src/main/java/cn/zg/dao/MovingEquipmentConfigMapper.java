package cn.zg.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.daoEntity.MovingEquipmentConfig;
import cn.zg.entity.serviceEntity.QueryCondition;

@Mapper
public interface MovingEquipmentConfigMapper {
    int insert(MovingEquipmentConfig record);

    int insertSelective(MovingEquipmentConfig record);
    
    /**
     * 根据条件查询
     */
    
    List<MovingEquipmentConfig> findByCondition(QueryCondition condition);
}