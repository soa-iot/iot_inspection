package cn.zg.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.InspectionPlanMapper;
import cn.zg.entity.daoEntity.InspectionPlan;
import cn.zg.entity.daoEntity.UserTree;
import cn.zg.service.inter.InspectionPlanS;

/**
 * @ClassName: InspectionPlanSl
 * @Description: 巡检计划Service层
 * @author Bru.Lo
 * @date 2019年12月24日
 */
@Service
public class InspectionPlanSl implements InspectionPlanS{

	@Autowired
	private InspectionPlanMapper  inspectionPlanMapper;
	/**
	 * 根据条件分页查询所有巡检计划查询数据service层
	 * @param inspectionPlan
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	@Override
	public List<InspectionPlan> findInspectionPlanAll(InspectionPlan inspectionPlan, Integer page, Integer limit) {
		
		//分页查询最大值最小值计算
		Integer minNum = null;
		Integer maxNum = null;
		if (page != null && limit != null) {
			minNum = limit * (page-1);
			maxNum = limit * page;
		}
		
		return inspectionPlanMapper.findInspectionPlanAll(inspectionPlan, minNum, maxNum);
	}
	
	/**
	 * 根据条件统计所有巡检计划数量Service层
	 * @param inspectionPlan
	 * @return
	 */
	@Override
	public Integer inspectionPlanCount(InspectionPlan inspectionPlan) {
		
		return inspectionPlanMapper.inspectionPlanCount(inspectionPlan);
	}

	/**
	 * 修改或新增巡检计划
	 * @param inspectionPlan
	 * @return
	 */
	@Transactional
	@Override
	public Integer addOrEditInspectionPlan(InspectionPlan inspectionPlan) {
		
		int row = 0;
		
		String cycleUnit = inspectionPlan.getRemark();
		if ("H".equals(cycleUnit)) {
			inspectionPlan.setPlanCycle(1+"");
		}if ("D".equals(cycleUnit)) {
			inspectionPlan.setPlanCycle(2+"");
		}
		if ("E".equals(cycleUnit)) {
			inspectionPlan.setPlanCycle(3+"");
		}
		if ("M".equals(cycleUnit)) {
			inspectionPlan.setPlanCycle(4+"");
		}
		
		if (inspectionPlan.getPlanID() != null && inspectionPlan.getPlanID() !="") {
			
			row = inspectionPlanMapper.updateInspectionPlan(inspectionPlan);
		}else {
			row = inspectionPlanMapper.insertInspectionPlan(inspectionPlan);
		}
		return row;
	}

	/**
	 * 人员组织树数据
	 * @param inspectionPlan
	 * @return
	 */
	@Override
	public List<UserTree> userTree(String roleID) {
		
		roleID = roleID == null ? "" : roleID;
		List<UserTree> userTrees = inspectionPlanMapper.userTree(roleID);
		
		List<UserTree> users = inspectionPlanMapper.userRole(roleID);
		users.addAll(userTrees);
		
		for (UserTree userTree : users) {
			if (userTree.getIsParent() == 0) {
				userTree.setIsParent(1);
			}else {
				userTree.setIsParent(0);
			}
		}
		
		return users;
	}

	/**
	 * 删除巡检计划
	 * @param planId
	 * @return
	 */
	@Override
	public Integer deleteInspectionPlan(String planId) {
		return inspectionPlanMapper.deleteInspectionPlan(planId);
	}

	/**
	 * 获取计划执行人
	 * @param inspectionPlan
	 * @return
	 */
	@Override
	public List<UserTree> planUsers(String roleID) {
		
		return inspectionPlanMapper.userRole(roleID);
	}	
}
