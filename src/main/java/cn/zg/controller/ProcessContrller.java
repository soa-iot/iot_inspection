package cn.zg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.MechatronicsProcessServiceImpl;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.ProcessInspectionServiceInter;

/**
 * @ClassName: ProcessContrller
 * @Description: 流程控制层
 * @author zhugang
 * @date 2018年10月11日
 */
@RestController
@RequestMapping( "/process" )
public class ProcessContrller {
	private static Logger logger = LoggerFactory.getLogger( ProcessContrller.class );

	
}
