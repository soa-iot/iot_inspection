package cn.zg.dao.inter;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.zg.entity.daoEntity.Schemeposition;

/**
 * 
 * @ClassName: PurificationSchemeDao
 * @Description: 净化方案Dao层
 * @author zhugang
 * @date 2018年9月18日
 */

public interface PurificationSchemeDao extends JpaRepository< Schemeposition , String >{
	
	
	/**   
	 * @Title: findAllByInspectionName   
	 * @Description: 根据方案名称查询表头信息  
	 * @param: @return      
	 * @return: List<Schemeposition>        
	 */  
	@Query( nativeQuery = true,
			value = " select * from CZ_INSPECTION_HEADCONFIG "
					+ "  where INSPECTIONNAME = :inspectionName "
					+ "  Order BY CHECKPOSITON")
	List<Schemeposition> findByInspectionName( @Param( "inspectionName" )  String inspectionName );
	
	/**   
	 * @Title: findByName   
	 * @Description:  根据方案id、巡检单元、巡检方案名称查询方案信息 
	 * @return: List<Schemeposition>        
	 */  
	@Query( nativeQuery = true,
			value = " select * from IOT_INSPECTION_HEADCONFIG_FXHY "
					+ "  where INSPECTIONNAME = :inspectionName"
					+ "    and CHECKPOSITON = :pointName "
					+ "    and PROJECTNAME1 = :projectName "
					+ "  Order BY CHECKPOSITON")
	List<Schemeposition> findByPlanPointName( 
			@Param( "inspectionName" )  String inspectionName,
			@Param( "pointName" )  String pointName,
			@Param( "projectName" )  String projectName);
	
}
