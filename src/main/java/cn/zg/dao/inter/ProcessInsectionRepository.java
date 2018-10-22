package cn.zg.dao.inter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.ProblemInspection;

/**
 * @ClassName: EmployeeRepository
 * @Description: 流程业务数据仓库
 * @author zhugang
 * @date 2018年10月5日
 */
public interface ProcessInsectionRepository extends JpaRepository<ProblemInspection, String> {
	

}
