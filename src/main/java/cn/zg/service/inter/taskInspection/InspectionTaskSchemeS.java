package cn.zg.service.inter.taskInspection;

import cn.zg.entity.daoEntity.InspectionPlan;

/**
 * @ClassName: InspectionTaskSchemeS
 * @Description: 巡检计划Service层
 * @author Bru.Lo
 * @date 2019年12月30日
 */
public interface InspectionTaskSchemeS {

	/**
	 * 生成方案表实例
	 * @param inspectionTaskScheme
	 * @return
	 */
	Integer insertInspectionScheme(InspectionPlan inspectionPlan,String rolid,String roleName);
	
	/**
	 * 删除方案表实例
	 * @param inspectionTaskScheme
	 * @return
	 */
	void deleteInspectionScheme(String planId);
	
}
