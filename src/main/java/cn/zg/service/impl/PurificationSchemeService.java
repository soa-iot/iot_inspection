package cn.zg.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.zg.annotation.CacheNameSpace;
import cn.zg.annotation.QueryCache;
import cn.zg.annotation.QueryCacheKey;
import cn.zg.dao.impl.TableDDLDao;
import cn.zg.dao.inter.InpectionValueRepos;
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.InpectionValue;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.entity.serviceEntity.CheckPosition;
import cn.zg.entity.serviceEntity.DataRange;
import cn.zg.entity.serviceEntity.Position;
import cn.zg.entity.serviceEntity.PositionNum;
import cn.zg.entity.serviceEntity.ProjectName1;
import cn.zg.entity.serviceEntity.ProjectName2;
import cn.zg.entity.serviceEntity.Requireid;
import cn.zg.entity.serviceEntity.Unit;
import cn.zg.service.inter.PurificationSchemeInter;

/**
 * 
 * @ClassName: PurificationSchemeService
 * @Description: 净化方案业务层实现
 * @author zhugang
 * @date 2018年9月18日
 */
@Service
public class PurificationSchemeService implements PurificationSchemeInter{
	private static Logger logger = LoggerFactory.getLogger( PurificationSchemeService.class );
	
	@Autowired
	private PurificationSchemeDao psd;
	
	@Autowired
	private InpectionValueRepos inpectValueRepos;
	
	@Autowired
	private TableDDLDao  tableDDLDao;
	
	
	/**   
	 * @Title: getTableHead   
	 * @Description: 生成 表格表头数据对象
	 * @param: @param inspectionName
	 * @param: @return      
	 * @return: List<Object>        
	 */  
	@Override
	//@QueryCache( nameSpace = CacheNameSpace.SSO_USER )
	//public List<Object> getTableHead( @QueryCacheKey String inspectionName ) {
	public List<Object> getTableHead( String inspectionName ) {
//		List<Schemeposition> tableHeadInfoList = psd.findAll(example, sort).findByInspectionName( inspectionName );
		List<Schemeposition> tableHeadInfoList = psd.findByInspectionName( inspectionName);
		logger.debug( "请求表格表头信息" , tableHeadInfoList );
		
		/*
		 * 格式化为表格表头数据格式
		 */
		List<Object> returnList = formatDataIntoTableHead( tableHeadInfoList );		
		
		logger.debug( "格式化完成的表格表头信息" , tableHeadInfoList );
		return returnList;
	}
	
