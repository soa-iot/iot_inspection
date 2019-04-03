package cn.zg.service.inter.mobile;

import org.springframework.stereotype.Service;

import cn.zg.entity.mobile.TableDataDDLInfo;
/**
 * 
 * @ClassName: OracleTableInfoInter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhugang
 * @date 2019年1月8日
 */

public interface OracleTableInfoInter {
		
	/**
	 * 
	 * @Title: getTableInfo   
	 * @Description: 获取指定表的ddl语句和数据信息  
	 * @param: @return      
	 * @return: TableDataDDLInfo<T>
	 */
	public TableDataDDLInfo getTableInfo();
}
