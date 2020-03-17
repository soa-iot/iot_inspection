package cn.zg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.InspectionPlan;
import cn.zg.entity.daoEntity.UserTree;
import cn.zg.service.inter.InspectionPlanS;
import cn.zg.service.inter.taskInspection.InspectionTaskSchemeS;
import cn.zg.utils.JsonForTableResult;

/**
 * @ClassName: InspectionPlanC
 * @Description: 巡检计划Control层
 * @author Bru.Lo
 * @date 2019年12月24日
 */

@RestController
@RequestMapping("/inspectionplan")
public class InspectionPlanC {
	private static Logger logger = LoggerFactory.getLogger(InspectionPlanC.class);

	@Autowired
	private InspectionPlanS inspectionPlanS;

	@Autowired
	private InspectionTaskSchemeS inspectionTaskSchemeS;

	/**
	 * 获取巡检计划
	 * 
	 * @param inspectionPlan
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/inspectionplanall")
	public JsonForTableResult<List<InspectionPlan>> findInspectionPlanAll(InspectionPlan inspectionPlan, Integer page,
			Integer limit) {

		logger.info("---------------inspectionPlan---------------" + inspectionPlan);
		logger.info("---------------page---------------" + page);
		logger.info("---------------limit---------------" + limit);
		List<InspectionPlan> inspectionPlans = inspectionPlanS.findInspectionPlanAll(inspectionPlan, page, limit);
		Integer count = inspectionPlanS.inspectionPlanCount(inspectionPlan);

		if (inspectionPlans != null) {
			return new JsonForTableResult<List<InspectionPlan>>(0, inspectionPlans, "数据查询成功", count);
		} else {
			return new JsonForTableResult<List<InspectionPlan>>(1, null, "数据查询失败", 0);
		}

	}

	/**
	 * 新增巡检计划
	 * 
	 * @param inspectionPlan
	 * @return
	 */
	@RequestMapping("/addoreditinspectionplan")
	public JsonForTableResult<Integer> addOrEditInspectionPlan(InspectionPlan inspectionPlan) {

		logger.info("---------------inspectionPlan---------------" + inspectionPlan);

		int row = inspectionPlanS.addOrEditInspectionPlan(inspectionPlan);

		if (row > 0) {
			return new JsonForTableResult<Integer>(0, row, "操作成功", row);
		} else {
			return new JsonForTableResult<Integer>(1, row, "操作失败", row);
		}

	}

	/**
	 * 执行巡检计划
	 * 
	 * @param inspectionPlan
	 * @return
	 */
	@RequestMapping("/taskinspectionissued/{rolid}")
	public JsonForTableResult<Integer> addTaskInspectionIssued(InspectionPlan inspectionPlan, @PathVariable("rolid")String rolid,
			String roleName) {

		logger.info("---------------inspectionPlan(C)---------------" + inspectionPlan);
		logger.info("---------------rolid(C)---------------" + rolid);
		logger.info("---------------roleName(C)---------------" + roleName);
		inspectionTaskSchemeS.deleteInspectionScheme(inspectionPlan.getPlanID());
		Integer row = inspectionTaskSchemeS.insertInspectionScheme(inspectionPlan, rolid, roleName);

		if (row > 0) {
			return new JsonForTableResult<Integer>(0, row, "操作成功", row);
		} else {
			return new JsonForTableResult<Integer>(1, row, "操作失败", row);
		}

	}

	@RequestMapping("deleteplan/{planId}")
	public JsonForTableResult<Integer> deletePlan(@PathVariable("planId")String planId) {

		logger.info("---------------planId(C)---------------" + planId);
		inspectionTaskSchemeS.deleteInspectionScheme(planId);
		Integer row = inspectionPlanS.deleteInspectionPlan(planId);

		if (row > 0) {
			return new JsonForTableResult<Integer>(0, row, "操作成功", row);
		} else {
			return new JsonForTableResult<Integer>(1, row, "操作失败", row);
		}

	}

	/**
	 * 获取人员组织信息
	 * 
	 * @param roleID
	 * @return
	 */
	@RequestMapping("/usertree")
	public JsonForTableResult<List<UserTree>> userTree(String roleID) {

		logger.info("++++++++++++++++++++++roleID++++++++++++++++++++++" + roleID);
		List<UserTree> userTree = inspectionPlanS.userTree(roleID);

		if (userTree != null) {
			return new JsonForTableResult<List<UserTree>>(0, userTree, "操作成功", 1);
		} else {
			return new JsonForTableResult<List<UserTree>>(1, userTree, "操作失败", 0);
		}

	}
	
	/**
	 * 获取计划执行人
	 * 
	 * @param roleID
	 * @return
	 */
	@RequestMapping("/planusers/{roleID}")
	public JsonForTableResult<List<UserTree>> planUsers(@PathVariable("roleID")String roleID) {

		logger.info("++++++++++++++++++++++roleID++++++++++++++++++++++" + roleID);
		List<UserTree> userTree = inspectionPlanS.planUsers(roleID);

		if (userTree != null) {
			return new JsonForTableResult<List<UserTree>>(0, userTree, "操作成功", 1);
		} else {
			return new JsonForTableResult<List<UserTree>>(1, userTree, "操作失败", 0);
		}

	}

}
