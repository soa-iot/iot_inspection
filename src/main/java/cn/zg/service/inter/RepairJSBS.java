package cn.zg.service.inter;

import java.util.List;
import java.util.Map;

public interface RepairJSBS {

	/**   
	 * @Title: getHeadJSB   
	 * @Description:  查询维修方案-静设备、工艺管道 表头   
	 * @return: List<Map<String,Object>>        
	 */  
	List<Map<String, Object>> getHeadJSB(String planId);

	/**   
	 * @Title: getValueJSB   
	 * @Description:   获取维修方案-静设备、工艺管道值  
	 * @return: List<Object>        
	 */  
	List<Object> getValueJSB(String planId, String time);

	/**   
	 * @Title: getPlans   
	 * @Description: 获取此表头配置表中所有的方案      
	 * @return: List<Map<String,Object>>        
	 */  
	List<Map<String, Object>> getPlans();

	/**   
	 * @Title: getDaysByMonS   
	 * @Description: 根据月份查询有值的具体日期   
	 * @return: List<Map<String,Object>>        
	 */  
	List<Map<String, Object>> getDaysByMonS(String time);

}
