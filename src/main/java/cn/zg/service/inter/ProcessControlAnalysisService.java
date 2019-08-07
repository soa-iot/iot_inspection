
/**
 * <一句话功能描述>
 * <p>过程控制与质量分析业务层
 * @author 陈宇林
 * @version [版本号, 2019年7月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.inter;

import java.util.List;
import java.util.Map;

import cn.zg.entity.daoEntity.ProcessControlParaConfig;
import cn.zg.entity.serviceEntity.QueryCondition;

public interface ProcessControlAnalysisService {

	/**
	 * 根据条件获取记录
	 * 
	 * @param condition
	 * @return
	 */
	List<Map<String, Object>> getRecordByCondition(QueryCondition condition);

	/**
	 * 删除数据
	 * 
	 * @param data
	 * @return
	 */
	Integer delRecord(List<Map<String, String>> data);

	/**
	 * @param data
	 * @return
	 */
	Integer addUpdateRecord(List<Map<String, String>> data);

	/**
	 * @param condition
	 * @return
	 */
	List<ProcessControlParaConfig> getParaConfig(QueryCondition condition);

}
