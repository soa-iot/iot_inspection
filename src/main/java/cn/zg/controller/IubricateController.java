package cn.zg.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.dao.IubricateMapper;
import cn.zg.entity.daoEntity.MeterInspectionResult;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.entity.serviceEntity.ResponseEntity;
import cn.zg.service.impl.AnalysisSI;
import cn.zg.service.inter.AnalysisS;
import cn.zg.service.inter.PurificationSchemeInter;
import cn.zg.service.inter.RepairJSBS;

/**
 * @ClassName: AnalysisC
 * @Description: 维修方案-静设备、工艺管道控制层
 * @author
 * @date 2019年9月25日
 */
@RestController
@RequestMapping("/ibricate")
public class IubricateController {
	private static Logger logger = LoggerFactory.getLogger(IubricateController.class);

	@Autowired
	private IubricateMapper iubricate;

	/**
	 * 获取润滑巡检数据
	 * 
	 * @param schemeName
	 * @param recordDay
	 * @return
	 */
	@RequestMapping("/getIubricateResult")
	public ResponseEntity<List<Map<String, Object>>> getMeterInspectionResult(
			@RequestParam(name = "schemeId", required = true) String schemeId,
			@RequestParam(name = "recordDay", required = true) String recordDay) {

		List<Map<String, Object>> lis1 = new ArrayList<Map<String, Object>>();
		ResponseEntity<List<Map<String, Object>>> resObj = new ResponseEntity<List<Map<String, Object>>>();

		try {

			List<Map<String, Object>> result1 = iubricate.selectDayBycontent(schemeId, recordDay);
			List<Map<String, Object>> result2 = iubricate.selectDayByIubricat(schemeId, recordDay);

			for (Map<String, Object> mapq1 : result1) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("TASK_CONTENT_NAME", mapq1.get("TASK_CONTENT_NAME"));
				map1.put("EQU_NAME", mapq1.get("EQU_NAME"));

				for (Map<String, Object> mapq2 : result2) {
					if (mapq1.get("TASK_CONTENT_ID").equals(mapq2.get("TASK_CONTENT_ID"))) {
						String val = "";
						try {
							val = mapq2.get("VALUE").toString();
						} catch (Exception e) {
							val = "/";
						}
						map1.put(mapq2.get("TASK_REQUIRE_CONTEXT").toString(), val);
					}
				}

				String text = "";
				try {

					if (!"null".equals(mapq1.get("DISPORE").toString()))
						text += (String) mapq1.get("DISPORE") + " : ";

					if (!"null".equals(mapq1.get("OILS").toString()) && !"0".equals(mapq1.get("OILS").toString()))
						text += (String) mapq1.get("OILS") + " : ";

					if (!"null".equals(mapq1.get("VALUE").toString()))
						text += (String) mapq1.get("VALUE");
				} catch (Exception e) {
					// TODO: handle exception
				}
				map1.put("TEXT", text + "");
				lis1.add(map1);
			}

			resObj.setCode(0);
			resObj.setCount(lis1.size());
			resObj.setData(lis1);
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

	/**
	 * 获取润滑月度记录
	 * 
	 * @param year 年份
	 * @return
	 */
	@RequestMapping("/getMonthlyResult")
	public ResponseEntity<List<Map<String, Object>>> getMonthlyResult(
			@RequestParam(name = "year", required = true) String year) {

		List<Map<String, Object>> lis1 = new ArrayList<Map<String, Object>>();
		ResponseEntity<List<Map<String, Object>>> resObj = new ResponseEntity<List<Map<String, Object>>>();
		Map<String, String> mapMonet = new HashMap<String, String>();
		mapMonet.put("Jan", "01");
		mapMonet.put("Feb", "02");
		mapMonet.put("Mar", "03");
		mapMonet.put("Apr", "04");
		mapMonet.put("May", "05");
		mapMonet.put("Jun", "06");
		mapMonet.put("Jul", "07");
		mapMonet.put("Aug", "08");
		mapMonet.put("Sep", "09");
		mapMonet.put("Oct", "10");
		mapMonet.put("Nov", "11");
		mapMonet.put("Dec", "12");

		try {
			List<Map<String, Object>> lubeList = iubricate.selectLubeList();
			logger.info(lubeList.toString());
//			List<Map<String, Object>> MonthList = iubricate.selectMonthlyResult(year);
//			logger.info(MonthList.toString());
			List<Map<String, Object>> MonthhList = iubricate.selectMonthlhResult(year);
			logger.info(MonthhList.toString());
			for (Map<String, Object> lube : lubeList) {
				Map<String, Object> lubes = new HashMap<String, Object>();
				lubes.put("name", lube.get("ONAME"));
				for (Map.Entry<String, String> entry : mapMonet.entrySet()) {
					String mapKey = entry.getKey();
					String mapValue = entry.getValue();
					int reg = 0;
					//巡检换油记录
//					for (Map<String, Object> Month : MonthList) {
//						if (lube.get("ONAME").equals(Month.get("OILS"))
//								&& Month.get("TIMES").equals(year + "-" + mapValue)) {
//							reg = Integer.parseInt(Month.get("AMOUNT").toString()) ;
//						}
//					}
					//换油记录
					for (Map<String, Object> Months : MonthhList) {
						if (lube.get("ONAME").equals(Months.get("ONAME"))
								&& Months.get("TIMES").equals(year + "-" + mapValue)) {
							reg = reg+Integer.parseInt(Months.get("AMOUNT").toString()) ;
						}
					}
					
					lubes.put(mapKey, ""+reg+lube.get("OUNIT").toString());
				}
				lis1.add(lubes);
			}
			logger.info(lis1.toString());
			resObj.setCode(0);
			resObj.setCount(lis1.size());
			resObj.setData(lis1);
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

	/**
	 * 获取设备润滑油记录表
	 * 
	 * @param schemeName
	 * @param recordDay
	 * @return
	 */
	@RequestMapping("/getEquLubrication")
	public ResponseEntity<List<Map<String, Object>>> getEquLubrication(
			@RequestParam(name = "eqbit", required = true) String eqbit,
			@RequestParam(name = "eqname", required = true) String eqname,
			@RequestParam(name = "beginTime", required = true) String beginTime,
			@RequestParam(name = "finishTime", required = true) String finishTime) {
		ResponseEntity<List<Map<String, Object>>> resObj = new ResponseEntity<List<Map<String, Object>>>();

		try {
			List<Map<String, Object>> result1 = iubricate.selectEquLubricatio(eqbit, eqname, beginTime, finishTime);
			logger.info(result1.toString());

			resObj.setCode(0);
			resObj.setCount(result1.size());
			resObj.setData(result1);
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
