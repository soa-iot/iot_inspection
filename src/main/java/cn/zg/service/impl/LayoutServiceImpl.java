package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.LayoutTemplateMapper;
import cn.zg.entity.daoEntity.LayoutTemplate;
import cn.zg.entity.serviceEntity.ComponentsInstanceInfo;
import cn.zg.service.inter.LayoutServiceInter;
import cn.zg.utils.globalUtils.GlobalUtil;

@Service
public class LayoutServiceImpl implements LayoutServiceInter{
	 static Logger logger = LoggerFactory.getLogger(LayoutServiceImpl.class);
	
	/**   
	 * @Fields layoutTemplateMapper : 布局连接数据库接口
	 */  
	@Autowired
	private LayoutTemplateMapper layoutTemplateMapper;
		
	
	/**   
	 * @Title: getLayoutTemplateService   
	 * @Description: 获取布局模板  
	 * @param: @return      
	 * @return: List<LayoutTemplate>        
	 */  
	public List<LayoutTemplate> getLayoutTemplateService(){
		List<LayoutTemplate> returnList = new ArrayList<LayoutTemplate>();
			returnList = layoutTemplateMapper.selectAll();
			logger.debug("getLayoutTemplateService:", returnList);
			if(returnList == null || returnList.size() < 1) {
				GlobalUtil.throwRuntimeException("布局信息为空");
			}
			return returnList;
	}
	
	
}