	/**   
	 * @Title: formatDataIntoTableHead   
	 * @Description: 数据格式化为表格表头数据格式  
	 * @param: @param tableHeadInfoList
	 * @param: @return      
	 * @return: List<Object>        
	 */  
	public List<Object> formatDataIntoTableHead( List<Schemeposition> tableHeadInfoList ) {		
		/*
		 * 定义7级动态表头
		 */
		//一级表头-巡检点
		String firstName = "";
		Set<String> pointSet = new HashSet<String>();
		List<CheckPosition> checkPositionList = new ArrayList<CheckPosition>();
		//二级表头-巡检项目名称1
		String secondName1 = "";
		List<ProjectName1> projectName1List = new ArrayList<ProjectName1>();
		//三级表头-巡检项目名称2
		String secondName2 = "";
		List<ProjectName2> projectName2List = new ArrayList<ProjectName2>();
		//四级级表头-单位
		int fourthFlag = 0;
		List<Unit> unitList = new ArrayList<Unit>();
		//五级级表头
		int fivthFlag = 0;
		List<DataRange> dataRangeList = new ArrayList<DataRange>();
		//六级表头-中控、现场
		int sixthFlag = 0;
		List<Position> positionList = new ArrayList<Position>();
		//七级表头
		List<PositionNum> positionNumList = new ArrayList<PositionNum>();
		//八级表头
		List<Requireid> requireidList = new ArrayList<Requireid>();
		
		/*
		 * 配置左侧表头-初始化
		 */
		//一级表头-巡检点
		CheckPosition cp = new CheckPosition();	
		cp.setColspan( "2" );
		cp.setTitle( "单元" );
		checkPositionList.add( cp );
		//二级表头-巡检项目名称1
		ProjectName1 pj1 = new ProjectName1();
		pj1.setColspan( "2" );
		pj1.setTitle( "项目项目" );
		projectName1List.add( pj1 );
		//三级表头-巡检项目名称2
//		ProjectName2 pj2 = new ProjectName2();
//		pj2.setColspan( "2" );
//		pj2.setTitle( "项目名称" );
//		projectName2List.add( pj2 );
		//四级级表头-单位
		Unit un = new Unit();
		un.setColspan( "2" );
		un.setTitle( "单位" );
		unitList.add( un );
		//五级级表头
		DataRange dr = new DataRange();
		dr.setColspan( "2" );
		dr.setTitle( "控制值" );
		dataRangeList.add( dr );
		//六级表头-中控、现场
//		Position po = new Position();
//		po.setColspan( "2" );
//		po.setMinWidth( 120 );
//		po.setTitle( "位置" );
//		positionList.add( po );
		//七级表头
		PositionNum pn = new PositionNum();
		pn.setColspan( "2" );
		pn.setAlign( "center" );
		pn.setTitle( "位号" );
		positionNumList.add( pn );
//		PositionNum pn = new PositionNum();
//		pn.setColspan( "1" );
//		pn.setAlign( "center" );
//		pn.setTitle( "时间段" );
//		positionNumList.add( pn );
//		PositionNum pn1 = new PositionNum();
//		pn1.setColspan( "1" );
//		pn1.setAlign( "center" );
//		pn1.setTitle( "时间" );
//		positionNumList.add( pn1 );
		//八级表头
		Requireid rq = new Requireid();
		rq.setField( "timeName" );
		rq.setTitle("时间段");
		requireidList.add( rq );
		Requireid rq1 = new Requireid();
		rq1.setField( "time" );
		rq1.setTitle("巡检要求");
		requireidList.add( rq1 );
		
		/*
		 * 动态生成指定格式
		 */
		for( Schemeposition s : tableHeadInfoList ) {
			
			//一级表头-巡检点			
			if( !pointSet.contains( s.getCheckPosition().trim() ) ) {
				pointSet.add( s.getCheckPosition() );
				//状态值修改
				firstName = s.getCheckPosition();		
				
				//构造数据
				CheckPosition c = new CheckPosition();			
				c.setTitle( s.getCheckPosition() );
				c.setColspan( s.getFirstColspan() + "" );
				checkPositionList.add( c );
			}
						
			//二级表头-巡检项目名称1
//			if( !s.getProjectName1().trim().equals( secondName1 ) ) {
				//状态值修改
//				secondName1 = s.getProjectName1().trim();		
				
				//构造数据
				ProjectName1 p = new ProjectName1();
				p.setTitle( s.getProjectName1() );
//				p.setColspan( s.getSecondColspan1() + "" );
				p.setColspan(  "1" );
				projectName1List.add( p );
//			}
			
			//三级表头-巡检项目名称2
//			if( !s.getProjectName2().trim().equals( secondName2 ) ) {
//				//状态值修改
//				secondName2 = s.getProjectName2().trim();		
				
				//构造数据
//				ProjectName2 p2 = new ProjectName2();
//				p2.setTitle( s.getProjectName2() );
//				p.setColspan( s.getSecondColspan2() + "" );
//				p2.setColspan( "1" );
//				projectName2List.add( p2 );
//			}

			//四级表头-单位
//			if( fourthFlag == 0) {
//				//状态值修改
//				fourthFlag ++;
				
				//构造数据
				Unit u = new Unit();
				u.setTitle( s.getUnit() );
//				u.setColspan( s.getFourthColspan() + "" );
				u.setColspan( "1" );
				unitList.add( u );
//			}else {
//				//状态值修改
//				fourthFlag --;
//			}
			
			//五级表头-数据范围
//			if( fivthFlag == 0) {
//				//状态值修改
//				fivthFlag ++;
				
				//构造数据
				DataRange d = new DataRange();
				d.setTitle( s.getDataRange() );
				d.setColspan( s.getFivthColspan() + "" );
				dataRangeList.add( d );
//			}else {
//				//状态值修改
//				fivthFlag --;
//			}
			
			//六级表头-中控、现场
//			if( sixthFlag == 0) {
//				//状态值修改
//				sixthFlag ++;
//				
//				//构造数据
//				Position pp = new Position();
//				p.setTitle( p.getTitle() );
//				positionList.add( pp );
//			}else {
//				//状态值修改
//				sixthFlag --;
//				
//				//构造数据
//				Position pp = new Position( "中控" );
//				pp.setTitle( pp.getTitle() );
//				positionList.add( pp );
//			}
//			
			//七级表头-点位
			PositionNum positionNum = new PositionNum();
			
			positionNum.setColspan("1");
			positionNum.setTitle( s.getPositionNum() );
			positionNumList.add( positionNum );		
			
			//八级要求id
			Requireid requireid = new Requireid();
			requireid.setField( s.getrequireid() );
			requireid.setTitle( s.getProjectName2() );
			requireid.setAlign( "center" );
			requireidList.add( requireid );
		}
		
		/*
		 * 合并表头数据
		 */
		List<Object> returnList = new ArrayList<Object>();
		returnList.add( checkPositionList );
		returnList.add( projectName1List );
		returnList.add( projectName2List );
		returnList.add( positionNumList );
		returnList.add( unitList );
		returnList.add( dataRangeList );
		returnList.add( positionList );
		
		returnList.add( requireidList );
		
		logger.debug( "格式化表格的表头数据" , returnList );
		return returnList;
	}
	
