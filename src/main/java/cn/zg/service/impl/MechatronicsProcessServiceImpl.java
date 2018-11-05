package cn.zg.service.impl;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.controller.ProcessBusinessContrller;
import cn.zg.dao.inter.ProcessInsectionRepository;
import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.inter.MechatronicsProcessServiceInter;
import cn.zg.service.inter.ProcessServiceInter;
import cn.zg.utils.globalUtils.GlobalUtil;

/**
 * @ClassName: ProcessInspectionServiceImpl
 * @Description: 流程业务层业务实现
 * @author zhugang
 * @date 2018年10月16日
 */
@Service
public class MechatronicsProcessServiceImpl implements MechatronicsProcessServiceInter {
	private static Logger logger = LoggerFactory.getLogger( MechatronicsProcessServiceImpl.class );
	
	@Autowired
	private ProcessInsectionRepository processInsectionRepository;
	
	@Autowired
	private ProcessServiceInter processServiceInter;
	
	@Override
	public ProblemInspection saveProblemInspect( ProblemInspection problemInspection ) {		
		/*
		 * 格式化、新增保存的数据
		 */
		//UUID
		problemInspection.setPiid( UUID.randomUUID().toString() );
		//TIME
		problemInspection.setReportTime( new Timestamp( System.currentTimeMillis() ) );		
		//设置流程进度
		problemInspection.setCurrentProgress( "问题上报" );
		logger.debug( "业务层保存流程业务表单，保存对象" + problemInspection.toString() );
		//
		
		/*
		 * 保存的数据
		 */
		ProblemInspection returnProblemInspection = 
				processInsectionRepository.save( problemInspection );
		logger.debug( "业务层保存流程业务表单，保存后返回对象" + problemInspection.toString() );
		return returnProblemInspection;
	}
	
	/**   
	 * <p>Title: startMechatronicsProcess</p>   
	 * <p>Description: </p>   启动机电仪流程
	 * @param problemReportRole
	 * @param problemInspection
	 * @param nextNodeExecutor   
	 * @see cn.zg.service.inter.MechatronicsProcessServiceInter#startMechatronicsProcess(java.lang.String, cn.zg.entity.daoEntity.ProblemInspection, java.lang.String)   
	 */ 
	public void startMechatronicsProcess( 
			String problemReportRole, ProblemInspection problemInspection,
			String nextNodeExecutor ){
		logger.debug( "机电仪问题流程启动……" + problemInspection );
		/*
		 * 启动机电仪流程
		 */		
		//部署流程
		Deployment deployment = processServiceInter.deployMechatronicsProcess();
		//获取流程定义
		ProcessDefinition processDefinition = 
				processServiceInter.getMechatronicsProcess( deployment );
		//获取，设置流程变量
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put( "problemReportRole", problemReportRole);
		if( "维修".equals( problemReportRole ) ) {
			nextNodeExecutor = problemInspection.getReporter();
			vars.put( "repairArrangeExecutor", nextNodeExecutor );
		}else if( "非维修技干".equals( problemReportRole ) ) {
			nextNodeExecutor = problemInspection.getReporter();
			vars.put( "pureArrangeExecutor", nextNodeExecutor );
		}else if( "非维修非技干".equals( problemReportRole ) ) {
			nextNodeExecutor = "";
			List<Emploee> employees = 
				processServiceInter.getMonitor( problemInspection.getReporter() );
			logger.debug( "机电仪问题流程走-非维修非技干-执行人" + employees.toArray().toString() );
			for( Emploee e : employees) {
				nextNodeExecutor = nextNodeExecutor + "," +  e.getEmpName();
				System.out.println("------" + nextNodeExecutor);
			}
			nextNodeExecutor = nextNodeExecutor.substring( 1, nextNodeExecutor.length() );
			vars.put( "problemEstimateExecutors", nextNodeExecutor );			
		}
		
		//启动流程	
		logger.debug( "机电仪问题上报后下一步执行人:" + nextNodeExecutor );
		
		//测试打印var
		GlobalUtil.showMapKeyValue( vars );		
		
		processServiceInter.startMechatronicsProcess(
				processDefinition, vars, problemInspection);		
	}
	
	/**   
	 * @Title: getUnfinishedMechatronicsTask   
	 * @Description: 获取用户代办事项   
	 * @param: @return      
	 * @return: ProblemInspection        
	 */  
	public List<ProblemInspection> getUnfinishedMechatronicsTask( String userName ) {
		logger.debug( "机电仪问题获取用户代办事项……userName：" + userName );
		List<ProblemInspection> problemInspections = new ArrayList<ProblemInspection>();
		//获取用户业务ids
		List<Task> tasks = processServiceInter.getCanPerTasksByAssignee( userName );
		if( tasks == null || tasks.size() == 0 ) {
			return problemInspections;
		}
		logger.debug( "机电仪问题获取用户代办事项……tasks：" + tasks.get( 0 ).getId() );
		
		List<String> businessKeys = new ArrayList<String>();
		for( Task t : tasks ) {
			String businessKey = processServiceInter.getProblemInspectionByTaskId( t.getId() );
			if( businessKey.length() > 0) {
				businessKeys.add( businessKey );
			}		
		}
		logger.debug( "机电仪问题获取用户代办事项……businessKeys：" + businessKeys );
		
		//获取对应的业务实体类对象
		if( businessKeys != null &&  businessKeys.size() > 0 ) {
			problemInspections = 
					processInsectionRepository.findAllById( businessKeys );
			return problemInspections;
		}				
		return problemInspections;
	}
	
	/**   
	 * @Title: getDealUrlNameService   
	 * @Description: 查询问题处理的节点html名     
	 * @param: @param userName
	 * @param: @return      
	 * @return: List<ProblemInspection>        
	 */  
	public String getDealUrlNameService( String currentPrid ) {
		logger.debug( "机电仪问题获取用户代办事项……currentPrid：" + currentPrid );
		String idName = new String();
		idName = processServiceInter.getActivityIdByPridService( currentPrid );		
		return idName;
	}

}
