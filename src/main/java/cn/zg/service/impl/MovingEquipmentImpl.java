
/**
 * <一句话功能描述>
 * <p>
 * @author 陈宇林
 * @version [版本号, 2019年4月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.impl;

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
	public Collection<Map<String, String>> getMovingEquipmentDataByCondition(QueryCondition condition) throws Exception {

		System.out.println(condition.getSchemeRemark().replaceAll(" ", "-"));

		List<MovingEquipmentValue> result = movingEquipmentValueMapper.findByCondition(condition);

		System.out.println(result);

		Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
		for (MovingEquipmentValue value : result) {
			Map<String, String> data = dataMap.get(value.getPositionNum());
			if (data == null) {
				data = new HashMap<String, String>();
			}

			data.put("positionNum", value.getPositionNum());

			data.put("equName", value.getEquName());

			data.put(value.getRequireId(), value.getParaValue());
			dataMap.put(value.getPositionNum(), data);

		}


		return dataMap.values();
	}

}
