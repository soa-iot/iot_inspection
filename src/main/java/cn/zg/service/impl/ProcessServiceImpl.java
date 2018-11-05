package cn.zg.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.EmployeeRepository;
import cn.zg.dao.inter.OrganizationRepository;
import cn.zg.dao.inter.ProcessInsectionRepository;
import cn.zg.dao.inter.RoleRepository;
import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.Organization;
import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.service.inter.ProcessServiceInter;
import cn.zg.utils.globalUtils.GlobalUtil;

/**
 * @ClassName: ProcessServiceImpl
 * @Description: 流程控制接口实现
 * @author zhugang
 * @date 2018年10月16日
 */
@Service
public class ProcessServiceImpl implements ProcessServiceInter {
	private static Logger logger = LoggerFactory.getLogger( ProcessServiceImpl.class );
	
	@Autowired
    private RepositoryService repositoryService;
	
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ProcessInsectionRepository processInsectionRepository;
	/**   
	 * @Title: deployMechatronicsProcess   
	 * @Description: 部署机电仪流程定义  
	 * @param: @return      
	 * @return: Deployment        
	 * @throws FileNotFoundException 
	 */  
	public Deployment deployMechatronicsProcess()  {
		//获取资源相对路径
//		String pngPath = "process/repairProcess.png";	
//		String bpmnPath = "process/repairProcess.bpmn";					
		//读取资源作为一个输入流
//		FileInputStream pngfileInputStream = new FileInputStream( pngPath );
//		FileInputStream bpmnfileInputStream = new FileInputStream( bpmnPath );
		Deployment deployment = repositoryService.createDeployment()
				 .name( "净化厂机电仪检维修流程")
//				 .addInputStream( "repairProcess.bpmn",  bpmnfileInputStream )
//				 .addInputStream( "repairProcess.png",  pngfileInputStream )
				 .addClasspathResource("process/repairProcess.bpmn")
				 .addClasspathResource("process/repairProcess.png")
				 .deploy();
		return deployment;
	}
	
	/**   
	 * @Title: getMechatronicsProcess   
	 * @Description: 根据流程部署，获取流程定义  
	 * @param: @param deployment
	 * @param: @return      
	 * @return: ProcessDefinition        
	 */  
	public ProcessDefinition getMechatronicsProcess( Deployment deployment ) {
		ProcessDefinition processDefinition = 
				repositoryService.createProcessDefinitionQuery()
				.deploymentId( deployment.getId() )
				.singleResult();
		return processDefinition;
	}
	
	/**   
	 * @Title: startMechatronicsProcess   
	 * @Description: 根据流程定义，启动流程  
	 * @param: @param processDefinition
	 * @param: @return      
	 * @return: String        
	 */  
	public String startMechatronicsProcess( 
			ProcessDefinition processDefinition, Map<String,Object> vars,
			ProblemInspection problemInspection ){
		/*
		 * 启动流程
		 */
		ProcessInstance processInstance = 
				runtimeService.startProcessInstanceById( 
						processDefinition.getId(), 
						problemInspection.getPiid() , 
						vars );
        String processId = processInstance.getId();
        logger.debug( "流程实例ID" + processId );
        
        /*
         * 保存流程实例ID到业务表
         */
        try {
        	problemInspection.setCurrentPrid( processId ); 
        	processInsectionRepository.saveAndFlush( problemInspection );
		} catch (Exception e) {
			logger.debug( "保存流程实例ID的prid失败" );
		}
        
        return processId;
	}
	
	/**   
	 * @Title: startMechatronicsProcess   
	 * @Description: 根据流程key，启动流程   
	 * @param: @param mechatronicsProcesskey
	 * @param: @param vars
	 * @param: @param problemInspection      
	 * @return: void        
	 */  
	public void startMechatronicsProcess( 
			String mechatronicsProcesskey, Map<String,Object> vars,
			ProblemInspection problemInspection ){
		runtimeService.startProcessInstanceByKey( 
				mechatronicsProcesskey, 
				problemInspection.getPiid(), 
				vars );
	}
	
