package cn.zg.service.inter;

import java.util.List;

import cn.zg.entity.daoEntity.InpectionValue;

/**
 * @ClassName: EeleInspectServiceInter
 * @Description: 电子巡检业务层接口
 * @author zhugang
 * @date 2018年11月8日
 */
public interface EeleInspectServiceInter {
	/**   
	 * @Title: saveInspectValue   
	 * @Description: 保存方案巡检值
	 * @param: @param inpectValues
	 * @param: @return      
	 * @return: int        
	 */  
	public int saveInspectValue( List<InpectionValue> _inpectValues );
	
	/**   
	 * @Title: getPositionsServ   
	 * @Description: 根据装置名，查询所有点位     
	 * @param: @param schemeName
	 * @param: @return      
	 * @return: List<String>        
	 */  
	public List<String> getPositionsServ( String schemeName );
}
