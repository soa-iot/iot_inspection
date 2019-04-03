package cn.zg.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class TableDDLDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public void findDDL() {
		 String tableName = "CZ_PEPOLE_UNIT";
		 String sql = "select dbms_metadata.get_ddl('TABLE','" + tableName + "') ddl from dual";
		 List<Map<String, Object>> list= jdbcTemplate.queryForList(sql);
		 list.forEach(in-> System.out.print(in));
	}
	
	//@Test
	public void findData() {
		String tableName = "CZ_PEPOLE_UNIT";
		String sql = "select * from " + tableName;
		List<Map<String, Object>> list= jdbcTemplate.queryForList(sql);
		list.forEach(in-> System.out.print(in));
	}
	
	@Test
	public void findTablesName(){
		String sql = " select table_name from mobile_table";		
		List<Map<String, Object>> list = jdbcTemplate.queryForList( sql );
		System.out.println(list);
	}
}
