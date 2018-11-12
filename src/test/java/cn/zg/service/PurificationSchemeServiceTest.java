package cn.zg.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.start.ElectricInspection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class PurificationSchemeServiceTest {
	@Autowired
	private PurificationSchemeService pss;
	
	@Test
	public void getTableHeadTest() {
		String inspectionName = "Ⅰ列1100#-1400#现场巡检参数记录";
		List<Object> list =  pss.getTableHead( inspectionName );
		//List<Schemeposition> list = psd.findAll();
		System.out.println( list );
	}
	
}
