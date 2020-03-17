package cn.zg.service.impl.taskInspection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zg.dao.InspectionPlanMapper;
import cn.zg.dao.taskInspection.InspectionTaskSchemeMapper;
import cn.zg.entity.daoEntity.InspectionPlan;
import cn.zg.entity.taskInspection.InspectionContent;
import cn.zg.entity.taskInspection.InspectionPoint;
import cn.zg.entity.taskInspection.InspectionRequire;
import cn.zg.entity.taskInspection.InspectionTaskScheme;
import cn.zg.entity.taskInspection.TaskInspectionContent;
import cn.zg.entity.taskInspection.TaskInspectionPoint;
import cn.zg.entity.taskInspection.TaskInspectionRequire;
import cn.zg.service.inter.taskInspection.InspectionTaskSchemeS;
import lombok.extern.log4j.Log4j2;

/**
 * @ClassName: InspectionTaskSchemeS
 * @Description: 巡检计划Service层
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@Log4j2
@Service
public class InspectionTaskSchemeSI implements InspectionTaskSchemeS{

	@Autowired
	private InspectionTaskSchemeMapper inspectionTaskSchemeMapper;
	
	@Autowired
	private InspectionPlanMapper inspectionPlanMapper;
	
	/**
	 * 生成方案表实例
	 * @param inspectionTaskScheme
	 * @return
	 */
	//@Transactional
	@Override
	public Integer insertInspectionScheme(InspectionPlan inspectionPlan,String rolid,String roleName) {
		Integer row = 0;
		String schemeId = inspectionPlan.getSchemeID();
		
		long t1 = System.currentTimeMillis();
		//生成方案
		List<String> taskIds = insertInspectionTaskScheme(inspectionPlan,rolid,roleName);
		
		//生成巡检点
		Map<String, String> pointIds = insertInspectionTaskPoint(schemeId,taskIds,inspectionPlan.getSchemeName());
		
		//生成巡讲设备
		Map<String, String> contentIds = insertInspectionTaskContent(pointIds,schemeId);

		row = insertTaskInspectionRequire(schemeId);
		
		log.info("---------------------taskIds(S)--------------------" + taskIds.size());
		log.info("---------------------pointIds(S)--------------------" + pointIds.size());
		log.info("---------------------contentIds(S)--------------------" + contentIds.size());
		log.info("---------------------row(S)--------------------" + row);
		//生成巡讲要求
		long t2 = System.currentTimeMillis();
		System.err.println("-----------------------结束: " + System.currentTimeMillis());
		System.err.println("-----------------------用时: " + (t2 - t1));
		System.err.println("-----------------------用时: " + schemeId);
		return row;
	}
	
	/**
	 * 生成方案实例
	 * @param inspectionPlan
	 * @param rolid
	 * @param roleName
	 * @return
	 */
	public List<String> insertInspectionTaskScheme(InspectionPlan inspectionPlan,String rolid,String roleName) {
		
		Integer size = 0;
		List<String> taskIds = new ArrayList<String>();
		List<InspectionPlan> inspectionPlans = inspectionPlanMapper.findInspectionPlanAll(inspectionPlan, null, null);
		inspectionPlan = inspectionPlans.get(0);
		
		List<InspectionTaskScheme> inspectionTaskSchemes = new ArrayList<InspectionTaskScheme>();
		
		String fishUnit = inspectionPlan.getRemark();
		
		if ("H".equals(fishUnit)) {
			int cycle = Integer.valueOf(inspectionPlan.getPlanIntervalTime());
			size = 24/cycle;
		}
		
		for (int i = 0; i <= size; i++) {

			InspectionTaskScheme inspectionTaskScheme = new InspectionTaskScheme();
			String taskId = null;
			if (i==0) {
				taskId = getTaskId();
			}else {
				Long tmp = Long.valueOf(taskIds.get(i-1))+i;
				taskId = tmp+"";
			}
			
			
			inspectionTaskScheme.setTaskId(taskId);
			inspectionTaskScheme.setTaskteamId(rolid);
			inspectionTaskScheme.setTaskteamName(roleName);
			inspectionTaskScheme.setSchemeId(inspectionPlan.getSchemeID());
			inspectionTaskScheme.setEquUnit(inspectionPlan.getEquUnit());
			inspectionTaskScheme.setSchemeName(inspectionPlan.getSchemeName());
			inspectionTaskScheme.setTaskName(inspectionPlan.getPlanName());
			inspectionTaskScheme.setTaskDescribe(inspectionPlan.getPlanDesc());
			inspectionTaskScheme.setTaskState("UNFINISHED");
			inspectionTaskScheme.setIsdownload("FALSE");
			inspectionTaskScheme.setRemarkone(inspectionPlan.getPlanID());
			inspectionTaskScheme.setRemarkfour(inspectionPlan.getSchemeType());
			inspectionTaskScheme.setSchemeType(inspectionPlan.getPlanCycle());

			// "任务完成时间"
			Date startTime = getTime(inspectionPlan.getPlanStartTime(),Integer.valueOf(inspectionPlan.getPlanIntervalTime())*i);
			
			inspectionTaskScheme.setTaskDeadline(new Date());
			inspectionTaskScheme.setTaskStartTime(startTime);
			inspectionTaskScheme.setPlannedCompletionTime(inspectionPlan.getPlanExpectTime());
			
			if (startTime != null) {
				taskIds.add(taskId);
				inspectionTaskSchemes.add(inspectionTaskScheme);
			}
		}
		
		int row = inspectionTaskSchemeMapper.insertInspectionScheme(inspectionTaskSchemes);
		System.err.println("巡检方案插入数据量："+row);
		return taskIds;
	}
	
	/**
	 * 生成巡检点实例
	 * @param schemeId
	 * @param taskIds
	 * @return
	 */
	public Map<String, String> insertInspectionTaskPoint(String schemeId,List<String> taskIds, String schemeName) {
		
		Map<String, String> pointIds = new HashMap<String, String>();
		
		List<TaskInspectionPoint> taskInspectionPoints = new ArrayList<TaskInspectionPoint>();
		
		//获取模板数据
		List<InspectionPoint> inspectionPoints = inspectionTaskSchemeMapper.getInspectionPoint(schemeId);
		String taskPointIdStr = getTaskId();
		Long taskPointId = Long.valueOf(taskPointIdStr);
				
		for (int i = 0; i < taskIds.size(); i++) {
			for (int j = 0; j < inspectionPoints.size(); j++) {
				TaskInspectionPoint taskInspectionPoint = new TaskInspectionPoint();
				InspectionPoint inspectionPoint = inspectionPoints.get(j);
				taskPointId += 1;
				
				pointIds.put(taskPointId + "", inspectionPoint.getPointName());
				
				taskInspectionPoint.setTaskPointId(taskPointId + "");
				taskInspectionPoint.setTaskId(taskIds.get(i));
				taskInspectionPoint.setTaskName(schemeName);
				taskInspectionPoint.setTaskPointName(inspectionPoint.getPointName());
				taskInspectionPoint.setTaskPointNum(inspectionPoint.getPointNum());
				taskInspectionPoint.setTaskPointRfid(inspectionPoint.getPointRfid());
				taskInspectionPoint.setTaskPointStarttime(inspectionPoint.getPointCreateTime());
				taskInspectionPoint.setTaskPointOrder(inspectionPoint.getOrdernum());
				taskInspectionPoint.setTaskPointDesc(inspectionPoint.getPointDesc());
				taskInspectionPoint.setRemarkone(inspectionPoint.getRemarkone());
				taskInspectionPoint.setRemarktwo(inspectionPoint.getRemarktwo());
				taskInspectionPoint.setRemarkthree(inspectionPoint.getRemarkthree());
				taskInspectionPoint.setRemarkfour(inspectionPoint.getRemarkfour());
				
				taskInspectionPoints.add(taskInspectionPoint);
				if (taskInspectionPoints.size() == 100) {
					inspectionTaskSchemeMapper.insertTaskInspectionPoint(taskInspectionPoints);
					taskInspectionPoints.clear();
				}
			}
			
		}
		
		Integer row = inspectionTaskSchemeMapper.insertTaskInspectionPoint(taskInspectionPoints);
		System.err.println("巡检点实例生成条数：" + row);
		return pointIds;
		
	}
	
	/**
	 * 生成巡检设备实例
	 * @param pointIds
	 */
	public Map<String, String> insertInspectionTaskContent(Map<String, String> pointIds, String schemeId) {
		Set<String> keys = pointIds.keySet();
		List<String> pointIdKeys = new ArrayList<String>();
		
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			pointIdKeys.add(iterator.next());
		}
		List<TaskInspectionContent> taskInspectionContents = new ArrayList<TaskInspectionContent>();
		HashMap<String, String> contentIds = new HashMap<String, String>();
		
		String contentIdStr = getTaskId();
		Long contentId = Long.valueOf(contentIdStr);
		List<InspectionContent> inspectionContents = inspectionTaskSchemeMapper.getInspectionContent(schemeId);
		int row = 0;
		
		for (int i = 0; i < pointIdKeys.size(); i++) {
			for (int j = 0; j < inspectionContents.size(); j++) {
				
				if ( pointIds.get( pointIdKeys.get(i) ).trim().equals( inspectionContents.get(j).getPointName().trim() ) ) {
					
					row++;
					contentId ++;
					
					TaskInspectionContent taskInspectionContent = new TaskInspectionContent();
					
					InspectionContent inspectionContent = inspectionContents.get(j);
					
					contentIds.put(contentId + "", inspectionContent.getContentName());
					
					taskInspectionContent.setTaskContentId(contentId + "");
					taskInspectionContent.setTaskContentName(inspectionContent.getContentName());
					taskInspectionContent.setTaskPointId(pointIdKeys.get(i));
					taskInspectionContent.setTaskPointName(pointIds.get(pointIdKeys.get(i)));
					taskInspectionContent.setTaskContentOrder(inspectionContent.getOrdernum());
					taskInspectionContent.setRemarktwo(inspectionContent.getContentId());
					taskInspectionContent.setTaskContentDesc(inspectionContent.getRemark());
					
					taskInspectionContents.add(taskInspectionContent);
					if (taskInspectionContents.size() == 100 ) {
						
						inspectionTaskSchemeMapper.insertTaskInspectionContent(taskInspectionContents);
						taskInspectionContents.clear();
					}
				}
			}
		}
		
		System.err.println("+++++++++++++++taskInspectionContents(S)+++++++++++++"+taskInspectionContents.size());
		if (taskInspectionContents.size() > 0) {
			inspectionTaskSchemeMapper.insertTaskInspectionContent(taskInspectionContents);
		}
		
		System.err.println("生成巡检设备数量：" + row);
		return contentIds;
		
	}
	
	/**
	 * 生成巡检项实例
	 * @param pointIds
	 */
	public Integer insertTaskInspectionRequire(String schemeId) {
		
		List<TaskInspectionContent> taskInspectionContents = inspectionTaskSchemeMapper.getTaskInspectionContent(schemeId);
		List<TaskInspectionRequire> taskInspectionRequires = new ArrayList<TaskInspectionRequire>();
		
		
		String requireIdStr = getTaskId();
		Long requireId = Long.valueOf(requireIdStr);
		List<InspectionRequire> inspectionRequires = inspectionTaskSchemeMapper.getInspectionRequire(schemeId);
		System.err.println("----------------------------"+inspectionRequires.size());
		int row = 0;
		for (int i = 0; i < taskInspectionContents.size(); i++) {
			for (int j = 0; j < inspectionRequires.size(); j++) {
				TaskInspectionContent taskInspectionContent = taskInspectionContents.get(i);
				InspectionRequire inspectionRequire = inspectionRequires.get(j);
				
				if (taskInspectionContent.getRemarktwo().trim().equals(inspectionRequire.getContentId().trim())) {
					
					TaskInspectionRequire taskInspectionRequire = new TaskInspectionRequire();
					
					requireId += 1;
					taskInspectionRequire.setTaskRequireId(requireId +"");
					taskInspectionRequire.setTaskContentId(taskInspectionContent.getTaskContentId());
					taskInspectionRequire.setTaskRequireType(Integer.valueOf(inspectionRequire.getRequireType()));
					taskInspectionRequire.setTaskRequireContext(inspectionRequire.getRequireContext());
					taskInspectionRequire.setTaskRequireResDesc(inspectionRequire.getRequireResDesc());
					taskInspectionRequire.setTaskCcsParaId(inspectionRequire.getCcsParaId());
					taskInspectionRequire.setTaskEquPositionNum(inspectionRequire.getEquPositionNum());
					taskInspectionRequire.setTaskRequireItem(inspectionRequire.getRequireItem());
					taskInspectionRequire.setTaskRequireUnit(inspectionRequire.getRequireUnit());
					taskInspectionRequire.setTaskRequireMix(inspectionRequire.getRequireMix());
					taskInspectionRequire.setTaskRequireMax(inspectionRequire.getRequireMax());
					taskInspectionRequire.setTaskRequireRecordtime(inspectionRequire.getRequireRecordtime());
					taskInspectionRequire.setRequireDesc(inspectionRequire.getRequireDesc());
					taskInspectionRequire.setTaskRequireOrder(Integer.valueOf(inspectionRequire.getOrdernum()));
					taskInspectionRequire.setRemarkone(inspectionRequire.getRemarkthree());
					taskInspectionRequire.setRemarktwo(inspectionRequire.getRequireId());
					taskInspectionRequire.setRemarkfour(getIsHour(taskInspectionContent.getTaskStartTime(),inspectionRequire.getRequireRecordtime()));
					taskInspectionRequire.setTaskRequireSymbol(inspectionRequire.getRequireSymbol());
					taskInspectionRequire.setTaskResultStatus("TRUE");
					row++;
					taskInspectionRequires.add(taskInspectionRequire);
					if (taskInspectionRequires.size() == 100) {
						inspectionTaskSchemeMapper.inserttaskInspectionRequires(taskInspectionRequires);
						taskInspectionRequires.clear();
					}
				}
			}
		}
		if (taskInspectionRequires.size()>0) {
			inspectionTaskSchemeMapper.inserttaskInspectionRequires(taskInspectionRequires);
		}
		System.err.println("生成巡检项数量：" + row);
		return row;
		
	}
	
	/**
	 * 删除方案表实例
	 * @param inspectionTaskScheme
	 * @return
	 */
	@Override
	public void deleteInspectionScheme(String schemeId) {
		int row1 = inspectionTaskSchemeMapper.deleteTaskInspectionRequire(schemeId);
		int row2 = inspectionTaskSchemeMapper.deleteTaskInspectionContent(schemeId);
		int row3 = inspectionTaskSchemeMapper.deleteTaskInspectionPoint(schemeId);
		int row4 = inspectionTaskSchemeMapper.deleteTaskInspection(schemeId);
		
		System.err.println("---------------deleteTaskInspectionRequire---------------"+row1);
		System.err.println("---------------deleteTaskInspectionContent---------------"+row2);
		System.err.println("---------------deleteTaskInspectionPoint---------------"+row3);
		System.err.println("---------------deleteTaskInspection---------------"+row4);
	}

	public String getIsHour(Date taskStartTime, String requireRecordtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String hour = sdf.format(taskStartTime);
		
		if (hour.substring(0, 1).equals("0")) {
			hour = hour.substring(1);
		}
		
		if (requireRecordtime == null) {
			return "TRUE";
		}else {
			String[] hours = requireRecordtime.trim().split(",");
			
			for (int i = 0; i < hours.length; i++) {
				
				if (hours[i].equals(hour)) {
					log.info("-------------------任务时间------------："+hour);
					return "TRUE";
				}
			}
		}
		
		
		
		return "FALSE";
	}

	public String getTaskId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSSSSS");
		String d = sdf.format(new Date());
		System.err.println(d);
		return d;
	}
	
	public Date getTime(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date d = null;
		try {
			d = sdf.parse(source);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public Date getTime(String time,int hour) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateStr = null;
		Date d = null;
		try {
			d = sdf1.parse(time);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			String day = String .format("%td", d);
			c.add(Calendar.HOUR, hour);
			Date date = c.getTime();
			String day1 = String .format("%td", date);
			if (day1.equals(day)) {
				dateStr = c.getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}

}

