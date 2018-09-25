package cn.zg.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.start.H5PageGenerateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class PurificationSchemeDaoTest {
	@Autowired
	private PurificationSchemeDao psd;
	
	@Test
	public void findAllByInspectionNameTest() {
		String inspectionName = "Ⅰ列1100#-1400#现场巡检参数记录";
		List<Schemeposition> list =  psd.findByInspectionName( inspectionName );
		//List<Schemeposition> list = psd.findAll();
		System.out.println( list );
	}
	
}
