
/**
 * <一句话功能描述>
 * <p>过程控制与质量分析业务层实现类
 * @author 陈宇林
 * @version [版本号, 2019年7月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import cn.zg.dao.ProcessControlParaConfigMapper;
import cn.zg.dao.ProcessControlParaValueMapper;
import cn.zg.entity.daoEntity.ProcessControlParaConfig;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.service.inter.ProcessControlAnalysisService;

@Service
public  class ProcessControlAnalysisServiceImpl implements ProcessControlAnalysisService {

	@Autowired
	private ProcessControlParaValueMapper processControlParaValueMapper;

	@Autowired
	private ProcessControlParaConfigMapper processControlParaConfigMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.zg.service.inter.ProcessControlAnalysisService#getRecordByCondition(cn.zg.
	 * entity.serviceEntity.QueryCondition)
	 */
	@Override
	public List<Map<String, Object>> getRecordByCondition(QueryCondition condition) {

		List<Map<String, Object>> result = processControlParaValueMapper.selectByCondition(condition);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.zg.service.inter.ProcessControlAnalysisService#delRecord(java.util.List)
	 */
	@Override
	public Integer delRecord(List<Map<String, String>> data) {

		Integer result = processControlParaValueMapper.deleteRecord(data);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.zg.service.inter.ProcessControlAnalysisService#addUpdateRecord(java.util.
	 * List)
	 */
	@Override
	@Transactional
	public Integer addUpdateRecord(List<Map<String, String>> data) {

		Integer result = processControlParaValueMapper.deleteRecord(data);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>删除数据:" + result);
		result = processControlParaValueMapper.addRecore(data);
		return result;
	}

	/* (non-Javadoc)
	 * @see cn.zg.service.inter.ProcessControlAnalysisService#getParaConfig(cn.zg.entity.serviceEntity.QueryCondition)
	 */
	@Override
	public List<ProcessControlParaConfig> getParaConfig(QueryCondition condition) {
		
		List<ProcessControlParaConfig> result = processControlParaConfigMapper.selectByCondition(condition);
		
		return result;
	}

}
