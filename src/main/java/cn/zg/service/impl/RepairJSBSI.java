package cn.zg.service.impl;

import static org.assertj.core.api.Assertions.entry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.HeadconfigJSBMapper;
import cn.zg.dao.ValueJSBMapper;
import cn.zg.entity.daoEntity.HeadConfigJSB;
import cn.zg.entity.daoEntity.ValueJSB;
import cn.zg.service.inter.RepairJSBS;

@Service
public class RepairJSBSI implements RepairJSBS{
	private static Logger logger = LoggerFactory.getLogger( RepairJSBSI.class );
	
	@Autowired
	private HeadconfigJSBMapper headconfigJSB;
	
	@Autowired
	private ValueJSBMapper vm;
	
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
			Map<String, Object> headOrder = new LinkedHashMap<String,Object>();
			headOrder.put( "type", "numbers" );
			headOrder.put( "width", "6%" );
			headOrder.put( "title", "序号" );
			heads.add( headOrder );
			Map<String, Object> headEquip = new LinkedHashMap<String,Object>();
			headEquip.put( "field", "equipName" );
			headEquip.put( "title", "装置列名" );
			headEquip.put( "colspan", "1" );
			headEquip.put( "align", "center" );
			headEquip.put( "width", "8%" );
			heads.add( headEquip );
			Map<String, Object> headUnit = new LinkedHashMap<String,Object>();
			headUnit.put( "field", "unitName" );
			headUnit.put( "title", "单元名称" );
			headUnit.put( "colspan", "1" );
			headUnit.put( "width", "15%" );
			headUnit.put( "align", "center" );
			heads.add( headUnit );
			
			for( HeadConfigJSB h : headJSB ) {
				Map<String, Object> head = new LinkedHashMap<String,Object>();
				head.put( "field", h.getRequireid() );
				head.put( "title", h.getProjectName() );
				head.put( "colspan", h.getColspan() );
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
	
	/**   
	 * @Title: getValueJSB   
	 * @Description:   获取维修方案-静设备、工艺管道值   
	 * @return: List<Object>        
	 */  
	@Override
	public List<Object> getValueJSB( String planId, String time ) {
		List<Object> values = new ArrayList<Object>();	
		LinkedHashMap<String, Object> flag = new LinkedHashMap<String,Object>();
		try {
			List<ValueJSB> valueJSB = vm.findByPlanIdTime( planId, time );
			String tempStr = "";
			logger.debug( valueJSB.toString() );
			//循环防止value值
			for( ValueJSB v : valueJSB ) {
				String tempStr1 = v.getUnitType()==null?"":v.getUnitType().trim() ;
				String tempStr2 = v.getUnitName()==null?"":v.getUnitName().trim();
				tempStr = tempStr1 + tempStr2;
				System.out.println(tempStr);
				if( !flag.containsKey( tempStr )) {	
					HashMap<String, Object> tempMap = new HashMap<String,Object>();
					tempMap.put( v.getRequireid(), v.getValue() );
					tempMap.put( "equipName", v.getUnitType() );
					tempMap.put( "unitName", v.getUnitName());
					
					flag.put( tempStr, tempMap );					
				}else {
					Object object = flag.get( tempStr );
					Map<String,Object> tempValueMap = (Map<String,Object>)object;
					tempValueMap.put( v.getRequireid(), v.getValue() );			
				}
			}
			logger.debug( flag.toString() );
			
			//循环防止构造返回值
			for( Map.Entry<String, Object> e : flag.entrySet() ) {
				values.add( e.getValue() );
			}
			return values;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**   
	 * @Title: getSchemes   
	 * @Description: 获取此表头配置表中所有的方案    
	 * @return: void        
	 */  
	@Override
	public List<Map<String,Object>> getPlans() {
		try {
			List<Map<String, Object>> plans = headconfigJSB.selectPlanId();
			logger.debug( plans.toString() );
			//增加获取plan对应的方案的名称
			return plans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**   
	 * @Title: getDaysByMonS   
	 * @Description:  根据月份查询有值的具体日期  
	 * @return: List<Map<String,Object>>        
	 */
	@Override
	public  List<Map<String,Object>> getDaysByMonS( String time ) {
		try {
			List<Map<String, Object>> days = vm.selectDayByMonth(time);
			logger.debug( days.toString() );
			return days;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
