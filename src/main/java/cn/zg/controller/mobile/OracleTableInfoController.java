package cn.zg.controller.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.entity.mobile.TableDataDDLInfo;
import cn.zg.service.inter.mobile.OracleTableInfoInter;

/**
 * 
 * @ClassName: OracleTableInfo
 * @Description: oracle数据库具体表中信息
 * @author zhugang
 * @date 2019年1月7日
 */
@RestController
@RequestMapping( "/oracle/tables" )
public class OracleTableInfoController {
	private static Logger logger = LoggerFactory.getLogger( OracleTableInfoController.class );
	
	@Autowired
	private OracleTableInfoInter tableInter;
	
	@GetMapping( "/list" )
	public ResultJson<TableDataDDLInfo> saveInspectValueContr(){	
		TableDataDDLInfo returnTableInfo = tableInter.getTableInfo();
		System.out.println();
		if( returnTableInfo == null ) {
			return new ResultJson<TableDataDDLInfo>(0, "资源查询失败", null);		
		}
		return new ResultJson<TableDataDDLInfo>(0, "资源查询成功",returnTableInfo);		
	}
			
}
