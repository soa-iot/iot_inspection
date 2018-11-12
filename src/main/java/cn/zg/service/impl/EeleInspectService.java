package cn.zg.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.EeleInspectRepository;
import cn.zg.entity.daoEntity.InpectionValue;
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
	
	/**   
	 * @Title: saveInspectValue   
	 * @Description: 保存方案巡检值
	 * @param: @param inpectValues
	 * @param: @return      
	 * @return: int        
	 */  
	public int saveInspectValue( List<InpectionValue> _inpectValues ) {
		logger.debug( "S-保存方案巡检值……" + _inpectValues.toString() );
		long begin = System.currentTimeMillis();
		logger.debug( "S-保存方案巡检值……开始时间：" + System.currentTimeMillis() );
		List<InpectionValue> inpectValues = eleInspectRep.saveAll( _inpectValues );
		long end = System.currentTimeMillis();
		logger.debug( "S-保存方案巡检值……开始时间：" + System.currentTimeMillis() );
		logger.debug( "S-保存方案巡检值……耗时：" + ( end - begin ) );
		if( inpectValues == null || inpectValues.size() == 0 ) {
			return 0;
		}
		return inpectValues.size();
	}
}
