
/**
 * <一句话功能描述>
 * <p> 人员组织树控制层
 * @author 陈宇林
 * @version [版本号, 2019年6月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.ResponseObject;
import cn.zg.entity.UserOrganization;
import cn.zg.service.inter.UserOrganizationTreeService;

@RestController
@RequestMapping("/userOrganizationTree")
public class UserOrganizationTreeController {

	@Autowired
	private UserOrganizationTreeService userOrganizationTreeService;

	/**
	 * 获取人员组织树的数据
	 * 
	 * @return
	 */
	@RequestMapping("/userOrganizationTreeData")
	public ResponseObject<List<UserOrganization>> userOrganizationTreeData() {

		ResponseObject<List<UserOrganization>> resObj;
		try {
			List<UserOrganization> result = userOrganizationTreeService.getUserOrganizationTreeData();
			resObj = new ResponseObject<List<UserOrganization>>(0, "success", result);
		} catch (Exception e) {
			e.printStackTrace();
			resObj = new ResponseObject<List<UserOrganization>>(1, "failed>>>" + e.getMessage(), null);
		}

		return resObj;
	}
}