	/**   
	 * @Title: executeCurrentProcessNode   
	 * @Description: 根据流程实例ID完成任务 
	 * @param: @param processId      
	 * @return: void        
	 */  
	public void executeCurrentProcessNode( String processId,
			ProblemInspection problemInspection ){
		Task task = taskService.createTaskQuery()
						.processInstanceId( processId )
						.singleResult();
		taskService.complete( task.getId() );
		
		/*
		 * 更新流程最新节点ID，到上报表中
		 */
		//获取当前流程实例任务节点
		Task activeTask = 
				taskService.createTaskQuery().taskId( task.getId() ).active().singleResult();
		String activeTaskId = activeTask.getId();
		//根据流程实例id获取业务主键id
		problemInspection.setCurrentTsid( activeTaskId );
	}
	
	/**   
	 * @Title: executeCurrentProcessNode   
	 * @Description: 根据流程实例和流程变量，ID完成任务   
	 * @param: @param processId
	 * @param: @param vars      
	 * @return: void        
	 */  
	public void executeCurrentProcessNode( String processId , 
			Map<String,Object> vars, ProblemInspection problemInspection  ) {
		Task task = taskService.createTaskQuery()
						.processInstanceId( processId )
						.singleResult();
		taskService.complete( task.getId() , vars );
	}
	
	/**   
	 * @Title: getTasksByAssignee   
	 * @Description: 查询用户个人任务
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getPersonalTasksByAssignee( String userName ){
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee( userName )
				.orderByTaskCreateTime()
				.desc()
				.list();
		return tasks;
	}
	
	/**   
	 * @Title: getCandidateTasksByAssignee   
	 * @Description:  查询用户组任务   
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getCandidateTasksByAssignee( String userName ){
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateUser( userName )
				.orderByTaskCreateTime()
				.desc()
				.list();
		return tasks;
	}
	
	/**   
	 * @Title: getCanPerTasksByAssignee   
	 * @Description: 查询用户的个人任务+组任务
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getCanPerTasksByAssignee( String userName ){
		List<Task> personalTasks = taskService.createTaskQuery()
				.taskAssignee( userName )
				.orderByTaskCreateTime()
				.desc()
				.list();
		List<Task> candidatetasks = taskService.createTaskQuery()
				.taskCandidateUser( userName )
				.orderByTaskCreateTime()
				.desc()
				.list();
		personalTasks.addAll( candidatetasks );				
		return personalTasks;
	}
	
	/**   
	 * @Title: getProblemInspectionByTaskId   
	 * @Description: 根据任务id,查询任务对应的业务数据对象  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: String        
	 */  
	public String getProblemInspectionByTaskId( String taskId ) {
		Task task = taskService.createTaskQuery()
				.taskId( taskId )
				.singleResult();
		ProcessInstance pi = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId( task.getProcessInstanceId() )
				.singleResult();
		
		String businessKey = "";
		//判断
		if( pi == null || task == null ) {
			return "";
		}
		try {
			businessKey = pi.getBusinessKey();
		} catch (Exception e) {
			return "";
		}		
		return businessKey == null?"":businessKey ;
	}	
	
	/**   
	 * @Title: findProcessDefinitionByTaskId   
	 * @Description: 根据任务id查询流程定义
	 * @param: @param taskId
	 * @param: @return      
	 * @return: ProcessDefinition        
	 */  
	public ProcessDefinition findProcessDefinitionByTaskId( String taskId ) {
		if( StringUtils.isBlank( taskId ) ) {
			logger.debug( "findProcessDefinitionByTaskId方法传入参数为空" );
			return null;
		}	
		
		Task task = findTaskById( taskId );
		String pdid = task.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService
				.getProcessDefinition( pdid );
		
		if( taskService == null) {
			logger.debug( "findProcessDefinitionByTaskId查询ProcessDefinition不存在" );
			return null;
		}		
		return processDefinition;
	}
	
	/**   
	 * @Title: findProcessDefinitionEntityByTaskId   
	 * @Description: 根据任务id查询流程定义实现  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: ProcessDefinitionEntity        
	 */  
	public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId( String taskId ) {
		if( StringUtils.isBlank( taskId ) ) {
			logger.debug( "findProcessDefinitionEntityByTaskId方法传入参数taskId为空或null" );
			return null;
		}	
		
		Task task = findTaskById( taskId );
		String pdid = task.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)
				repositoryService.getProcessDefinition( pdid );
		
