package cn.zg.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.service.inter.LoadRecordService;
import cn.zg.utils.JsonResult;

/**
 * 
 */

@RestController
public class LoadRecordController {
	@Resource
	private LoadRecordService loadRecordService;
	
	@ResponseBody
	@RequestMapping("/findTableHead.do")
	public JsonResult findAll(String scheme_id,String date){
		return loadRecordService.findTableHead(scheme_id, date);
	}
	
	@ResponseBody
	@RequestMapping("/findTableContent.do")
	public JsonResult findInspectionData(String plan_id,String date){
		return loadRecordService.findInspectionData(plan_id, date);
	}
	
	@ResponseBody
	@RequestMapping("/initPlans.do")
	public JsonResult findPlans(){
		return loadRecordService.findPlans();
	}
	
}
