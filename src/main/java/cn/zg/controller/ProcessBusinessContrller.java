package cn.zg.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.MechatronicsProcessServiceImpl;
import cn.zg.service.inter.ProcessServiceInter;
/**
 * @ClassName: ProcessContrller
 * @Description: 流程控制层
 * @author zhugang
 * @date 2018年10月11日
 */
@RestController
@RequestMapping( "/processBusiness" )
public class ProcessBusinessContrller {
	private static Logger logger = LoggerFactory.getLogger( ProcessBusinessContrller.class );
	
	@Autowired
	private MechatronicsProcessServiceImpl mechatronicsProcessServiceImpl;	
	
	@Autowired
	private ProcessServiceInter processServiceInter;
	
	/**   
	 * @Title: saveProblemReport   
	 * @Description: 机电仪问题上报 
	 * @param: @return      
	 * @return: ResultJson<String>        
	 * @throws FileNotFoundException 
	 */  
	@RequestMapping( value = "/problemDealInfo/problemReport", 
			method = RequestMethod.POST )	
	public ResultJson<ProblemInspection> saveProblemReport(
			@RequestBody @Validated ProblemInspection problemInspection,
			@CookieValue( value="userName" , required=true ) String userName, 
			@CookieValue( value="userOrganization" , required=true ) String userOrganization,
			@CookieValue( value="userRole" , required=true ) String userRole,
			@CookieValue( value="nextNodeExecutor", required=false, defaultValue="" ) 
				String nextNodeExecutor) throws FileNotFoundException {
		logger.debug( "问题上报业务层……" +
			userName.toString() + "," +   
			userOrganization.toString() + "," + userRole.toString()
		);
		
		/*
		 * 保存流程上报信息
		 */
		ProblemInspection returnProblemInspection = 
				mechatronicsProcessServiceImpl.saveProblemInspect( problemInspection );
		
		/*
		 * 判断问题上报信息保存情况
		 */
		if( returnProblemInspection == null || 
				returnProblemInspection.getPiid() == null ) {
			return new ResultJson<ProblemInspection>( 1, "问题上报信息保存失败，未启动流程", null );
		}
		
		/*
		 * 判断用户所属，确定流程走向,启动流程
		 */
		String problemReportRole = "";
		if( "机电仪问题".equals( returnProblemInspection.getProblemType() ) ) {
			if( userRole.contains( "维修") ) {
				problemReportRole = "维修";
			}else if( !userRole.contains( "维修") && userRole.contains( "技干")  ) {
				problemReportRole = "非维修技干";
			}else if( !userRole.contains( "维修") && !userRole.contains( "技干")  ) {
				problemReportRole = "非维修非技干";
			}else {
				
			}
			logger.debug( "问题上报流程变量" + problemReportRole );
			//启动机电仪流程
			mechatronicsProcessServiceImpl.startMechatronicsProcess( 
					problemReportRole, returnProblemInspection, nextNodeExecutor );
		}else if( "净化检维修流程".equals( returnProblemInspection.getProblemClass() ) ){
			//启动净化检维修流程
			
		}else if( "其他检维修流程".equals( returnProblemInspection.getProblemClass() ) ){
			//启动其他检维修流程
			
		}else if( "隐患检维修流程".equals( returnProblemInspection.getProblemClass() ) ){
			//启动隐患检维修流程
			
		}else {
			
		}
		
		return new ResultJson<ProblemInspection>( returnProblemInspection );
	}
	
	/**   
	 * @Title: executorProblemEstimate   
	 * @Description: 问题评估处理  
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@PostMapping( "/problemDealInfo/problemEstimate" )
	public ResultJson<String> executorProblemEstimate(
			@RequestParam( "comment" ) String comment ){
		
		return null;
	}
	
	/**   
	 * @Title: getUnfinishedTask   
	 * @Description: 获取用户代办信息  - 机电仪流程
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@GetMapping( "/runtimeTask/{userName}" )
	public ResultJson<List<ProblemInspection>> getUnfinishedMechatronicsTask(
			@PathVariable( "userName" ) String userName ) {
		//去掉名字的双引号
		userName = userName.substring( 1, userName.length() -1 );
		logger.debug( "获取用户代办信息   …userName:" + userName );
		List<ProblemInspection> problemInspectionList = mechatronicsProcessServiceImpl
				.getUnfinishedMechatronicsTask( userName );
		return 
			new ResultJson<List<ProblemInspection>>( 0, "代办任务查询成功", problemInspectionList );
	}
	
	/**   
	 * @Title: getHistoryTask   
	 * @Description: 获取用户历史信息  
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@GetMapping( "/historyTask/{userName}" )
	public ResultJson<List<ProblemInspection>> getHistoryTask(
			@PathVariable( "userName" ) String userName,
			@RequestParam( value = "problemTypeStr", required=true)
			@NotBlank( message = "不能为空" ) String problemTypeStr) {
		//去掉名字的双引号
		userName = userName.substring( 1, userName.length() -1 );
		logger.debug( "获取历史信息   …userName:" + userName );
		List<ProblemInspection> problemInspections = 
				processServiceInter.getUserHistoryTask( userName, problemTypeStr );
		logger.debug( "获取历史信息   …problemInspections长度:" + problemInspections.size() );
		return 
			new ResultJson<List<ProblemInspection>>( 0, "查询历史上报问题成功", problemInspections);
	}
	
	/**   
	 * @Title: getHistoryTask   
	 * @Description: 查询问题处理的节点html名  
	 * @param: @return      
	 * @return: ResultJson<List<ProblemInspection>>        
	 */  
	@GetMapping( "/problemDealUrl/{currentPrid}" )
	public ResultJson<String> getDealUrlNameController(
			@PathVariable( "currentPrid" ) 
			@NotBlank( message = "不能为空" ) String currentPrid ){
		logger.debug( "查询问题处理的节点html名  …currentPrid:" + currentPrid );
		String idName = 
				mechatronicsProcessServiceImpl.getDealUrlNameService( currentPrid );	
		if( idName != null) {
			return new ResultJson<String>( 0, "查询成功", idName );
		}
		return new ResultJson<String>( 1, "查询失败", null );
	}
	
	@GetMapping( "/problemDealInfo/problemEstimate/problemReportInfo/{currentDealPiid}" )
	public ResultJson<ProblemInspection> getProblemReportInfo(
		@PathVariable( "currentDealPiid" ) String currentDealPiid ){
		
		return null;
	}
}
