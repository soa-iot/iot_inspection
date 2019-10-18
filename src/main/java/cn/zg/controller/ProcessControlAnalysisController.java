
/**
 * <一句话功能描述>
 * <p>过程控制与质量分析报表处理类控制层
 * @author 陈宇林
 * @version [版本号, 2019年7月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.ProcessControlParaConfig;
import cn.zg.entity.serviceEntity.QueryCondition;
import cn.zg.entity.serviceEntity.ResponseEntity;
import cn.zg.service.inter.ProcessControlAnalysisService;

@RestController
@RequestMapping("/processControlAnalysis")
public class ProcessControlAnalysisController {

	@Autowired
	private ProcessControlAnalysisService processControlAnalysisService;

	/**
	 * 获取质量分析与过程控制分析的数据
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param paraType
	 *            报表类型： 1- 净化气 2- 闪蒸气 3- 硫磺 4- 溶液 5- 酸气 6- 尾气 7- 锅炉炉水 8- 污水 9- 循环水
	 *            10- 废锅水质
	 * @param paraId(可选)
	 * 
	 * @param queryType
	 *            请求类型： 1- 列表数据请求 2-趋势图数据请求
	 * 
	 * @return
	 */
	@RequestMapping("/getRecord")
	public ResponseEntity<List<Map<String, Object>>> getRecord(@RequestParam(name = "beginDate") String beginDate,
			@RequestParam(name = "endDate") String endDate, @RequestParam(name = "paraType") String paraType,
			@RequestParam(name = "paraId", required = false) String paraId,
			@RequestParam(name = "queryType") String queryType) {

		QueryCondition condition = new QueryCondition();
		condition.setBeginDate(beginDate);
		condition.setEndDate(endDate);
		condition.setParaType(paraType);
		condition.setParaId(paraId);
		condition.setQueryType(queryType);
		System.out.println(condition);

		ResponseEntity<List<Map<String, Object>>> resObj = new ResponseEntity<List<Map<String, Object>>>();

		try {
			List<Map<String, Object>> result = processControlAnalysisService.getRecordByCondition(condition);
			resObj.setCode(0);
			resObj.setCount(result.size());
			resObj.setData(result);
			resObj.setMsg("query data success");
		} catch (Exception e) {
			resObj.setCode(1);
			resObj.setCount(0);
			resObj.setData(null);
			resObj.setMsg("query data field>>>>>>" + e.getMessage());
			e.printStackTrace();
		}

		return resObj;
	}

	/**
	 * 删除记录
	 * 
	 * @param data
	 *            传入需要删除的数据
	 * @return 删除的数据条数
	 */
	@RequestMapping(value = "/delRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> delRecord(@RequestBody List<Map<String, String>> data) {

		ResponseEntity<String> resObj = new ResponseEntity<>();

		try {
			Integer result = processControlAnalysisService.delRecord(data);
			resObj.setCode(0);
			resObj.setCount(result);
			resObj.setData(result + "");
			resObj.setMsg("delete data success,count:" + result);
		} catch (Exception e) {
			resObj.setCode(1);
			resObj.setCount(0);
			resObj.setMsg("delete data failed >>>" + e.getMessage());
		}
		return resObj;
	}

	/**
	 * 新增或修改数据（先删除，后更新）
	 * 
	 * @param data
	 *            传入的需要新增或修改的数据，数据格式为json数组
	 * @return 更新的数据条数
	 */
	@RequestMapping(value = "/addUpdateRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> addUpdateRecord(@RequestBody List<Map<String, String>> data) {
		System.out.println(data);
		ResponseEntity<String> resObj = new ResponseEntity<String>();

		try {
			Integer result = processControlAnalysisService.addUpdateRecord(data);
			resObj.setCode(0);
			resObj.setCount(result);
			resObj.setData(result + "");
		} catch (Exception e) {
			resObj.setCode(1);
			resObj.setCount(0);
			resObj.setMsg("save data failed >>>" + e.getMessage());
			e.printStackTrace();
		}

		return resObj;
	}

	/**
	 * 根据参数类型获取参数信息
	 * 
	 * @param paraType
	 *            报表类型： 1- 净化气 2- 闪蒸气 3- 硫磺 4- 溶液 5- 酸气 6- 尾气 7- 锅炉炉水 8- 污水 9- 循环水
	 *            10- 废锅水质
	 * @return
	 */
	@RequestMapping("/getParaConfig")
	public ResponseEntity<List<ProcessControlParaConfig>> getParaConfig(String paraType) {

		QueryCondition condition = new QueryCondition();
		condition.setParaType(paraType);

		ResponseEntity<List<ProcessControlParaConfig>> resObj = new ResponseEntity<List<ProcessControlParaConfig>>();

		try {
			List<ProcessControlParaConfig> result = processControlAnalysisService.getParaConfig(condition);
			resObj.setCode(0);
			resObj.setCount(result.size());
			resObj.setData(result);
			resObj.setMsg("query data success");
		} catch (Exception e) {
			resObj.setCode(1);
			resObj.setCount(0);
			resObj.setData(null);
			resObj.setMsg("query data failed >>>>>" + e.getMessage());
			e.printStackTrace();
		}

		return resObj;
	}
	
	

}
