package cn.zg.service.inter;

import cn.zg.entity.daoEntity.ProblemInspection;

/**
 * @ClassName: ProcessInspectionServiceInter
 * @Description: 流程业务表接口
 * @author zhugang
 * @date 2018年10月16日
 */
public interface ProcessInspectionServiceInter {

	
	/**   
	 * @Title: saveProblemInspect   
	 * @Description: 保存问题流程   
	 * @param: @param problemInspection
	 * @param: @return      
	 * @return: ProblemInspection        
	 */  
	ProblemInspection saveProblemInspect( ProblemInspection problemInspection);
}
