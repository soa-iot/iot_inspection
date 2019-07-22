
/**
 * <一句话功能描述>
 * <p> 九龙山仪表类巡检记录控制层
 * @author 陈宇林
 * @version [版本号, 2019年7月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.MeterInspectionResult;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.entity.serviceEntity.ResponseEntity;
import cn.zg.service.inter.MeterService;

@RestController
@RequestMapping("/meter")
public class MeterController {

	@Autowired
	private MeterService service;

	/**
	 * 获取巡检数据
	 * 
	 * @param schemeName
	 * @param recordDay
	 * @return
	 */
	@RequestMapping("/getMeterInspectionResult")
	public ResponseEntity<List<MeterInspectionResult>> getMeterInspectionResult(
			@RequestParam(name = "schemeName", required = true) String schemeName,
			@RequestParam(name = "recordDay", required = true) String recordDay) {

		QueryCondition condition = new QueryCondition();
		condition.setRecordDate(recordDay);
		condition.setSchemeName(schemeName);
		System.out.println(condition);

		ResponseEntity<List<MeterInspectionResult>> resObj = new ResponseEntity<List<MeterInspectionResult>>();

		try {
			List<MeterInspectionResult> result = service.getMeterInspectionResult(condition);
			resObj.setCode(0);
			resObj.setCount(result.size());
			resObj.setData(result);
			resObj.setMsg("query data success");
		} catch (Exception e) {
			resObj.setCode(1);
			resObj.setCount(0);
			resObj.setData(null);
			resObj.setMsg("query data failed" + e.getMessage());
			e.printStackTrace();
		}

		return resObj;

	}

}
