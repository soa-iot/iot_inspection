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
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.Point;
import cn.zg.entity.daoEntity.Schemeposition;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class CzInspectionPointDao {
	@Autowired
	private CzInspectionMapper psd;
	
	@Test
	public void selectTaskId() {
		
		List<Point> list =  psd.selectTaskId("检维修变电站负荷记录");
		System.err.println( list );
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 1; j < 3; j++) {
				
				Point point = list.get(i);
				point.setTask_point_id(new Date().getTime()+"");
				point.setTask_point_name("检维修变电站负荷巡检点"+j);
				point.setTask_point_starttime(new Date());
				point.setTask_pointorder("12"+i);
				Integer row = psd.insertPoint(point );
			}
			
		}
		
		System.err.println( "完成" );
	}
	
}
