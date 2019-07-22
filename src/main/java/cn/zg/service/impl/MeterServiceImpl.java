
/**
 * <一句话功能描述>
 * <p>九龙山仪表巡检结果获取处理业务层实现
 * @author 陈宇林
 * @version [版本号, 2019年7月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.MeterInspectionResultMapper;
import cn.zg.entity.daoEntity.MeterInspectionResult;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.service.inter.MeterService;

@Service
public class MeterServiceImpl implements MeterService {

	
	@Autowired
	private MeterInspectionResultMapper meterIMapper;
	/* (non-Javadoc)
	 * @see cn.zg.service.inter.MeterService#getMeterInspectionResult(cn.zg.entity.serviceEntity.QueryCondition)
	 */
	@Override
	public List<MeterInspectionResult> getMeterInspectionResult(QueryCondition condition) {
		
		List<MeterInspectionResult> result = meterIMapper.findRecordBySchemeAndDate(condition);
		return result;
	}

}
