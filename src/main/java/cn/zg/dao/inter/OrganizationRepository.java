package cn.zg.dao.inter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.zg.entity.daoEntity.Organization;

/**
 * @ClassName: OrganizationRepository
 * @Description: 员工组织数据仓库
 * @author zhugang
 * @date 2018年10月5日
 */
public interface OrganizationRepository extends JpaRepository<Organization, String> {
	
	/**   
	 * @Title: findByEmpId   
	 * @Description: 根据员工的id查询员工的组织信息 
	 * @param: @param empId
	 * @param: @return      
	 * @return: List<Organization>        
	 */  
	@Query( nativeQuery = true, 
			value = " select o.* "
					+ " from process_employee_organization eo"
					+ " left join process_organization o "
					+ " on eo.org_id = o.org_id "
					+ " where eo.emp_id = :empId" )
	List<Organization> findByEmpId( @Param( "empId" ) String empId );
}
