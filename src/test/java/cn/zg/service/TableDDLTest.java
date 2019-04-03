package cn.zg.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.ElectricInspection;
import cn.zg.dao.impl.TableDDLDao;
import cn.zg.entity.mobile.TableDataDDLInfo;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.mobile.OracleTableInfoInter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class TableDDLTest {
	@Autowired
	private OracleTableInfoInter tableInter;
	
	@Autowired
	private TableDDLDao tableDDLDao;
	
	//@Test
	public void getTablesName(){
		List<Map<String, Object>> tableNamesList = tableDDLDao.findTablesName();
		List<String> nameList = new ArrayList<String>();
		tableNamesList.forEach( m -> 
			System.out.println(m)
				);		
		
	}
	
	@Test
	public void getTableInfo() {
		TableDataDDLInfo returnTableInfo = new TableDataDDLInfo();
		Map<String, String> returnDDL = new HashMap<String,String>();
		HashMap<String, List<Map<String, Object>>> returnData = new HashMap<String,List<Map<String,Object>>>();
		/*
		 * 获取所有配置表名
		 */
		List<String> tablesNameList = new ArrayList<String>();
		try {
			tablesNameList.add( "CZ_PEPOLE_UNIT");
			if( tablesNameList.size() < 0 ) {
				System.out.println(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(11);
		}
				
		/*
		 * 循环查找DDL和表数据
		 */
		try {
			for( int i = 0; i < tablesNameList.size(); i++ ) {
				String tableName = tablesNameList.get( i );
				//获取DDL
				System.out.println( "startTime:" + System.currentTimeMillis() );
				List<Map<String, Object>> originalDDL = tableDDLDao.findDDL(tableName);
				System.out.println( "endTime:" + System.currentTimeMillis() );
				String tempStr = (String)originalDDL.get(0).get("DDL");
				int startIndex = tempStr.indexOf("(");
				int endIndex = tempStr.indexOf("USING");	
				tempStr = tempStr.substring(startIndex, endIndex);
				returnDDL.put( tableName, tempStr );
				System.out.println( "startIndex:" + startIndex );
				System.out.println( "endIndex:" + endIndex );
				System.out.println( returnDDL);
				//获取表data
				List<Map<String, Object>> originalData = tableDDLDao.findData(tableName);
				returnData.put( tableName , originalData );
				
				System.out.println( "returnData");
				System.out.println(returnData);
			}
			returnTableInfo.setDdlMap(returnDDL);
			returnTableInfo.setInfoMap(returnData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
