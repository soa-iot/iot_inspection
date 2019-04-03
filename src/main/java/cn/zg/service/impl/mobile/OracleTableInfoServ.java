package cn.zg.service.impl.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.impl.TableDDLDao;
import cn.zg.entity.mobile.TableDataDDLInfo;
import cn.zg.service.inter.mobile.OracleTableInfoInter;


/**
 * 
 * @ClassName: OracleTableInfoServ
 * @Description: 表格信息业务层
 * @author zhugang
 * @date 2019年1月7日
 */
@Service
public class OracleTableInfoServ implements OracleTableInfoInter{
	private static Logger logger = LoggerFactory.getLogger( OracleTableInfoServ.class );
	
	@Autowired
	private TableDDLDao tableDDLDao;
	/**
	 * 
	 * @Title: getTableInfoServ   
	 * @Description: 获取表格ddl和数据信息 
	 * @param: @return      
	 * @return: TableDataDDLInfo
	 */
	@Override
	public TableDataDDLInfo getTableInfo() {
		TableDataDDLInfo returnTableInfo = new TableDataDDLInfo();
		Map<String, String> returnDDL = new HashMap<String,String>();
		HashMap<String, List<Map<String, Object>>> returnData = new HashMap<String,List<Map<String,Object>>>();
		/*
		 * 获取所有配置表名
		 */
		List<String> tablesNameList = new ArrayList<String>();
		try {
			tablesNameList = getTablesName();
			logger.debug( "service - tablesNameList - " + tablesNameList);
			if( tablesNameList.size() < 0 ) {
				return null;
			}
		} catch (Exception e) {
			logger.debug( e.toString());
			e.printStackTrace();
			return null;
		}
				
		/*
		 * 循环查找DDL和表数据
		 */
		try {
			for( int i = 0; i < tablesNameList.size(); i++ ) {
				String tableName = tablesNameList.get( i );
				//获取DDL
				List<Map<String, Object>> originalDDL = tableDDLDao.findDDL(tableName);
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
				
			}
			returnTableInfo.setDdlMap(returnDDL);
			returnTableInfo.setInfoMap(returnData);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug( e.toString());
			return null;
		}
		System.out.println( "2222222222222");
		return returnTableInfo;
	}
	
	/**
	 * 
	 * @Title: getTablesName   
	 * @Description: 获取所有配置表   
	 * @param: @return      
	 * @return: List<String>
	 */
	public List<String> getTablesName(){
		List<Map<String, Object>> tableNamesList = tableDDLDao.findTablesName();
		List<String> nameList = new ArrayList<String>();
		tableNamesList.forEach( m -> 
			nameList.add( (String)m.get("TABLE_NAME") )
				);		
		return nameList;
	}
	
	
}
