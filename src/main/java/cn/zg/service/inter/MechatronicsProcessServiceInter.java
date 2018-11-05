package cn.zg.service.inter;

import java.util.List;

import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.entity.dataExchange.ResultJson;

/**
 * @ClassName: ProcessInspectionServiceInter
 * @Description: 流程业务表接口
 * @author zhugang
 * @date 2018年10月16日
 */
public interface MechatronicsProcessServiceInter {

	
	/**   
	 * @Title: saveProblemInspect   
	 * @Description: 保存问题流程   
	 * @param: @param problemInspection
	 * @param: @return      
	 * @return: ProblemInspection        
	 */  
	ProblemInspection saveProblemInspect( ProblemInspection problemInspection);
		
	/**   
	 * @Title: startMechatronicsProcess   
	 * @Description: 启动机电仪流程  
	 * @param: @param problemReportRole
	 * @param: @param problemInspection
	 * @param: @param nextNodeExecutor      
	 * @return: void        
	 */  
	public void startMechatronicsProcess( 
			String problemReportRole, ProblemInspection problemInspection,
			String nextNodeExecutor );
	
	/**   
	 * @Title: getDealUrlNameService   
	 * @Description: 查询问题处理的节点html名  
	 * @param: @param currentPrid
	 * @param: @return      
	 * @return: List<ProblemInspection>        
	 */  
	public String getDealUrlNameService( String currentPrid );
	
}
