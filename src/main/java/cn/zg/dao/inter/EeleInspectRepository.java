/**  
 * @Title: EeleInspectRepository.java
 * @Package cn.zg.dao.inter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author zhugang
 * @date 2018年11月8日
 * @version V1.0  
 */
package cn.zg.dao.inter;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zg.entity.daoEntity.InpectionValue;
import cn.zg.entity.daoEntity.Schemeposition;

/**
 * @ClassName: EeleInspectRepository
 * @Description: 电子巡检仓库
 * @author zhugang
 * @date 2018年11月8日
 */
public interface EeleInspectRepository extends JpaRepository<InpectionValue,Integer>{
	
}
