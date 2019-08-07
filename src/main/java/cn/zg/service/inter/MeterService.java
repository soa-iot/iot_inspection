
/**
 * <一句话功能描述>
 * <p> 九龙山仪表巡检业务层
 * @author 陈宇林
 * @version [版本号, 2019年7月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.inter;

import java.util.List;

import cn.zg.entity.daoEntity.InspectionScheme;
import cn.zg.entity.daoEntity.MeterInspectionResult;
import cn.zg.entity.serviceEntity.QueryCondition;


public interface MeterService {

	/**
	 * 根据条件获取仪表巡检数据
	 * @param condition
	 */
	List<MeterInspectionResult> getMeterInspectionResult(QueryCondition condition);

	/**
	 * 获取方案数据
	 * @param condition
	 * @return
	 */
	List<InspectionScheme> getSchemeInfo(QueryCondition condition);

}
