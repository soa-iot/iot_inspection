package cn.zg.service.inter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.entity.daoEntity.ValueFXHY;

@Service
public interface AnalysisS {

	/**   
	 * @Title: getPointType   
	 * @Description: 获取分析方案的巡检单元种类    
	 * @return: List<String>        
	 */  
	List<String> getPointType(  String name  );

	/**   
	 * @Title: getProjectType   
	 * @Description: 获取分析方案的巡检项目种类 
	 * @return: List<String>        
	 */  
	List<String> getProjectType( String name, String pointName );

	/**   
	 * @Title: getHeadConfigS   
	 * @Description: 获取分析方案的表头信息   
	 * @return: List<Object>        
	 */  
	List<Object> getHeadConfigS(String planName, String pointName, String projectName);

	/**   
	 * @Title: formatHeadConfigS   
	 * @Description: 格式化表头信息  
	 * @return: List<Object>        
	 */  
	List<Object> formatHeadConfigS(List<Schemeposition> tableHeadInfoList);

	/**   
	 * @Title: saveValueS   
	 * @Description: 保存分析化验值    
	 * @return: int        
	 */  
	int saveValueS(List<ValueFXHY> fvalues);

	/**   
	 * @Title: getValueByUnitPnameTimeS   
	 * @Description: 根据巡检单元、巡检项目、时间 查询方案数据     
	 * @return: LinkedList<Object>        
	 */  
	LinkedList<Object> getValueByUnitPnameTimeS(String stime, String etime, String uname, String pname);

	/**   
	 * @Title: getHeadConfigS1   
	 * @Description: 获取分析方案的表头信息   
	 * @return: List<Object>        
	 */  
	List<Object> getHeadConfigS1(String planName, String pointName, String projectName);

	/**   
	 * @Title: formatHeadConfigS1   
	 * @Description: 格式化表头信息  
	 * @return: List<Object>        
	 */  
	List<Object> formatHeadConfigS1(List<Schemeposition> tableHeadInfoList);

}
