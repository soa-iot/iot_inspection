package cn.zg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.LayoutTemplate;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.LayoutServiceImpl;
import cn.zg.service.inter.LayoutServiceInter; 

@RestController
@RequestMapping("/layoutModel")
public class LayoutController {
	private static Logger logger = LoggerFactory.getLogger(LayoutServiceImpl.class);
	
	@Autowired
	private LayoutServiceInter layoutServiceInter;
	
	/**   
	 * @Title: getLayoutController   
	 * @Description: 获取所有布局模板信息   
	 * @param: @return      
	 * @return: ResultJson<List<LayoutTemplate>>        
	 */  
	@RequestMapping("/layout/all")
	public ResultJson<List<LayoutTemplate>> getLayoutController() {
		List<LayoutTemplate> returnList = layoutServiceInter.getLayoutTemplateService();
		logger.debug("LayoutController返回returnList：" + returnList);
		return new ResultJson<List<LayoutTemplate>>(returnList);
	}
	

}
