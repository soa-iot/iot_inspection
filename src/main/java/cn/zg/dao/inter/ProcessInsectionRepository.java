package cn.zg.dao.inter;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.zg.entity.daoEntity.ProblemInspection;

/**
 * @ClassName: EmployeeRepository
 * @Description: 流程业务数据仓库
 * @author zhugang
 * @date 2018年10月5日
 */
public interface ProcessInsectionRepository extends JpaRepository<ProblemInspection, String> {
	
	/**   
	 * @Title: findAllByReporter   
	 * @Description: 根据上报人名，查询全部任务  
	 * @param: @param userName
	 * @param: @return      
	 * @return: List<ProblemInspection>        
	 */  
	@Transactional
	public List<ProblemInspection> findAllByReporter( String userName );
	
	/**   
	 * @Title: findAllByReporterAndProblemTypeInAndSort   
	 * @Description: 根据问题类型、上报人，查找问题   
	 * @param: @param userName
	 * @param: @param problemInspections
	 * @param: @param sort
	 * @param: @return      
	 * @return: List<ProblemInspection>        
	 */  
	@Transactional
	public List<ProblemInspection> findAllByReporterAndProblemTypeIn( 
			String userName, List<String> problemTypes, Sort sort );
}
