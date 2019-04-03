package cn.zg.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.InpectionValue;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.EeleInspectService;
import cn.zg.service.inter.EeleInspectServiceInter;

@RestController
//@Validated
public class EeleInspectContr {
	private static Logger logger = LoggerFactory.getLogger( EeleInspectContr.class );
	
	@Autowired
	EeleInspectServiceInter eleInspectInter;
	

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
		int i = eleInspectInter.saveInspectValue( inpectionValues );
		if( i < 1 ) {
			return new ResultJson<Integer>( 1, "更新数据失败", i );
		}
		return new ResultJson<Integer>( 0, "更新数据成功", i );
	}
	
	/**   
	 * @Title: getPositions   
	 * @Description: 根据装置名，查询所有点位  
	 * @param: @param schemeName
	 * @param: @return      
	 * @return: ResultJson<List<String>>        
	 */  
	@GetMapping( "/position/{schemeName}")
	public ResultJson<List<String>> getPositionsContr(
			@PathVariable( "schemeName" ) @NotBlank String schemeName ){
		logger.debug( "C-根据装置名，查询所有点位-chemeName：" + schemeName );
		List<String> strs = eleInspectInter.getPositionsServ( schemeName );
		if( strs.size() < 1) {
			return new ResultJson<List<String>>( 1, "查询数据为空", strs );
		}
		return new ResultJson<List<String>>( 0, "查询数据成功", strs  );
	}
}
