package cn.zg.service.inter;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.Schemeposition;

/**
 * 
 * @ClassName: PurificationSchemeInter
 * @Description: 净化方案业务层接口
 * @author zhugang
 * @date 2018年9月18日
 */
@Service
public interface PurificationSchemeInter {
	/**   
	 * @Title: getTableHead   
	 * @Description: 生成 表格表头数据对象
	 * @param: @param inspectionName
	 * @param: @return      
	 * @return: List<Object>        
	 */  
	List<Object> getTableHead( String inspectionName );
	
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
			throws ParseException ;

	/**   
	 * @Title: getInspectioNames   
	 * @Description:  获取所有的方案信息  
	 * @return: List<Map<String,Object>>        
	 */  
	List<Map<String, Object>> getInspectioNames();

}
