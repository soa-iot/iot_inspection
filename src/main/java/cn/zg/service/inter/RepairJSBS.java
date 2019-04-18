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

}
