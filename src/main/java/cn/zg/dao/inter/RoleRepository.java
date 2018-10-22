package cn.zg.dao.inter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.zg.entity.daoEntity.Role;

/**
 * @ClassName: RoleRepository
 * @Description: 员工角色信息
 * @author zhugang
 * @date 2018年10月5日
 */
public interface RoleRepository extends JpaRepository<Role, String> {
	
	/**   
	 * @Title: findByEmpId   
	 * @Description: 根据用户id,查询用户角色  
	 * @param: @param empId
	 * @param: @return      
	 * @return: List<Role>        
	 */  
	@Query( nativeQuery = true, 
			value = " select r.* "
					+ " from process_employee_role er"
					+ " left join process_role r "
					+ " on er.rol_id = r.rol_id "
					+ " where er.emp_id = :empId " )
	List<Role> findByEmpId( @Param( "empId" ) String empId);
	
	
}
