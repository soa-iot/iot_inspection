package cn.zg.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.AnalysisSI;
import cn.zg.service.inter.AnalysisS;
import cn.zg.service.inter.PurificationSchemeInter;
import cn.zg.service.inter.RepairJSBS;

/**
 * @ClassName: AnalysisC
 * @Description: 维修方案-静设备、工艺管道控制层
 * @author zhugang
 * @date 2019年4月11日
 */
@RestController
@RequestMapping("/repair")
public class RepairJSBC {
	private static Logger logger = LoggerFactory.getLogger( RepairJSBC.class );
	
	@Autowired
	private RepairJSBS repairS;
	
	/**   
	 * @Title: getPointTypeC   
	 * @Description:  查询维修方案-静设备、工艺管道 表头  
	 * @return: ResultJson<List<String>>        
	 */  
	@GetMapping("/headconfig")
	public ResultJson<List<Map<String, Object>>> getHeadC( 
			@RequestParam("planId") String planId ){
		logger.debug( "---------查询维修方案-静设备、工艺管道 表头 ------" );
		logger.debug( planId );
		List<Map<String, Object>> heads = repairS.getHeadJSB( planId );
		if( heads == null ) {
			return new ResultJson<List<Map<String, Object>>>( 1, "查询失败", null);
		}
		return new ResultJson<List<Map<String, Object>>>( 0, "查询成功", heads);
	}
	
}
