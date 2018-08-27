package cn.zg.service.inter;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.LayoutTemplate;
import cn.zg.entity.serviceEntity.ComponentsInstanceInfo;

@Service
public interface LayoutServiceInter {
	/**   
	 * @Title: getLayoutTemplateService   
	 * @Description: 获取所有布局模板  
	 * @param: @return      
	 * @return: List<LayoutTemplate>        
	 */  
	List<LayoutTemplate> getLayoutTemplateService();
	
	
}