		if( processDefinitionEntity == null) {
			logger.debug( "findProcessDefinitionByTaskId查询ProcessDefinition不存在" );
			return null;
		}		
		return processDefinitionEntity;
	}
	
	/**   
	 * @Title: findTaskById   
	 * @Description: 根据任务ID查询任务  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: Task        
	 */  
	public Task findTaskById( String taskId ) {
		if( StringUtils.isBlank( taskId ) ) {
			logger.debug( "findTaskById方法传入参数为空" );
			return null;
		}	
		
		Task task= taskService.createTaskQuery()
				.taskId( taskId )
				.singleResult();
		
		if( taskService == null) {
			logger.debug( "findTaskById查询Task任务不存在" );
			return null;
		}
		return task;
	}
	
	/**   
	 * @Title: findActivityImplByTaskId   
	 * @Description: 根据任务ID和活动ID，得到活动节点 
	 * @param: @param taskId
	 * @param: @param activityId
	 * @param: @return      
	 * @return: ActivityImpl        
	 */  
	public ActivityImpl findActivityImplByTaskActId( String taskId, String activityId ) {
//		ProcessDefinition processDefinition = 
//				findProcessDefinitionByTaskId( taskId );
		ProcessDefinitionEntity processDefinition = 
				findProcessDefinitionEntityByTaskId( taskId );
		//验证参数taskId
		if( StringUtils.isBlank( taskId ) ) {
			logger.debug( "findActivityImplByTaskId方法传入参数taskId为空或null" );
			return null; 			
		}
		//验证参数activityId为null和空
		if( StringUtils.isBlank( activityId ) ) {
			activityId = findTaskById( taskId ).getTaskDefinitionKey(); 		
		}
		//验证参数activityId = END
		if ( activityId.toUpperCase().equals( "END" ) ) {  
            for ( ActivityImpl activityImpl : processDefinition.getActivities() ) {  
                List<PvmTransition> pvmTransitionList = activityImpl  
                        .getOutgoingTransitions();  
                if ( pvmTransitionList.isEmpty() ) {  
                    return activityImpl;  
                }  
            }  
        }  		
		ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)  
                .findActivity(activityId);	
		return activityImpl;
	}
	
	/**   
	 * @Title: findActivityImplByTaskId   
	 * @Description: 根据任务id，查询活动节点   
	 * @param: @param taskId
	 * @param: @return      
	 * @return: ActivityImpl        
	 */  
	public ActivityImpl findActivityImplByTaskId( String taskId ) {
		Task task = taskService.createTaskQuery().taskId( taskId ).singleResult();
		//获取流程定义id、实例id
		String processDefinitionId = task.getProcessInstanceId();
		String processInstanceId = task.getProcessInstanceId(); 		
		//获取流程实例对象、流程定义对象
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId( processDefinitionId )
				.singleResult();
//		ProcessDefinitionEntity processDefinitionEntity =     //实现方式1
//				(ProcessDefinitionEntity) processDefinition;
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)
				repositoryService.getProcessDefinition( processDefinitionId );
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId( processInstanceId )
				.singleResult();
		//根据流程实例对象-查询活动节点对象-获取活动节点id
		String activityId = processInstance.getActivityId();
		ActivityImpl activityImpl = processDefinitionEntity
				.findActivity( activityId );
		return activityImpl;
	}
	
	/**   
	 * @Title: findActivityImplByPrid   
	 * @Description: 根据流程实例id，查询活动节点     
	 * @param: @param prid
	 * @param: @return      
	 * @return: ActivityImpl        
	 */  
	public ActivityImpl findActivityImplByPrid( String prid ){	
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId( prid.trim() )
				.singleResult();
		String processDefinitionId = processInstance.getProcessDefinitionId();
		//根据流程实例对象-查询活动节点对象-获取活动节点id
		String activityId = processInstance.getActivityId();
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId( processDefinitionId )
				.singleResult();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)
				repositoryService.getProcessDefinition( processDefinitionId );
		ActivityImpl activityImpl = processDefinitionEntity
				.findActivity( activityId );
		return activityImpl;
	}
	
	public void saveComment( String taskId, String _comment ){
		
	}
	
	
	
	/**   
	 * @Title: getMonitor   
	 * @Description: 问题评估节点，根据问题上报人名称，查找对应的班组长 
	 * @param: @param executorName
	 * @param: @return      
	 * @return: List<String>       
	 */  
	public List<Emploee> getMonitor( String reportorName ) {
		/*
		 * 查询用户组织
		 */
		List<Emploee> employeeList = employeeRepository.findByEmpName( reportorName ); 	
		//用户组织
		List<Organization> totalOrganization = new ArrayList<Organization>();
		for( Emploee e : employeeList) {
			List<Organization> organizationList = 
					organizationRepository.findByEmpId( e.getEmpId() );
			for( Organization o : organizationList ) {
				totalOrganization.add( o );
			}
		}
		
		/*
		 * 查询角色下所有成员
		 */
		String markRole = "";
		for( Organization o : totalOrganization ) {
			markRole = markRole +  o.getOrgName() + "班长,";
		}
		markRole = markRole.substring( 0, markRole.length() -1 );
		System.out.println("markRole:" + markRole);
		String roleNameLike = "";
		String pureString = "净化";
		String repairString = "维修";
		String testString = "化验";
		if( markRole.contains( "净化") && !markRole.contains( "维修") 
				&& !markRole.contains( "化验") ) {
			roleNameLike = pureString + "%" + "班班长";
		} else if( markRole.contains( "维修") && !markRole.contains( "净化") 
				&& !markRole.contains( "化验") ){
			roleNameLike = repairString + "%" + "班班长";
		} else if( markRole.contains( "化验") && !markRole.contains( "净化") 
				&& !markRole.contains( "维修") ){
			roleNameLike = testString + "%" + "班班长";
		} else if( markRole.contains( "净化") ){
			roleNameLike = pureString + "%" + "班班长";
		} else if( markRole.contains( "维修") ){
			roleNameLike = repairString + "%" + "班班长";
		} else {
			throw new RuntimeException( "获取问题上报人的班长失败……" );
		}
		System.out.println("roleNameLike:"+ roleNameLike);
		List<Emploee> problemEstimateExecutors = 
				employeeRepository.findByRoleName( roleNameLike );			
		return problemEstimateExecutors;
	}
	
	/**   
	 * @Title: getUserHistoryTask   
	 * @Description: 根据上报人名，查询全部上报任务    
	 * @param: @param userName
	 * @param: @return      
	 * @return: List<ProblemInspection>        
	 */  
	public List<ProblemInspection> getUserHistoryTask( 
			String userName, String problemTypeStr ){
		//问题类型
		List<String> problemTypes = new ArrayList<String>();
		String[] problemTypeArr = problemTypeStr.split( "," );
		for( String s : problemTypeArr ) {
			problemTypes.add( s.trim() );
		}
		logger.debug( "查询历史任务：" + problemTypes.toString() );
		logger.debug( "查询历史任务：" + userName );
		List<ProblemInspection> problemInspections = new ArrayList<ProblemInspection>();		
		Sort sort = new Sort( Sort.Direction.ASC, "problemType" );
		problemInspections = 
				processInsectionRepository
				.findAllByReporterAndProblemTypeIn( userName, problemTypes, sort );
		return problemInspections;
	}
	
	
	/**   
	 * @Title: getActivityIdByPridService   
	 * @Description: 根据流程实例ID，获取活动节点ID和名称 
	 * @param: @return      
	 * @return: String        
	 */  
	public String getActivityIdByPridService( String prid ) {		
		ActivityImpl activityImpl = findActivityImplByPrid( prid );
		if( activityImpl == null ) {
			return "";
		}
		String idName = activityImpl.getId();
		idName = idName +  "," + activityImpl.getProperty( "name" );
		logger.debug( "根据流程实例ID，获取活动节点ID和名称 ：" + prid );
		return idName;
	}
}
