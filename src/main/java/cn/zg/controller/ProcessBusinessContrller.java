package cn.zg.controller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.MechatronicsProcessServiceImpl;
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
		System.out.println( "problemInspection:" + problemInspection);
		
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
		if( "机电仪检维修流程".equals( returnProblemInspection.getProblemClass() ) ) {
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
	 * @Title: getUnfinishedTask   
	 * @Description: 获取用户代办信息   
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@GetMapping( "/runtimeTask/{userName}" )
	public ResultJson<String> getUnfinishedTask() {
		
		return null;
	}
	
	/**   
	 * @Title: getHistoryTask   
	 * @Description: 获取用户历史信息  
	 * @param: @return      
	 * @return: ResultJson<String>        
	 */  
	@GetMapping( "/historyTask/{userName}" )
	public ResultJson<String> getHistoryTask() {
		
		return null;
	}
}
