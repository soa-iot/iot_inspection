package cn.zg.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.daoEntity.InspectionPlan;
import cn.zg.entity.daoEntity.UserTree;

/**
 * @ClassName: InspectionPlanMapper
 * @Description: 巡检计划D奥层
 * @author Bru.Lo
 * @date 2019年12月24日
 */
@Mapper
public interface InspectionPlanMapper {

	/**
	 * 根据条件分页查询所有巡检计划查询数据dao层
	 * @param inspectionPlan
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	List<InspectionPlan> findInspectionPlanAll(@Param("inspectionPlan")InspectionPlan inspectionPlan,
			@Param("minNum")Integer minNum,
			@Param("maxNum")Integer maxNum);
	
	/**
	 * 根据条件统计所有巡检计划数量dao层
	 * @param inspectionPlan
	 * @return
	 */
	Integer inspectionPlanCount(@Param("inspectionPlan")InspectionPlan inspectionPlan);
	
	/**
	 * 新增巡检计划
	 * @param inspectionPlan
	 * @return
	 */
	Integer insertInspectionPlan(@Param("inspectionPlan")InspectionPlan inspectionPlan);
	
	/**
	 * 更新巡检计划
	 * @param inspectionPlan
	 * @return
	 */
	Integer updateInspectionPlan(@Param("inspectionPlan")InspectionPlan inspectionPlan);
	
	/**
	 * 人员组织树数据
	 * @param inspectionPlan
	 * @return
	 */
	LinkedList<UserTree> userTree(String rolId);
	
	/**
	 * 当前角色人员
	 * @param inspectionPlan
	 * @return
	 */
	LinkedList<UserTree> userRole(String rolId);
	
	/**
	 * 删除巡检计划
	 * @param planId
	 * @return
	 */
	Integer deleteInspectionPlan(String planId);
}
