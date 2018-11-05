package cn.zg.service.inter;


import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.ProblemInspection;

/**
 * @ClassName: ProcessServiceInter
 * @Description: 流程控制接口
 * @author zhugang
 * @date 2018年10月16日
 */
@Service
public interface ProcessServiceInter {
	/**   
	 * @Title: deployMechatronicsProcess   
	 * @Description: 部署机电仪流程定义   
	 * @param: @return      
	 * @return: Deployment        
	 */  
	public Deployment deployMechatronicsProcess();
	
	/**   
	 * @Title: getMechatronicsProcess   
	 * @Description: 根据流程部署，获取流程定义    
	 * @param: @param deployment
	 * @param: @return      
	 * @return: ProcessDefinition        
	 */  
	public ProcessDefinition getMechatronicsProcess( Deployment deployment );
	
	/**   
	 * @Title: startMechatronicsProcess   
	 * @Description: 根据流程定义，启动流程    
	 * @param: @param processDefinition
	 * @param: @param vars
	 * @param: @param problemInspection
	 * @param: @return      
	 * @return: String        
	 */  
	public String startMechatronicsProcess( 
			ProcessDefinition processDefinition, Map<String,Object> vars,
			ProblemInspection problemInspection );
	
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
			ProblemInspection problemInspection );
	
	/**   
	 * @Title: executeCurrentProcessNode   
	 * @Description:根据流程实例ID完成任务    
	 * @param: @param processId      
	 * @return: void        
	 */  
	public void executeCurrentProcessNode( 
			String processId, ProblemInspection problemInspection );
	
	/**   
	 * @Title: executeCurrentProcessNode   
	 * @Description: 根据流程实例和流程变量，ID完成任务     
	 * @param: @param processId
	 * @param: @param vars      
	 * @return: void        
	 */  
	public void executeCurrentProcessNode( String processId, 
			Map<String,Object> vars, ProblemInspection problemInspection  );
	
	/**   
	 * @Title: getPersonalTasksByAssignee   
	 * @Description:  查询用户的个人任务
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getPersonalTasksByAssignee( String userId );
	
	/**   
	 * @Title: getCandidateTasksByAssignee   
	 * @Description: 查询用户的个人任务+组任务    
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getCandidateTasksByAssignee( String userId );
	
	/**   
	 * @Title: getCanPerTasksByAssignee   
	 * @Description:  获取用户代办事项      
	 * @param: @param userName
	 * @param: @return      
	 * @return: List<Task>        
	 */  
	public List<Task> getCanPerTasksByAssignee( String userName );
	
	/**   
	 * @Title: findActivityImplByTaskId   
	 * @Description: 根据任务id，查询活动节点 
	 * @param: @param taskId
	 * @param: @return      
	 * @return: ActivityImpl        
	 */  
	public ActivityImpl findActivityImplByTaskId( String taskId );	
	
	/**   
	 * @Title: findActivityImplByPrid   
	 * @Description: 根据流程实例id，查询活动节点    
	 * @param: @param prid
	 * @param: @return      
	 * @return: ActivityImpl        
	 */  
	public ActivityImpl findActivityImplByPrid( String prid );
	
	/**   
	 * @Title: getProblemInspectionByTaskId   
	 * @Description: 根据任务id,查询任务对应的业务数据对象  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: String        
	 */  
	public String getProblemInspectionByTaskId( String taskId );
	
	/**   
	 * @Title: getMonitor   
	 * @Description: 问题评估节点，根据问题上报人名称，查找对应的班组长   
	 * @param: @param reportorName
	 * @param: @return      
	 * @return: List<Emploee>        
	 */  
	public List<Emploee> getMonitor( String reportorName );
	
	/**   
	 * @Title: getUserHistoryTask   
	 * @Description: 获取用户历史上报业务  
	 * @param: @param userName
	 * @param: @return      
	 * @return: List<ProblemInspection>        
	 */  
	public List<ProblemInspection> getUserHistoryTask( 
			String userName, String problemTypeStr );
	
	/**   
	 * @Title: getActivityIdByPridService   
	 * @Description: 根据流程实例ID，获取活动节点ID和名称 
	 * @param: @return      
	 * @return: String        
	 */  
	public String getActivityIdByPridService( String prid );
}
