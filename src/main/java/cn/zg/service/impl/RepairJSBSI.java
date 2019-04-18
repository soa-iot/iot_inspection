package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.HeadconfigJSB;
import cn.zg.dao.impl.AnalysisDao;
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.HeadConfigJSB;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.entity.serviceEntity.CheckPosition;
import cn.zg.entity.serviceEntity.DataRange;
import cn.zg.entity.serviceEntity.Position;
import cn.zg.entity.serviceEntity.PositionNum;
import cn.zg.entity.serviceEntity.ProjectName1;
import cn.zg.entity.serviceEntity.ProjectName2;
import cn.zg.entity.serviceEntity.Requireid;
import cn.zg.entity.serviceEntity.Unit;
import cn.zg.service.inter.AnalysisS;
import cn.zg.service.inter.RepairJSBS;

@Service
public class RepairJSBSI implements RepairJSBS{
	private static Logger logger = LoggerFactory.getLogger( RepairJSBSI.class );
	
	@Autowired
	private HeadconfigJSB headconfigJSB;
	
	/**   
	 * @Title: getHeadJSB   
	 * @Description: 查询维修方案-静设备、工艺管道 表头  
	 * @return: List<Map<String,Object>>        
	 */  
	@Override
	public List<Map<String, Object>> getHeadJSB( String planId ) {
		List<Map<String, Object>> heads = new ArrayList<Map<String, Object>>();		
		try {
			List<HeadConfigJSB> headJSB = headconfigJSB.findAll( planId );
			logger.debug( headJSB.toString() );
			Map<String, Object> headEquip = new LinkedHashMap<String,Object>();
			headEquip.put( "flied", "equipName" );
			headEquip.put( "title", "装置列名" );
			headEquip.put( "colspan", "1" );
			headEquip.put( "align", "center" );
			heads.add( headEquip );
			Map<String, Object> headUnit = new LinkedHashMap<String,Object>();
			headUnit.put( "flied", "unitName" );
			headUnit.put( "title", "单元名称" );
			headUnit.put( "colspan", "1" );
			headUnit.put( "align", "center" );
			heads.add( headUnit );
			
			for( HeadConfigJSB h : headJSB ) {
				Map<String, Object> head = new LinkedHashMap<String,Object>();
				head.put( "flied", h.getRequireid() );
				head.put( "title", h.getProjectName() );
				head.put( "colspan", h.getHsid() );
				head.put( "align", "center" );
				heads.add( head );
			}
			logger.debug( heads.toString() );
			return heads;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