	/**   
	 * @Title: getInspectData   
	 * @Description: 获取表格展示数据   
	 * @param: @param planId
	 * @param: @param time
	 * @param: @return
	 * @param: @throws ParseException      
	 * @return: List<Map<String,Object>>        
	 */  
	public List<Map<String,Object>> getInspectData( String planId, String time ) 
			throws ParseException {
		//参数格式转化
//		SimpleDateFormat sdf = new SimpleDateFormat( "YYYY-MM-DD" );
//		Date date = sdf.parse( time );
//		java.sql.Date date1 = new java.sql.Date( date.getTime() );		 
		//查询		
		List<InpectionValue> inpectValues = new ArrayList<InpectionValue>();
		try {
			inpectValues = inpectValueRepos.findByPlanidAndTime( planId, time );
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		logger.debug( "S-获取表格展示数据   -inpectValues:" + inpectValues.toString());
		//动态添加当前时间点没有巡检要求-满足layui表格展示要求
		try {
			ArrayList<String> tempRequireids = new ArrayList<String>();
			Date plantime = inpectValues.get(0).getRecord_time();
			for( InpectionValue i : inpectValues ) {
				tempRequireids.add( i.getRemark1() );
			}
			List<Schemeposition> requires = psd.findByInspectionName(planId);
			String plantime1 = inpectValues.get(0).getRemark2();
			for( Schemeposition s : requires ) {
				if ( !tempRequireids.contains( s.getrequireid() )) {
					inpectValues.add( new InpectionValue( (long)1, "", plantime ,s.getPositionNum(), " ", s.getUnit(), s.getrequireid(), plantime1 ));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//分类处理
		String flag =inpectValues.get( 0 ).getRemark2();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime( inpectValues.get( 0 ).getRecord_time() );
//		logger.debug( "查询表格数据……" + cal.toString() );
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> m = new HashMap<String,Object>();
		int length = inpectValues.size();
		String currentHour = "";
		for( int i = 0; i < length; i++ ) {				
			if( i == length-1 || !flag.equals( inpectValues.get( i ).getRemark2().trim() ) ) {				
				flag = inpectValues.get( i ).getRemark2();
				String timeStr = inpectValues.get( i ).getRemark2();
				m.put( "time", timeStr + ":00");
				if( Integer.parseInt( timeStr ) > 8 && Integer.parseInt( timeStr ) < 19  ) {
					m.put( "timeName", "白班" );
				}else {
					m.put( "timeName", "夜班" );
				}
				list.add( m );
				m = null;
				m = new HashMap<String,Object>();
			}
//			cal.setTime( inpectValues.get( i ).getRecord_time() );
//			currentHour = cal.get( Calendar.HOUR_OF_DAY ) + "" ;
//			if( !flag.equals( currentHour )) {
//				flag = currentHour;
//				logger.debug( "查询表格数据……flag:" + flag ); 
//				if( m != null && m.size() > 0) {
//					if( Integer.parseInt( flag ) > 8 && 
//							Integer.parseInt( flag ) < 19 ) {
//						m.put( "timeName", "白班" );
//					}else {
//						m.put( "timeName", "夜班" );
//					}
//					m.put( "time", (Integer.parseInt( flag ) - 2 ) + ":00" );
//					list.add( m );
//				}
//				m = null;
//				m = new HashMap<String,Object>();				
//			}
//			m.put( inpectValues.get( i ).getPosition_num(), inpectValues.get( i ).getValue() );			
			m.put( inpectValues.get( i ).getRemark1(), inpectValues.get( i ).getValue() );
//			if( i == length - 1 ) {
//				if( Integer.parseInt( currentHour ) > 8 && 
//						Integer.parseInt( currentHour ) < 19 ) {
//					m.put( "timeName", "白班" );
//				}else {
//					m.put( "timeName", "夜班" );
//				}
//				m.put( "time", Integer.parseInt( currentHour ) + ":00" );
//				list.add( m );				
//			}			
		}		
		System.out.println( list.size() ); 
		return list;
	}
	
	
	/**   
	 * @Title: getInspectioNames   
	 * @Description:  获取所有的方案信息 
	 * @return: List<Map<String,Object>>        
	 */
	@Override
	public List<Map<String,Object>> getInspectioNames(){
		logger.debug( "---S--------获取所有的方案信息 ---------" ); 
		try {
			List<Map<String,Object>> list = tableDDLDao.findInspectionNames();
			ArrayList<String> ids = new ArrayList<String>();
			for( Map<String,Object> m : list ) {
				ids.add( (String)m.get("INSPECTIONNAME") );
			}
			List<Map<String, Object>> infos = tableDDLDao.findInspectionIdsNames(ids);
			return infos;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("-S---获取所有的方案信息报错 -------");
			return null;
		}
	}
}
