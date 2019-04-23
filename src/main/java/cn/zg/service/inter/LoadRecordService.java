package cn.zg.service.inter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.zg.utils.JsonResult;

@Service
public interface LoadRecordService {
	
	public JsonResult findTableHead(String scheme_id,String date);
	public JsonResult findInspectionData(String scheme_id,String date);
}
