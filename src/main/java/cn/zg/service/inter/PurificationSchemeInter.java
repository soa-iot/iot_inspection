package cn.zg.service.inter;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: PurificationSchemeInter
 * @Description: 净化方案业务层接口
 * @author zhugang
 * @date 2018年9月18日
 */
@Service
public interface PurificationSchemeInter {
	/**   
	 * @Title: getTableHead   
	 * @Description: 生成 表格表头数据对象
	 * @param: @param inspectionName
	 * @param: @return      
	 * @return: List<Object>        
	 */  
	List<Object> getTableHead( String inspectionName );
}
