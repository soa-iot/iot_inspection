package cn.zg.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.zg.entity.EquipmentInfo;
import cn.zg.entity.ResultJsonForTable;
import cn.zg.service.inter.EquipmentInfoSI;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

/**
 * @ClassName: ReportC
 * @Description: 问题上报  - 业务控制层
 * @author zhugang
 * @date 2019年5月29日
 */
@RestController
@RequestMapping("/equipment")
@Slf4j
public class EquipmentC {
	
	@Autowired
	private EquipmentInfoSI equipmentInfoS;

	/**
	 * @Title: EquipmentInfo   
	 * @Description: 根据条件查询设备信息
	 * @return: ResultJson<List<UnsafeType>> 返回不安全行为数据列表   
	 */
	@PostMapping("/show")
	public ResultJsonForTable<List<EquipmentInfo>> queryProblemInfo(EquipmentInfo info, Integer page, Integer limit){
		log.info("----------开始查询设备信息");
		log.info("查询条件为:{}", info);
		log.info("第几页:{}", page);
		log.info("每页条数:{}", limit);
		
		Integer count = equipmentInfoS.countEquipmentInfo(info);
		List<EquipmentInfo> result = null;
		if(count != null && count > 0) {
			result = equipmentInfoS.getEquipmentInfo(info, page, limit);
		}
		log.info("----------查询设备信息结束");
		
		return new ResultJsonForTable<List<EquipmentInfo>>(0, "查询数据有"+count, count, result);
	}

}
