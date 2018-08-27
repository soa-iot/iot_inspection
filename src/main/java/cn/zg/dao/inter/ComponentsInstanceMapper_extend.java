package cn.zg.dao.inter;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.zg.entity.serviceEntity.ComponentsInstanceInfo;

@Mapper
public interface ComponentsInstanceMapper_extend {
	
	/**   
	 * @Title: selectComponInsAndType   
	 * @Description: 查询组件的实例信息，包括对应模板类型  
	 * @param: @return      
	 * @return: List<ComponentsInstanceInfo>        
	 */  
	List<ComponentsInstanceInfo> selectComponInsAndType();
}
