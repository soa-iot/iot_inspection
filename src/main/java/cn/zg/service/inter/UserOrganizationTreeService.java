
/**
 * <一句话功能描述>
 * <p>人员组织树业务层接口
 * @author 陈宇林
 * @version [版本号, 2019年6月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.inter;

import java.util.List;
import cn.zg.entity.UserOrganization;


public interface UserOrganizationTreeService {
	
	/**
	 * 获取人鱼组织树的数据
	 * @return
	 */
	public List<UserOrganization> getUserOrganizationTreeData();
	
}
