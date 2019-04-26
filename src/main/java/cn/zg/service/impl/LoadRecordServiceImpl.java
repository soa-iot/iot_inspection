package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zg.dao.impl.LoadRecordDao;
import cn.zg.entity.daoEntity.Test;
import cn.zg.service.inter.LoadRecordService;
import cn.zg.utils.JsonResult;

@Service
public class LoadRecordServiceImpl implements LoadRecordService{
	
	@Resource
	private LoadRecordDao  loadRecordDao;

	/**
	 * 封装检维修变电站负荷记录表数据包含表头和表内容
	 */
	@Override
	public JsonResult findTableHead(String scheme_id,String date) {
		//查询表头
		String tableName = loadRecordDao.findTableName(scheme_id);
		//查询巡检时间
		List<Map<String, Object>> lists = loadRecordDao.findInspectionTime(scheme_id);
		String[]  inspectionTimes=new String[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			inspectionTimes[i]=(String)lists.get(i).get("REQUIRE_RECORDTIME");
		}
		Test test = new Test();
		test.setInspectionTimes(inspectionTimes);
		test.setTableName(tableName);
		return new JsonResult(test);
	}

	@Override
	public JsonResult findInspectionData(String plan_id, String date) {
		List<Map<String, Object>> InspectionDatas = loadRecordDao.findInspectionData(plan_id,date);
		List<String> InspectionNames = loadRecordDao.findInspectionNameByPlanId(plan_id, date);
		List<Map<String, Object>> lists=new ArrayList<Map<String, Object>>();
		for (String InspectionName : InspectionNames) {
			String POSITION_NAME=InspectionName;
			Map<String, Object> maps=new HashMap<String, Object>();
			maps.put("POSITION_NAME", POSITION_NAME);
			for (Map<String, Object> map : InspectionDatas) {
				String CURRENT_POSITION_NAME=(String)map.get("POSITION_NAME");
				if(POSITION_NAME.equals(CURRENT_POSITION_NAME)) {
					//设置上下限值
					String max=(String)map.get("REQUIRE_MAX");//最大值
					String min=(String)map.get("REQUIRE_MIX");//最小值
					String unit=(String)map.get("REQUIRE_UNIT");//单位
					if(max!=null&&min!=null) {//区间的限制
						maps.put("LIMIT_MAX", min+unit+"-"+max+unit);
					}else if(max!=null&&min==null) {//只有上限值
						maps.put("LIMIT_MAX",max+unit);
					}else {//只有下限值
						maps.put("LIMIT_MAX",min+unit);
					}
					maps.put((String)map.get("RECORD_TIME_NUM")+(String)map.get("REQUIRE_CONTEXT"), (String)map.get("VALUE")+unit);
				}
			}
			lists.add(maps);
		}
		return new JsonResult(lists);
	}

	@Override
	public JsonResult findPlans() {
		List<Map<String, Object>> Plans = loadRecordDao.findPlans();
		return new JsonResult(Plans);
	}
	
	
}
