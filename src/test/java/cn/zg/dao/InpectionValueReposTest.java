package cn.zg.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.ElectricInspection;
import cn.zg.dao.inter.InpectionValueRepos;
import cn.zg.entity.daoEntity.InpectionValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class InpectionValueReposTest {

	@Autowired
	InpectionValueRepos inpectionValueRepos;
	
	@Test
	public void findByPlanidAndTime() throws ParseException {
		String planId = "Ⅰ列现场巡检参数记录";
		SimpleDateFormat sdf = new SimpleDateFormat( "YYYY-MM-DD" );
		java.util.Date date = sdf.parse( "2018-11-21" );
		String  s = "2018-11-21";
		java.sql.Date date1 = new java.sql.Date( date.getTime() );
		List<InpectionValue> list = inpectionValueRepos.findByPlanidAndTime(planId, s);
		System.out.println(list);
	}
}
