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
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.EmployeeRepository;
import cn.zg.dao.inter.OrganizationRepository;
import cn.zg.dao.inter.RoleRepository;
import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.Organization;
import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.service.inter.ProcessServiceInter;

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
	/**   
	 * @Title: deployMechatronicsProcess   
	 * @Description: 部署机电仪流程定义  
	 * @param: @return      
	 * @return: Deployment        
	 * @throws FileNotFoundException 
	 */  
	public Deployment deployMechatronicsProcess() throws FileNotFoundException {
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
				vars);
	}
	
	/**   
	 * @Title: executeCurrentProcessNode   
	 * @Description: 根据流程实例ID完成任务 
	 * @param: @param processId      
	 * @return: void        
	 */  
	public void executeCurrentProcessNode( String processId ) {
		Task task = taskService.createTaskQuery()
						.processInstanceId( processId )
						.singleResult();
		taskService.complete( task.getId() );
	}
	
	/**   
	 * @Title: executeCurrentProcessNode   
	 * @Description: 根据流程实例和流程变量，ID完成任务   
	 * @param: @param processId
	 * @param: @param vars      
	 * @return: void        
	 */  
	public void executeCurrentProcessNode( String processId , Map<String,Object> vars  ) {
		Task task = taskService.createTaskQuery()
						.processInstanceId( processId )
						.singleResult();
		taskService.complete( task.getId() , vars);
	}
	
	/**   
	 * @Title: getTasksByAssignee   
	 * @Description: 查询用户个人任务
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getPersonalTasksByAssignee( String userId ){
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee( userId )
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
	public List<Task> getCandidateTasksByAssignee( String userId ){
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateUser( userId )
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
	public List<Task> getCanPerTasksByAssignee( String userId ){
		List<Task> personalTasks = taskService.createTaskQuery()
				.taskAssignee( userId )
				.orderByTaskCreateTime()
				.desc()
				.list();
		List<Task> candidatetasks = taskService.createTaskQuery()
				.taskCandidateUser( userId )
				.orderByTaskCreateTime()
				.desc()
				.list();
		personalTasks.addAll( candidatetasks );				
		return personalTasks;
	}
	
	/**   
	 * @Title: getProblemInspectionByTaskId   
	 * @Description: 查询任务对应的业务数据对象  
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
				.processInstanceId( task.getProcessDefinitionId() )
				.singleResult();
		String businessKey = pi.getBusinessKey();
		return businessKey;
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
}
