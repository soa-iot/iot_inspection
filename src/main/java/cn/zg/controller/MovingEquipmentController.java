
/**
 * <一句话功能描述>
 * <p>动设备相关实体类
 * @author 陈宇林
 * @version [版本号, 2019年4月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.zg.entity.daoEntity.MovingEquipmentConfig;
import cn.zg.entity.daoEntity.MovingEquipmentValue;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.entity.serviceEntity.ResponseEntity;
import cn.zg.service.inter.MovingEquipmentInter;

@RestController
@RequestMapping("/movingEquipment")
public class MovingEquipmentController {

	@Autowired
	private MovingEquipmentInter movingEquipmentInter;

	/**
	 * 获取动设备报表表头信息
	 * 
	 * @param schemeType
	 *            方案类型
	 * @return
	 */
	@RequestMapping("/getHeaderInfo")
	public ResponseEntity<List<MovingEquipmentConfig>> getHeaderInfo(
			@RequestParam(value = "schemeType", required = true) String schemeType) {

		QueryCondition condition = new QueryCondition();
		condition.setSchemeType(schemeType);

		ResponseEntity<List<MovingEquipmentConfig>> resObj = movingEquipmentInter.getHeaderInfo(condition);

		resObj.setCode(1);
		resObj.setMsg("success");

		return resObj;

	}

	/**
	 * 根据条件获取动设备巡检记录
	 * 
	 * @param date
	 *            日期
	 * @param schemeMark
	 *            方案标志
	 * @return
	 */
	@RequestMapping("/getMovingEquipmentData")
	public ResponseEntity<Collection<Map<String, String>>> getMovingEquipmentData(
			@RequestParam(value = "schemeRemark", required = true) String schemeRemark,
			@RequestParam(value = "taskInstId", required = true) String taskInstId) {

		/**
		 * 构造请求条件
		 */
		QueryCondition condition = new QueryCondition();
		condition.setSchemeRemark(schemeRemark.trim());
		condition.setSchemeType(schemeRemark);
		condition.setTaskInstId(taskInstId.trim());

		System.out.println(condition);
		ResponseEntity<Collection<Map<String, String>>> resObj;
		try {
			Collection<Map<String, String>> result = movingEquipmentInter.getMovingEquipmentDataByCondition(condition);

			resObj = new ResponseEntity<Collection<Map<String, String>>>(0, "success", result.size(), result);
		} catch (Exception e) {
			e.printStackTrace();
			resObj = new ResponseEntity<Collection<Map<String, String>>>(1, "failure >>" + e.getMessage(), 0, null);
		}

		System.out.println(resObj);
		return resObj;
	}

	/**
	 * 根据方案标志获取数据可选日期
	 * 
	 * @param schemeRemark
	 * @return
	 */
	@RequestMapping("/getDatesOfData")
	public ResponseEntity<List<MovingEquipmentValue>> getDatesOfData(
			@RequestParam(value = "schemeRemark", required = true) String schemeRemark) {

		/**
		 * 构造请求条件
		 */
		QueryCondition condition = new QueryCondition();
		condition.setSchemeRemark(schemeRemark.trim());
		List<MovingEquipmentValue> dates = movingEquipmentInter.getDatesOfData(condition);
		ResponseEntity<List<MovingEquipmentValue>> resObj = new ResponseEntity<List<MovingEquipmentValue>>(0, "success",
				dates.size(), dates);
		return resObj;

	}

	/**
	 * 根据方案标识和时间，获取任务次数
	 * 
	 * @param schemeRemark
	 * @param date
	 * @return
	 */
	@RequestMapping("/getTaskInstIds")
	public ResponseEntity<List<String>> getTaskInstIds(
			@RequestParam(value = "schemeRemark", required = true) String schemeRemark,
			@RequestParam(value = "date", required = true) String date) {
		QueryCondition condition = new QueryCondition();
		condition.setRecordDate(date);
		condition.setSchemeRemark(schemeRemark);

		List<String> taskInstIds = movingEquipmentInter.getTaskIdsByCondition(condition);

		ResponseEntity<List<String>> resObj = new ResponseEntity<List<String>>(0, "success", taskInstIds.size(),
				taskInstIds);
		return resObj;

	}

}
