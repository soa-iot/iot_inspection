package cn.zg.service.inter;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.Schemeposition;

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

}
