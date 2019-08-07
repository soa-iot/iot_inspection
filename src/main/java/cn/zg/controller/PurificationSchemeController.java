package cn.zg.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.StatusRecord;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.PurificationSchemeInter;
import cn.zg.service.inter.StatusRecordS;

/**
 * 
 * @ClassName: PurificationSchemeController
 * @Description: 净化方案控制层
 * @author zhugang
 * @date 2018年9月18日
 */

@RestController
@RequestMapping("/productionReport/purificationScheme")
public class PurificationSchemeController {
	private static Logger logger = LoggerFactory.getLogger( PurificationSchemeService.class );
	
	@Autowired
	private PurificationSchemeInter psi;
	
	@Autowired
	private StatusRecordS statusRecordS;
	
	/**   
	 * @Title: getTableHead   
	 * @Description: 获得表头信息   
	 * @param: @param inspectionName      
	 * @return: void        
	 */  
	@RequestMapping("/tableHead")
	public ResultJson<List<Object>> getTableHead (
			@RequestParam( "currentScheme" ) String inspectionName ) {
		List<Object> list = psi.getTableHead( inspectionName );
		System.out.println(list);
		logger.debug( "控制层请求的表头信息" , list );
		return new ResultJson<List<Object>>( list );
	}
	
	/**   
	 * @Title: getTableData   
	 * @Description: 获取表格展示数据  
	 * @param: @param time
	 * @param: @param currentScheme
	 * @param: @return
	 * @param: @throws ParseException      
	 * @return: ResultJson<List<Map<String,Object>>>        
	 */  
	@GetMapping("/tableData")
	public ResultJson<List<Map<String,Object>>> getTableData (
			@RequestParam( "time" ) String time,
			@RequestParam( "currentScheme" ) String currentScheme) throws ParseException {
		logger.debug( "C-获取表格展示数据 -time-currentScheme" + time + "-" + currentScheme );		
		List<Map<String,Object>> list = psi.getInspectData( currentScheme, time );
		logger.debug( "C-获取表格展示数据 -list:" + list.toString() );		
		return new ResultJson<List<Map<String,Object>>>( list );
	}
	
	/**   
	 * @Title: getInspectionNames   
	 * @Description:   获取所有的方案信息
	 * @return: ResultJson<List<Map<String,Object>>>        
	 */  
	@GetMapping("/inspectionNames")
	public ResultJson<List<Map<String,Object>>> getInspectionNames(){
		logger.debug( "-C-------获取所有的方案信息-----------" );
		List<Map<String, Object>> lists = psi.getInspectioNames();
		logger.debug( "-C-------获取所有的方案信息-----------lists:" + lists );
		if( lists == null || lists.isEmpty() ) {
			return new ResultJson<List<Map<String,Object>>>( 1, "查询方案数据为空", null );
		}
		return new ResultJson<List<Map<String,Object>>>( 0, "查询方案数据成功", lists );
	}
	
	
	/**   
	 * @Title: getTableStatusC   
	 * @Description: 查看任务状态    
	 * @return: ResultJson<List<Map<String,Object>>>        
	 */  
	@GetMapping("/status")
	public ResultJson<List<StatusRecord>> getTableStatusC (
			@RequestParam( "time" ) String time,
			@RequestParam( "currentScheme" ) String currentScheme) throws ParseException {
		logger.debug( "C-查看任务状态   -time-currentScheme" + time + "-" + currentScheme );		
		List<StatusRecord> list = statusRecordS.findTaskStateByPlanidAndTime( currentScheme, time );
		logger.debug( "C-查看任务状态   -list:" + list.toString() );		
		return new ResultJson<List<StatusRecord>>( list );
	}
	

}
