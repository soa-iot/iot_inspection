package cn.zg.dao.inter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.zg.entity.daoEntity.Emploee;

/**
 * @ClassName: EmployeeRepository
 * @Description: 员工数据仓库
 * @author zhugang
 * @date 2018年10月5日
 */
public interface EmployeeRepository extends JpaRepository<Emploee, String> {
	
	/**   
	 * @Title: findByEmpName   
	 * @Description: 根据员工名册，查询员工信息   
	 * @param: @param empName
	 * @param: @return      
	 * @return: Emploee        
	 */  
	List<Emploee> findByEmpName( String empName );
	
	/**   
	 * @Title: findByRoleName   
	 * @Description: 根据指定角色，查询角色下用户 
	 * @param: @param roleNameLike
	 * @param: @return      
	 * @return: List<Emploee>        
	 */  
	@Query( nativeQuery = true, 
			value = " SELECT pe.* "
					+ " FROM process_employee pe LEFT JOIN process_employee_role per  "
					+ " ON pe.EMP_ID=per.EMP_ID  LEFT JOIN process_role pr "
					+ " ON per.ROL_ID=pr.ROL_ID "
					+ " WHERE pr.ROL_NAME like :roleNameLike " )
	List<Emploee> findByRoleName( @Param("roleNameLike") String roleNameLike  );
}
