package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.ComponentsInstanceMapper_extend; 
import cn.zg.entity.serviceEntity.ComponentsInstanceInfo;
import cn.zg.service.inter.ComponentsServiceInter;
import cn.zg.utils.globalUtils.GlobalUtil;

@Service
public class ComponentsServiceImpl implements ComponentsServiceInter{
	private static Logger logger = LoggerFactory.getLogger(ComponentsServiceImpl.class);
	
	/**   
	 * @Fields layoutTemplateMapper : 组件连接数据库接口
	 */  
	@Autowired
	private ComponentsInstanceMapper_extend componentsIns_extend;
	
	/**   
	 * @Title: getComponInsAndTypeService   
	 * @Description: 获取组件的实例信息，包括对应模板类型   
	 * @param: @return      
	 * @return: List<ComponentsInstanceInfo>        
	 */  
	public List<ComponentsInstanceInfo> getComponInsAndTypeService(){
		List<ComponentsInstanceInfo> returnList = new ArrayList<ComponentsInstanceInfo>();
		returnList = componentsIns_extend.selectComponInsAndType();
		logger.debug("getLayoutTemplateService:", returnList);
		if(returnList == null || returnList.size() < 1) {
			GlobalUtil.throwRuntimeException("组件信息为空");
		}
		return returnList;
	}
}
