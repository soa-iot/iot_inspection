package cn.zg.dao.inter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

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
	List<Schemeposition> findByInspectionName( String inspectionName );
}
