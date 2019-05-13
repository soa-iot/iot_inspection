
/**
 * <一句话功能描述>
 * <p>
 * @author 陈宇林
 * @version [版本号, 2019年4月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.MovingEquipmentConfigMapper;
import cn.zg.dao.MovingEquipmentValueMapper;
import cn.zg.entity.daoEntity.MovingEquipmentConfig;
import cn.zg.entity.daoEntity.MovingEquipmentValue;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.entity.serviceEntity.ResponseEntity;
import cn.zg.service.inter.MovingEquipmentInter;

@Service
public class MovingEquipmentImpl implements MovingEquipmentInter {

	@Autowired
	private MovingEquipmentConfigMapper movingEquipmentConfigMapper;

	@Autowired
	private MovingEquipmentValueMapper movingEquipmentValueMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.zg.service.inter.MovingEquipmentInter#getHeaderInfo()
	 */
	@Override
	public ResponseEntity<List<MovingEquipmentConfig>> getHeaderInfo(QueryCondition condition) {

		List<MovingEquipmentConfig> result = movingEquipmentConfigMapper.findByCondition(condition);

		ResponseEntity<List<MovingEquipmentConfig>> res = new ResponseEntity<>();

		res.setData(result);

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.zg.service.inter.MovingEquipmentInter#getMovingEquipmentDataByCondition(cn
	 * .zg.entity.serviceEntity.QueryCondition)
	 */
	@Override
	public Collection<Map<String, String>> getMovingEquipmentDataByCondition(QueryCondition condition)
			throws Exception {

		List<MovingEquipmentValue> result = movingEquipmentValueMapper.findByCondition(condition);

		// System.out.println(result);

		List<MovingEquipmentConfig> headInfos = movingEquipmentConfigMapper.findByCondition(condition);

		/**
		 * 参数值-filed Map
		 */
		Map<String, String> fieldMap = new HashMap<String, String>();
		for (MovingEquipmentConfig headInfo : headInfos) {
			fieldMap.put(headInfo.getTitle(), headInfo.getField());
		}

		System.out.println(fieldMap);

		Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
		for (MovingEquipmentValue value : result) {
			Map<String, String> data = dataMap.get(value.getPositionNum() + value.getTaskInstId());
			if (data == null) {
				data = new HashMap<String, String>();
			}

			data.put("positionNum", value.getPositionNum());

			data.put("equName", value.getEquName());

			String paraName = value.getParaName();

			// System.out.println(paraName);
			if ("在用设备".equals(paraName)) {

				List<String> paraValues = Arrays.asList(value.getParaValue().split(","));
				for (String paraValue : paraValues) {
					// System.out.println(paraValue);
					// System.out.println(fieldMap.get(paraValue) + ">>>>>>>>>>>>>>>>");
					data.put(fieldMap.get(paraValue), "√");
					dataMap.put(value.getPositionNum() + value.getTaskInstId(), data);
				}
				continue;
			} else if ("振动".equals(paraName)) {
				data.put(fieldMap.get("实测值"), value.getParaValue());
				data.put(fieldMap.get("标准值"), value.getStandValue());
				dataMap.put(value.getPositionNum() + value.getTaskInstId(), data);
				continue;
			}

			System.out.println(paraName.substring(paraName.indexOf("-") + 1));
			data.put(fieldMap.get(paraName.substring(paraName.indexOf("-") + 1)), value.getParaValue());
			dataMap.put(value.getPositionNum() + value.getTaskInstId(), data);
		}

		System.out.println(dataMap.values());
		return dataMap.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.zg.service.inter.MovingEquipmentInter#getDatesOfData(cn.zg.entity.
	 * serviceEntity.QueryCondition)
	 */
	@Override
	public List<MovingEquipmentValue> getDatesOfData(QueryCondition condition) {

		List<MovingEquipmentValue> result = movingEquipmentValueMapper.findDatesByCondition(condition);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.zg.service.inter.MovingEquipmentInter#getTaskIdsByCondition(cn.zg.entity.
	 * serviceEntity.QueryCondition)
	 */
	@Override
	public List<String> getTaskIdsByCondition(QueryCondition condition) {

		List<MovingEquipmentValue> result = movingEquipmentValueMapper.findTaskInstIds(condition);
		System.out.println(result);
		List<String> taskInstIds = new ArrayList<String>();
		for (MovingEquipmentValue movingEquipmentValue : result) {
			taskInstIds.add(movingEquipmentValue.getTaskInstId());
		}

		return taskInstIds;
	}

}
