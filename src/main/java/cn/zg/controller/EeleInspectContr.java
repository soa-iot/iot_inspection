package cn.zg.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.InpectionValue;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.EeleInspectService;

@RestController
//@Validated
public class EeleInspectContr {
	private static Logger logger = LoggerFactory.getLogger( EeleInspectContr.class );
	
	@Autowired
	EeleInspectService eleInspService;
	

	/**   
	 * @Title: saveInspectValueContr   
	 * @Description: 保存电子巡检记录值   
	 * @param: @param inpectionValues
	 * @param: @return      
	 * @return: ResultJson<Integer>        
	 */  
	@PostMapping( "/inspectionValue" )
	public ResultJson<Integer> saveInspectValueContr(
			@RequestBody @Valid List<InpectionValue> inpectionValues ){
		//logger.debug( "C-电子巡检记录值  ……" +  inpectionValues.toString() );
		if( inpectionValues.size() == 0 ) {
			return new ResultJson<Integer>( 1, "传入数据为空", 0 );
		}
		int i = eleInspService.saveInspectValue( inpectionValues );
		if( i < 1 ) {
			return new ResultJson<Integer>( 1, "更新数据失败", i );
		}
		return new ResultJson<Integer>( 0, "更新数据成功", i );
	}
}
