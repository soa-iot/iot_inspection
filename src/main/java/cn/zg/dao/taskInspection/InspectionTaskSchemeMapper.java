package cn.zg.dao.taskInspection;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.taskInspection.InspectionContent;
import cn.zg.entity.taskInspection.InspectionPoint;
import cn.zg.entity.taskInspection.InspectionRequire;
import cn.zg.entity.taskInspection.InspectionTaskScheme;
import cn.zg.entity.taskInspection.TaskInspectionContent;
import cn.zg.entity.taskInspection.TaskInspectionPoint;
import cn.zg.entity.taskInspection.TaskInspectionRequire;

@Mapper
public interface InspectionTaskSchemeMapper {
	
	/**
	 * 生成方案表实例
	 * @param inspectionTaskScheme
	 * @return
	 */
	Integer insertInspectionScheme(@Param("inspectionTaskScheme")List<InspectionTaskScheme> inspectionTaskSchemes);
	
	/**
	 * 获取方案巡检点表实例
	 * @param inspectionTaskScheme
	 * @return
	 */
	List<InspectionPoint> getInspectionPoint(String schemeId);
	
	/**
	 * 新增巡检点实例
	 * @param taskInspectionPoint
	 * @return
	 */
	Integer insertTaskInspectionPoint(@Param("taskInspectionPoint") List<TaskInspectionPoint> taskInspectionPoint);
	
	/**
	 * 获取方案巡检设备表模板
	 * @param pointIds
	 * @return
	 */
	List<InspectionContent> getInspectionContent(String schemeId);
	
	/**
	 * 新增巡检设备实例
	 * @param taskInspectionContent
	 * @return
	 */
	Integer insertTaskInspectionContent(@Param("taskInspectionContent") List<TaskInspectionContent> taskInspectionContent);
	
	/**
	 * 获取方案巡检项表实例
	 * @param contentIds
	 * @return
	 */
	List<InspectionRequire> getInspectionRequire(String schemeId);
	
	/**
	 * 获取巡检设备数据，根据schemeId
	 * @param schemeId
	 * @return
	 */
	List<TaskInspectionContent> getTaskInspectionContent(String schemeId);

	/**
	 * 新增巡检项实例
	 * @param taskInspectionRequires
	 * @return
	 */
	Integer inserttaskInspectionRequires(@Param("taskInspectionRequires")List<TaskInspectionRequire> taskInspectionRequires);
	
	/**
	 * 删除巡检项实例
	 * @param schemeId
	 * @return
	 */
	Integer deleteTaskInspectionRequire(String schemeId);
	
	/**
	 * 删除巡检设备实例
	 * @param schemeId
	 * @return
	 */
	Integer deleteTaskInspectionContent(String schemeId); 
	
	/**
	 * 删除巡检点实例
	 * @param schemeId
	 * @return
	 */
	Integer deleteTaskInspectionPoint(String schemeId);
	
	/**
	 * 删除巡检方案实例
	 * @param schemeId
	 * @return
	 */
	Integer deleteTaskInspection(String planId);
}