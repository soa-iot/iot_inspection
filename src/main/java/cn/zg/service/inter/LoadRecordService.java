package cn.zg.service.inter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.zg.utils.JsonResult;

@Service
public interface LoadRecordService {
	/**
	 * 获取表头信息
	 * @param scheme_id
	 * @param date
	 * @return
	 */
	public JsonResult findTableHead(String scheme_id,String date);
	/**
	 * 获取巡检内容
	 * @param scheme_id
	 * @param date
	 * @return
	 */
	public JsonResult findInspectionData(String scheme_id,String date);
	/**
	 * 查询负荷记录方案集合
	 * @param scheme_id
	 * @return
	 */
	public JsonResult findPlans();
}
