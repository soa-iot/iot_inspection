package cn.zg.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.ElectricInspection;
import cn.zg.dao.inter.InpectionValueRepos;
import cn.zg.entity.daoEntity.InpectionValue;
import cn.zg.entity.daoEntity.ValueFXHY;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class AnalysisDao {

	@Autowired
    private ValueFXHYMapper vf;
	
	@Test
	public void insert() { 
		ValueFXHY v = new ValueFXHY();
		v.setRequireid("124351");
		java.util.Date time = Calendar.getInstance().getTime();
		java.sql.Date time1 =  new java.sql.Date(time.getTime());
		v.setRecordtime(time1);
		v.setValue( "234");
		v.setValueunit("");
		v.setValuename("参数1");
		v.setUnit("c");
		int i = vf.insert(v);
		System.out.println(i);
	}
}
