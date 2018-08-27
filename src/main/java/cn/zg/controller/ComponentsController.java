package cn.zg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.entity.serviceEntity.ComponentsInstanceInfo; 
import cn.zg.service.inter.ComponentsServiceInter; 

@RestController
@RequestMapping("/componentsModel")
public class ComponentsController {
	private static Logger logger = LoggerFactory.getLogger(ComponentsController.class);
	
	/**   
	 * @Fields componentsServiceInter : 组件信息的数据库接口  
	 */  
	@Autowired
	private ComponentsServiceInter componentsServiceInter;
	
	/**   
	 * @Title: getComponInsAndTypeController   
	 * @Description: 获取组件的实例信息，包括对应模板类型      
	 * @param: @return      
	 * @return: ResultJson<List<ComponentsInstanceInfo>>        
	 */  
	@RequestMapping("/component/all")
	public ResultJson<List<ComponentsInstanceInfo>> getComponInsAndTypeController() {
		List<ComponentsInstanceInfo> returnList = componentsServiceInter.getComponInsAndTypeService();
		logger.debug("LayoutController返回returnList：" + returnList);
		return new ResultJson<List<ComponentsInstanceInfo>>(returnList);
	}
}
