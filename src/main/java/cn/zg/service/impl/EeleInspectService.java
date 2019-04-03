package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.impl.EeleInspectDao;
import cn.zg.dao.inter.EeleInspectRepository;
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.InpectionValue;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.service.inter.EeleInspectServiceInter;

/**
 * @ClassName: EeleInspectService
 * @Description: 电子巡检业务层
 * @author zhugang
 * @date 2018年11月8日
 */
@Service
public class EeleInspectService implements EeleInspectServiceInter{
	private static Logger logger = LoggerFactory.getLogger( EeleInspectService.class );
	
	@Autowired
	EeleInspectRepository eleInspectRep;
	
	@Autowired
	EeleInspectDao eleInspectDao;
	
	@Autowired
	PurificationSchemeDao pureSchemeDao;
	
	/**   
	 * @Title: saveInspectValue   
	 * @Description: 保存方案巡检值
	 * @param: @param inpectValues
	 * @param: @return      
	 * @return: int        
	 */  
//	public int saveInspectValue( List<InpectionValue> _inpectValues ) {
//		logger.debug( "S-保存方案巡检值……" + _inpectValues.toString() );
//		long begin = System.currentTimeMillis();
//		logger.debug( "S-保存方案巡检值……开始时间：" + System.currentTimeMillis() );
//		eleInspectRep.flush();
//		//List<InpectionValue> inpectValues = eleInspectRep.saveAll( _inpectValues );
//		int i = new EeleInspectDao().saveEeleInspectAll( _inpectValues );
//		long end = System.currentTimeMillis();
//		logger.debug( "S-保存方案巡检值……开始时间：" + System.currentTimeMillis() );
//		logger.debug( "S-保存方案巡检值……耗时：" + ( end - begin ) );
//		if( inpectValues == null || inpectValues.size() == 0 ) {
//			return 0;
//		}
//		return inpectValues.size();
//	}	
	public int saveInspectValue( List<InpectionValue> _inpectValues ) {
		//logger.debug( "S-保存方案巡检值……" + _inpectValues.toString() );
		long begin = System.currentTimeMillis();
		logger.debug( "S-保存方案巡检值……开始时间：" + System.currentTimeMillis() );
		//List<InpectionValue> inpectValues = eleInspectRep.saveAll( _inpectValues );
		int i = eleInspectDao.saveEeleInspectAll( _inpectValues );
		long end = System.currentTimeMillis();
		logger.debug( "S-保存方案巡检值……结束时间：" + System.currentTimeMillis() );
		logger.debug( "S-保存方案巡检值……耗时：" + ( end - begin ) );
		return i;
	}
	
	/**   
	 * @Title: getPositionsServ   
	 * @Description: 根据装置名，查询所有点位     
	 * @param: @param schemeName
	 * @param: @return      
	 * @return: List<String>        
	 */  
	public List<String> getPositionsServ( String schemeName ){
		List<Schemeposition> lists = pureSchemeDao.findByInspectionName( schemeName );	
		List<String> listStrs = new ArrayList<String>();
		for( Schemeposition s : lists ) {
			listStrs.add( s.getPositionNum().trim() );
		}
		logger.debug( "S- 根据装置名，查询所有点位 -listStrs  ：" + listStrs.toString() );
		return listStrs;
	}
}
