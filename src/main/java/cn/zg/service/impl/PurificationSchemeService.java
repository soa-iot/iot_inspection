package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.entity.serviceEntity.CheckPosition;
import cn.zg.entity.serviceEntity.DataRange;
import cn.zg.entity.serviceEntity.Position;
import cn.zg.entity.serviceEntity.PositionNum;
import cn.zg.entity.serviceEntity.ProjectName1;
import cn.zg.entity.serviceEntity.ProjectName2;
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
	
	
	/**   
	 * @Title: getTableHead   
	 * @Description: 生成 表格表头数据对象
	 * @param: @param inspectionName
	 * @param: @return      
	 * @return: List<Object>        
	 */  
	public List<Object> getTableHead( String inspectionName ) {
		List<Schemeposition> tableHeadInfoList = psd.findByInspectionName( inspectionName );
		logger.debug( "请求表格表头信息" , tableHeadInfoList );
		System.out.println(tableHeadInfoList);
		
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
		pj1.setTitle( "项目号" );
		projectName1List.add( pj1 );
		//三级表头-巡检项目名称2
		ProjectName2 pj2 = new ProjectName2();
		pj2.setColspan( "2" );
		pj2.setTitle( "项目名称" );
		projectName2List.add( pj2 );
		//四级级表头-单位
		Unit un = new Unit();
		un.setColspan( "2" );
		un.setTitle( "单元" );
		unitList.add( un );
		//五级级表头
		DataRange dr = new DataRange();
		dr.setColspan( "2" );
		dr.setTitle( "控制值" );
		dataRangeList.add( dr );
		//六级表头-中控、现场
		Position po = new Position();
		po.setColspan( "2" );
		po.setMinWidth( 120 );
		po.setTitle( "位置" );
		positionList.add( po );
		//七级表头
		PositionNum pn = new PositionNum();
		pn.setField( "timeName" );
		pn.setAlign( "center" );
		pn.setTitle( "时间段" );
		positionNumList.add( pn );
		PositionNum pn1 = new PositionNum();
		pn1.setField( "time" );
		pn1.setAlign( "center" );
		pn1.setTitle( "时间" );
		positionNumList.add( pn1 );
		
		/*
		 * 动态生成指定格式
		 */
		for( Schemeposition s : tableHeadInfoList ) {
		
			//一级表头-巡检点
			if( !s.getCheckPosition().trim().equals( firstName ) ) {
				//状态值修改
				firstName = s.getCheckPosition();		
				
				//构造数据
				CheckPosition c = new CheckPosition();			
				c.setTitle( s.getCheckPosition() );
				c.setColspan( s.getFirstColspan() + "" );
				checkPositionList.add( c );
			}
						
			//二级表头-巡检项目名称1
			if( !s.getProjectName1().trim().equals( secondName1 ) ) {
				//状态值修改
				secondName1 = s.getProjectName1();		
				
				//构造数据
				ProjectName1 p = new ProjectName1();
				p.setTitle( s.getProjectName1() );
				p.setColspan( s.getSecondColspan1() + "" );
				projectName1List.add( p );
			}
			
			//三级表头-巡检项目名称2
			if( !s.getProjectName2().trim().equals( secondName2 ) ) {
				//状态值修改
				secondName2 = s.getProjectName1();		
				
				//构造数据
				ProjectName2 p = new ProjectName2();
				p.setTitle( s.getProjectName2() );
				p.setColspan( s.getSecondColspan2() + "" );
				projectName2List.add( p );
			}

			//四级表头-单位
			if( fourthFlag == 0) {
				//状态值修改
				fourthFlag ++;
				
				//构造数据
				Unit u = new Unit();
				u.setTitle( s.getUnit() );
				u.setColspan( s.getFourthColspan() + "" );
				unitList.add( u );
			}else {
				//状态值修改
				fourthFlag --;
			}
			
			//五级表头-数据范围
			if( fivthFlag == 0) {
				//状态值修改
				fivthFlag ++;
				
				//构造数据
				DataRange d = new DataRange();
				d.setTitle( s.getDataRange() );
				d.setColspan( s.getFivthColspan() + "" );
				dataRangeList.add( d );
			}else {
				//状态值修改
				fivthFlag --;
			}
			
			//六级表头-中控、现场
			if( sixthFlag == 0) {
				//状态值修改
				sixthFlag ++;
				
				//构造数据
				Position p = new Position();
				p.setTitle( p.getTitle() );
				positionList.add( p );
			}else {
				//状态值修改
				sixthFlag --;
				
				//构造数据
				Position p = new Position( "中控" );
				p.setTitle( p.getTitle() );
				positionList.add( p );
			}
			
			//七级表头-点位
			PositionNum positionNum = new PositionNum();
			positionNum.setField( s.getPositionNum() );
			positionNum.setTitle( s.getPositionNum() );
			positionNumList.add( positionNum );			
		}
		
		/*
		 * 合并表头数据
		 */
		List<Object> returnList = new ArrayList<Object>();
		returnList.add( checkPositionList );
		returnList.add( projectName1List );
		returnList.add( projectName2List );
		returnList.add( unitList );
		returnList.add( dataRangeList );
		returnList.add( positionList );
		returnList.add( positionNumList );
		
		logger.debug( "格式化表格的表头数据" , returnList );
		System.out.println( returnList );
		return returnList;
	}
}
