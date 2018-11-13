package cn.zg.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.ElectricInspection;
import cn.zg.service.impl.PurificationSchemeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class InspectValueTest {
	@Autowired
	private PurificationSchemeService pss;
	
	@Test
	public void getInspectData() throws ParseException {
		String planId = "18D178B18F351742FFF421AC60DC9E";
		SimpleDateFormat sdf = new SimpleDateFormat( "YYYY-MM-DD" );
		java.util.Date date = sdf.parse( "2018-11-13" );
		String  time = "2018-11-13";
		List<Map<String,Object>> lists = pss.getInspectData( planId, time ) ;
		System.out.println( lists );
	}
	
}
