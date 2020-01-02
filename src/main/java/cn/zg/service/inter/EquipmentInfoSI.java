package cn.zg.service.inter;

import java.util.List;

import cn.zg.entity.EquipmentInfo;

public interface EquipmentInfoSI {
	
	/**   
	 * @Title: getEquipmentInfo   
	 * @Description: 根据条件查找出设备信息数据
	 * @return: List<EquipmentInfo> 返回设备数据列表   
	 */
	public List<EquipmentInfo> getEquipmentInfo(EquipmentInfo info, Integer page, Integer limit);
	
	
	/**   
	 * @Title: countEquipmentInfo   
	 * @Description: 根据条件查统计设备信息条数
	 * @return: List<EquipmentInfo> 返回条数   
	 */
	public Integer countEquipmentInfo(EquipmentInfo info);

}
