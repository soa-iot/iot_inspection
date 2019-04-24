
/**
 * <一句话功能描述>
 * <p>
 * @author 陈宇林
 * @version [版本号, 2019年4月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.inter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.MovingEquipmentConfig;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.entity.serviceEntity.ResponseEntity;

@Service
public interface MovingEquipmentInter {
	
	public ResponseEntity<List<MovingEquipmentConfig>> getHeaderInfo(QueryCondition condition);

	/**
	 * 根据条件获取动设备巡检记录
	 * @param condition
	 * @return
	 * @throws Exception 
	 */
	public Collection<Map<String, String>> getMovingEquipmentDataByCondition(QueryCondition condition) throws Exception;

}
