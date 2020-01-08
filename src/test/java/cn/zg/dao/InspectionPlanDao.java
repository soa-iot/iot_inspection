package cn.zg.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.ElectricInspection;
import cn.zg.entity.daoEntity.InspectionPlan;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class InspectionPlanDao {

	@Autowired
    private InspectionPlanMapper mapper;
	
	@Test
	public void findInspectionPlanAlltest() { 
		InspectionPlan inspectionPlan = new InspectionPlan();
		//inspectionPlan.setPlanName("单元现场巡检");
		List<InspectionPlan> inspectionPlans = mapper.findInspectionPlanAll(inspectionPlan, 0, 20);
		System.err.println(inspectionPlans);
		System.err.println(inspectionPlans.size());
	}
	
	@Test
	public void insertInspectionPlanTest() { 
		InspectionPlan inspectionPlan = new InspectionPlan();
		inspectionPlan.setPlanName("单元现场巡检");
		Integer row = mapper.insertInspectionPlan(inspectionPlan);
		System.err.println(row);
	}
	
	@Test
	public void updateInspectionPlanTest() { 
		InspectionPlan inspectionPlan = new InspectionPlan();
		inspectionPlan.setSchemeName("单元现场巡检");
		inspectionPlan.setPlanID("3B5D02BB446F4B1FA8E4C4CDF4996360");
		Integer row = mapper.updateInspectionPlan(inspectionPlan);
		System.err.println(row);
	}
	
	@Test
	public void testAA() { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String d = sdf.format(new Date());
		System.err.println(d);
	}
}
