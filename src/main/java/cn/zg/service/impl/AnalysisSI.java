package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.zg.dao.ValueFXHYMapper;
import cn.zg.dao.impl.AnalysisDao;
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.entity.daoEntity.ValueFXHY;
import cn.zg.entity.serviceEntity.CheckPosition;
import cn.zg.entity.serviceEntity.DataRange;
import cn.zg.entity.serviceEntity.Position;
import cn.zg.entity.serviceEntity.PositionNum;
import cn.zg.entity.serviceEntity.ProjectName1;
import cn.zg.entity.serviceEntity.ProjectName2;
import cn.zg.entity.serviceEntity.Requireid;
import cn.zg.entity.serviceEntity.Unit;
import cn.zg.service.inter.AnalysisS;
import io.netty.util.internal.StringUtil;

@Service
public class AnalysisSI implements AnalysisS{
	private static Logger logger = LoggerFactory.getLogger( AnalysisSI.class );
	
	@Autowired
	private AnalysisDao ad;
	
	@Autowired
	private PurificationSchemeDao ps;
	
	@Autowired
	private ValueFXHYMapper vf;
	
	/**   
	 * @Title: getPointType   
	 * @Description:  获取分析方案的巡检单元种类 
	 * @return: List<String>        
	 */  
	@Override
	public List<String> getPointType(  String name  ){
		List<String> points = new ArrayList<String>();
		try {
			List<Map<String, Object>> pointTypes = ad.selectPointType( name );
			logger.debug( pointTypes.toString() );
			for( Map<String, Object> m : pointTypes ) {
				System.out.println(m);
				points.add( (String) m.get( "CHECKPOSITON" ) );
			}
			return points;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**   
	 * @Title: getPointType   
	 * @Description:  获取分析方案的巡检项目种类
	 * @return: List<String>        
	 */  
	@Override
	public List<String> getProjectType( String name, String pointName ){
		List<String> projects = new ArrayList<String>();
		try {
			List<Map<String, Object>> projectTypes = ad.selectProjectType( name, pointName );
			logger.debug( projectTypes.toString() );
			for( Map<String, Object> m : projectTypes ) {
				projects.add( (String) m.get( "PROJECTNAME1" ) );
			}
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**   
	 * @Title: getHeadConfigS   
	 * @Description:  获取分析方案的表头信息 
	 * @return: List<Object>        
	 */  
	@Override
	public List<Object> getHeadConfigS( String planName, String pointName, String projectName){
		logger.debug( "---S--------- 获取分析方案的表头信息--------" );
		try {
			List<Schemeposition> analysisSchemes = ps.findByPlanPointName(planName, pointName, projectName);
			logger.debug( analysisSchemes.toString() );
			//格式化
			List<Object> headConfig = formatHeadConfigS( analysisSchemes );
			logger.debug( headConfig.toString() );
			//格式化
			return headConfig;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**   
	 * @Title: formatHeadConfigS   
	 * @Description:   格式化表头信息
	 * @return: List<Object>        
	 */  
	@Override
	public List<Object> formatHeadConfigS( List<Schemeposition> tableHeadInfoList ){
		try {
			/*
			 * 定义7级动态表头
			 */
			//一级表头-巡检点
			String firstName = "";
			Set<String> pointSet = new HashSet<String>();
			List<CheckPosition> checkPositionList = new ArrayList<CheckPosition>();
			//二级表头-巡检项目名称1
			String secondName1 = "";
			Set<String> project1Set = new HashSet<String>();
			List<ProjectName1> projectName1List = new ArrayList<ProjectName1>();
			//三级表头-巡检项目名称2
			String secondName2 = "";
			List<ProjectName2> projectName2List = new ArrayList<ProjectName2>();
			//四级级表头-单位
			List<Unit> unitList = new ArrayList<Unit>();
			//五级级表头- 范围
			List<DataRange> dataRangeList = new ArrayList<DataRange>();
			//六级级表头- 要求id
			List<Requireid> requireidList = new ArrayList<Requireid>();
			
			//初始信息
			CheckPosition cpInit = new CheckPosition();
			cpInit.setColspan( "1" );
			cpInit.setWidth( 300 );
			cpInit.setTitle( "取样单元" );
			checkPositionList.add( cpInit );
			ProjectName1 projectInit = new ProjectName1();	
			projectInit.setColspan( "1" );
			projectInit.setTitle( "取样名称" );
			projectInit.setWidth( 300 );
			projectName1List.add( projectInit );
			ProjectName2 projectInit2 = new ProjectName2();
			projectInit2.setColspan( "1" );
			projectInit2.setWidth( 300 );
			projectInit2.setTitle( "分析项目" );
			projectName2List.add( projectInit2 );
			Unit unitInit = new Unit();
			unitInit.setColspan( "1" );
			unitInit.setTitle( "单位" );
			unitInit.setWidth( 300 );
			unitList.add( unitInit );
			DataRange dataRangeInit = new DataRange();
			dataRangeInit.setColspan( "1" );
			dataRangeInit.setWidth( 300 );
			dataRangeInit.setTitle( "参考范围" );
			dataRangeList.add( dataRangeInit );
			Requireid requireidInit = new Requireid();
			requireidInit.setField( "-1" ) ;
			requireidInit.setWidth( 300 );
			requireidInit.setTitle( "要求" );
			requireidList.add(requireidInit);

			int num = 400/tableHeadInfoList.size();
			for( Schemeposition s : tableHeadInfoList ) {
				//巡检单元
				if( !pointSet.contains( s.getCheckPosition() ) ) {
					CheckPosition cp = new CheckPosition();
					if( s.getFirstColspan() != null ) {
						cp.setColspan( s.getFirstColspan().toString());
					}else {
						cp.setColspan( "1" );
					}
					cp.setWidth( num );
					cp.setTitle( s.getCheckPosition() );
					checkPositionList.add( cp );
					pointSet.add( s.getCheckPosition() );
				}
				
				//巡检项目名称1
				if( !project1Set.contains( s.getProjectName1() ) ) {
					ProjectName1 project = new ProjectName1();					
					if( s.getSecondColspan1() != null ) {
						project.setColspan( s.getSecondColspan1().toString());					
					}else {
						project.setColspan( "1" );
					}
					project.setWidth( num );
					project.setTitle( s.getProjectName1() );		
					projectName1List.add( project );
					project1Set.add( s.getProjectName1() );
				}
				
				//巡检项目名称2
				ProjectName2 project2 = new ProjectName2();
				project2.setTitle( s.getProjectName2() );
				project2.setWidth( num );
				projectName2List.add( project2 );
				
				//单位
				Unit unit = new Unit();
				unit.setColspan( "1" );
				unit.setWidth( num );
				unit.setTitle( s.getUnit() );
				unitList.add( unit );
				
				//范围
				DataRange dataRange = new DataRange();
				dataRange.setColspan( "1" );
				dataRange.setTitle( s.getDataRange() );
				dataRange.setWidth( num );
				dataRangeList.add( dataRange );				
				
				//要求id
				Requireid requireid = new Requireid();
				requireid.setAlign( "1" );
				requireid.setField( s.getrequireid() );
				requireid.setTitle( s.getProjectName2() );
				requireidList.add( requireid );				
			}
			
			//全部添加
			ArrayList<Object> headList = new ArrayList<Object>();
			headList.add( checkPositionList );
			headList.add( projectName1List );
			headList.add( projectName2List );
			headList.add( unitList );
			headList.add( dataRangeList );
			headList.add( requireidList );
			
			return headList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**   
	 * @Title: getHeadConfigS   
	 * @Description:  获取分析方案的表头信息 
	 * @return: List<Object>        
	 */  
	@Override
	public List<Object> getHeadConfigS1( String planName, String pointName, String projectName){
		logger.debug( "---S--------- 获取分析方案的表头信息--------" );
		try {
			List<Schemeposition> analysisSchemes = ps.findByPlanPointName(planName, pointName, projectName);
			logger.debug( analysisSchemes.toString() );
			//格式化
			List<Object> headConfig = formatHeadConfigS1( analysisSchemes );
			logger.debug( headConfig.toString() );
			//格式化
			return headConfig;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**   
	 * @Title: formatHeadConfigS   
	 * @Description:   格式化表头信息
	 * @return: List<Object>        
	 */  
	@Override
	public List<Object> formatHeadConfigS1( List<Schemeposition> tableHeadInfoList ){
		try {
			/*
			 * 定义7级动态表头
			 */
			//一级表头-巡检点
			String firstName = "";
			Set<String> pointSet = new HashSet<String>();
			List<CheckPosition> checkPositionList = new ArrayList<CheckPosition>();
			//二级表头-巡检项目名称1
			String secondName1 = "";
			Set<String> project1Set = new HashSet<String>();
			List<ProjectName1> projectName1List = new ArrayList<ProjectName1>();
			//三级表头-巡检项目名称2
			String secondName2 = "";
			List<ProjectName2> projectName2List = new ArrayList<ProjectName2>();
			//四级级表头-单位
			List<Unit> unitList = new ArrayList<Unit>();
			//五级级表头- 范围
			List<DataRange> dataRangeList = new ArrayList<DataRange>();
			//六级级表头- 要求id
			List<Requireid> requireidList = new ArrayList<Requireid>();
			
			//初始信息
			CheckPosition cpInit = new CheckPosition();
			cpInit.setColspan( "2" );
			//cpInit.setWidth( 300 );
			cpInit.setTitle( "取样单元" );
			checkPositionList.add( cpInit );
			ProjectName1 projectInit = new ProjectName1();	
			projectInit.setColspan( "2" );
			projectInit.setTitle( "取样名称" );
			//projectInit.setWidth( 300 );
			projectName1List.add( projectInit );
			ProjectName2 projectInit2 = new ProjectName2();
			projectInit2.setColspan( "2" );
			//projectInit2.setWidth( 300 );
			projectInit2.setTitle( "分析项目" );
			projectName2List.add( projectInit2 );
			Unit unitInit = new Unit();
			unitInit.setColspan( "2" );
			unitInit.setTitle( "单位" );
			//unitInit.setWidth( 300 );
			unitList.add( unitInit );
			DataRange dataRangeInit = new DataRange();
			dataRangeInit.setColspan( "2" );
			//dataRangeInit.setWidth( 150 );
			dataRangeInit.setTitle( "参考范围" );
			dataRangeList.add( dataRangeInit );
			Requireid requireidInit1 = new Requireid();
			requireidInit1.setField( "time" ) ;
			requireidInit1.setAlign( "center");
			//requireidInit1.setWidth( 150 );
			requireidInit1.setTitle( "时间" );
			requireidList.add(requireidInit1);
			Requireid requireidInit2 = new Requireid();
			requireidInit2.setField( "equipcol" ) ;
			//requireidInit2.setWidth( 150 );
			requireidInit2.setAlign( "center");
			requireidInit2.setTitle( "装置列" );
			requireidList.add(requireidInit2);

			int num = 400/tableHeadInfoList.size();
			for( Schemeposition s : tableHeadInfoList ) {
				//巡检单元
				if( !pointSet.contains( s.getCheckPosition() ) ) {
					CheckPosition cp = new CheckPosition();
					if( s.getFirstColspan() != null ) {
						cp.setColspan( s.getFirstColspan().toString());
					}else {
						cp.setColspan( "1" );
					}
					//cp.setWidth( num );
					cp.setTitle( s.getCheckPosition() );
					checkPositionList.add( cp );
					pointSet.add( s.getCheckPosition() );
				}
				
				//巡检项目名称1
				if( !project1Set.contains( s.getProjectName1() ) ) {
					ProjectName1 project = new ProjectName1();					
					if( s.getSecondColspan1() != null ) {
						project.setColspan( s.getSecondColspan1().toString());					
					}else {
						project.setColspan( "1" );
					}
					//project.setWidth( num );
					project.setTitle( s.getProjectName1() );		
					projectName1List.add( project );
					project1Set.add( s.getProjectName1() );
				}
				
				//巡检项目名称2
				ProjectName2 project2 = new ProjectName2();
				project2.setTitle( s.getProjectName2() );
				//project2.setWidth( num );
				projectName2List.add( project2 );
				
				//单位
				Unit unit = new Unit();
				unit.setColspan( "1" );
				//unit.setWidth( num );
				unit.setTitle( s.getUnit() );
				unitList.add( unit );
				
				//范围
				DataRange dataRange = new DataRange();
				dataRange.setColspan( "1" );
				dataRange.setTitle( s.getDataRange() );
				//dataRange.setWidth( num );
				dataRangeList.add( dataRange );				
				
				//要求id
				Requireid requireid = new Requireid();
				requireid.setAlign( "center" );
				requireid.setField( s.getrequireid() );
				requireid.setTitle( s.getProjectName2() );
				requireidList.add( requireid );				
			}
			
			//全部添加
			ArrayList<Object> headList = new ArrayList<Object>();
			headList.add( checkPositionList );
			headList.add( projectName1List );
			headList.add( projectName2List );
			headList.add( unitList );
			headList.add( dataRangeList );
			headList.add( requireidList );
			
			return headList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**   
	 * @Title: saveValueS   
	 * @Description: 保存分析化验值  
	 * @return: int        
	 */  
	@Override
	public int saveValueS( List<ValueFXHY> fvalues ) {		
		int i = 0;
		for( ValueFXHY v : fvalues ) {
			try {
				int j = vf.insert( v );
				if( j > 0) i ++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;		

	}
	
	/**   
	 * @Title: getValueByUnitPnameTimeS   
	 * @Description: 根据巡检单元、巡检项目、时间 查询方案数据   
	 * @return: LinkedList<Object>        
	 */  
	@Override
	public LinkedList<Object> getValueByUnitPnameTimeS(
			 String stime, String etime, String uname, String pname){
		try {
			List<ValueFXHY> values = vf.findByUnitPnameTime(stime, etime, uname, pname);
			logger.debug( values.toString());
			Map<String, LinkedHashMap<String, Object>> flagMap = new HashMap<String,LinkedHashMap<String, Object>>();
			for( ValueFXHY v : values ) {
				String flagStr = "" ;
				if( v.getRecordtime() != null &&  v.getEquipcol() != null  ) {
					flagStr = v.getRecordtime().getTime() + v.getEquipcol().trim();
					if( flagMap.containsKey( flagStr )) {
						flagMap.get( flagStr ).put( v.getRequireid() , v.getValue());
						
					}else {
						LinkedHashMap<String, Object> valuesNew = new LinkedHashMap<String, Object>();
						LinkedHashMap<String, Object> formatValues = valuesNew;
						LinkedHashMap<String, Object> tempMap = formatValues;
						tempMap.put( "time",  v.getRecordtime() );
						tempMap.put( "equipcol", v.getEquipcol() );
						tempMap.put( v.getRequireid() , v.getValue() );
						flagMap.put( flagStr, tempMap );
					}
				}else {
					logger.debug( "日期或装置列为null: " + v.getRecordtime() );
					logger.debug( "日期或装置列为null: " + v.getEquipcol() );
				}
				logger.debug( flagMap.toString() );
			}
			
			//返回值
			LinkedList<Object> valuesNew = new LinkedList<Object>();
			for( Map.Entry<String, LinkedHashMap<String, Object>> f : flagMap.entrySet() ) {
				valuesNew.add( f.getValue() );
			}
			logger.debug( valuesNew.toString() );
			return valuesNew;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
