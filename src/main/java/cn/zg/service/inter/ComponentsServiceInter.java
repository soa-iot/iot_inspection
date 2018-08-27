package cn.zg.service.inter;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.zg.entity.serviceEntity.ComponentsInstanceInfo;

@Service
public interface ComponentsServiceInter {
	
	/**   
	 * @Title: getComponInsAndTypeService   
	 * @Description: 查询组件的实例信息，包括对应模板类型    
	 * @param: @return      
	 * @return: List<ComponentsInstanceInfo>        
	 */  
	List<ComponentsInstanceInfo> getComponInsAndTypeService();
}
