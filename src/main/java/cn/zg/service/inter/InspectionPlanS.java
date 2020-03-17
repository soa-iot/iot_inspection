package cn.zg.service.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zg.entity.daoEntity.InspectionPlan;
import cn.zg.entity.daoEntity.UserTree;

/**
 * @ClassName: InspectionPlanS
 * @Description: 巡检计划Service层
 * @author Bru.Lo
 * @date 2019年12月24日
 */
public interface InspectionPlanS {

	/**
	 * 根据条件分页查询所有巡检计划查询数据service层
	 * @param inspectionPlan
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	List<InspectionPlan> findInspectionPlanAll(InspectionPlan inspectionPlan,
			Integer minNum,
			Integer maxNum);
	
	/**
	 * 根据条件统计所有巡检计划数量Service层
	 * @param inspectionPlan
	 * @return
	 */
	Integer inspectionPlanCount(InspectionPlan inspectionPlan);
	
	/**
	 * 修改或新增巡检计划
	 * @param inspectionPlan
	 * @return
	 */
	Integer addOrEditInspectionPlan(@Param("inspectionPlan")InspectionPlan inspectionPlan);
	
	/**
	 * 人员组织树数据
	 * @param inspectionPlan
	 * @return
	 */
	List<UserTree> userTree(String roleID);
	
	/**
	 * 获取计划执行人
	 * @param inspectionPlan
	 * @return
	 */
	List<UserTree> planUsers(String roleID);
	
	/**
	 * 删除巡检计划
	 * @param planId
	 * @return
	 */
	Integer deleteInspectionPlan(String planId);
}
