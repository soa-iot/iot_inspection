package cn.zg.controller;

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

import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.PurificationSchemeInter;

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
	
	@GetMapping("/tableData")
	public ResultJson<List<Map<String,String>>> getTableData (
			@RequestParam( "time" ) String time,
			@RequestParam( "currentScheme" ) String currentScheme) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> m = new HashMap<String,String>();
		m.put( "timeName", "白班" );
		m.put( "time", "9:00" );
		m.put( "111-PG-003", "1111" );
		m.put( "111-PG-003", "1111" );
		m.put( "111-LT-101", "22222" );
		m.put( "111-LT-101", "22222" );
		m.put( "111-LIT-120A", "22222" );
		m.put( "111-LIT-120A", "22222" );
		m.put( "111-LIT-121A", "22222" );
		m.put( "111-LIT-121A", "22222" );
		m.put( "121-LT-103A", "22222" );
		m.put( "121-LT-103A", "22222" );
		m.put( "121-LT-116", "22222" );
		m.put( "121-LT-116", "22222" );
		m.put( "121-PG-018", "22222" );
		m.put( "121-PG-018", "22222" );
		m.put( "121-PG-016", "22222" );
		m.put( "121-PG-016", "22222" );
		m.put( "121-PG-026", "22222" );
		m.put( "121-PG-026", "22222" );
		m.put( "121-LT-108", "22222" );
		m.put( "121-LT-108", "22222" );
		m.put( "121-PG-031", "22222" );
		m.put( "121-PG-031", "22222" );
		m.put( "121-LT-111", "22222" );
		m.put( "121-LT-111", "22222" );
		m.put( "121-LT-110A", "22222" );
		m.put( "121-LT-110A", "22222" );
		m.put( "121-LT-104", "22222" );
		m.put( "121-LT-104", "22222" );
		list.add( m );
		
		Map<String,String> m1 = new HashMap<String,String>();
		m1.put( "timeName", "白班" );
		m1.put( "time", "11:00" );
		m1.put( "111-PG-003", "1111" );
		m1.put( "111-PG-003", "1111" );
		m1.put( "111-LT-101", "22222" );
		m1.put( "111-LT-101", "22222" );
		m1.put( "111-LIT-120A", "22222" );
		m1.put( "111-LIT-120A", "22222" );
		m1.put( "111-LIT-121A", "22222" );
		m1.put( "111-LIT-121A", "22222" );
		m1.put( "121-LT-103A", "22222" );
		m1.put( "121-LT-103A", "22222" );
		m1.put( "121-LT-116", "22222" );
		m1.put( "121-LT-116", "22222" );
		m1.put( "121-PG-018", "22222" );
		m1.put( "121-PG-018", "22222" );
		m1.put( "121-PG-016", "22222" );
		m1.put( "121-PG-016", "22222" );
		m1.put( "121-PG-026", "22222" );
		m1.put( "121-PG-026", "22222" );
		m1.put( "121-LT-108", "22222" );
		m1.put( "121-LT-108", "22222" );
		m1.put( "121-PG-031", "22222" );
		m1.put( "121-PG-031", "22222" );
		m1.put( "121-LT-111", "22222" );
		m1.put( "121-LT-111", "22222" );
		m1.put( "121-LT-110A", "22222" );
		m1.put( "121-LT-110A", "22222" );
		m1.put( "121-LT-104", "22222" );
		m1.put( "121-LT-104", "22222" );
		list.add( m1 );
		
		Map<String,String> m2 = new HashMap<String,String>();
		m2.put( "timeName", "夜班" );
		m2.put( "time", "21:00" );
		m2.put( "111-PG-003", "1111" );
		m2.put( "111-PG-003", "1111" );
		m2.put( "111-LT-101", "22222" );
		m2.put( "111-LT-101", "22222" );
		m2.put( "111-LIT-120A", "22222" );
		m2.put( "111-LIT-120A", "22222" );
		m2.put( "111-LIT-121A", "22222" );
		m2.put( "111-LIT-121A", "22222" );
		m2.put( "121-LT-103A", "22222" );
		m2.put( "121-LT-103A", "22222" );
		m2.put( "121-LT-116", "22222" );
		m2.put( "121-LT-116", "22222" );
		m2.put( "121-PG-018", "22222" );
		m2.put( "121-PG-018", "22222" );
		m2.put( "121-PG-016", "22222" );
		m2.put( "121-PG-016", "22222" );
		m2.put( "121-PG-026", "22222" );
		m2.put( "121-PG-026", "22222" );
		m2.put( "121-LT-108", "22222" );
		m2.put( "121-LT-108", "22222" );
		m2.put( "121-PG-031", "22222" );
		m2.put( "121-PG-031", "22222" );
		m2.put( "121-LT-111", "22222" );
		m2.put( "121-LT-111", "22222" );
		m2.put( "121-LT-110A", "22222" );
		m2.put( "121-LT-110A", "22222" );
		m2.put( "121-LT-104", "22222" );
		m2.put( "121-LT-104", "22222" );
		list.add( m2 );
		System.out.println(list);
		logger.debug( "控制层请求的表头信息" , list );
		return new ResultJson<List<Map<String,String>>>( list );
	}
	
}
