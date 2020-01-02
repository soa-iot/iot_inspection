
/**
 * <一句话功能描述>
 * <p>人员组织树业务层实现
 * @author 陈宇林
 * @version [版本号, 2019年6月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.UserOrganizationTreeMapper;
import cn.zg.entity.UserOrganization;
import cn.zg.service.inter.UserOrganizationTreeService;

@Service
public class UserOrganizationTreeServiceImpl implements UserOrganizationTreeService {

	@Autowired
	private UserOrganizationTreeMapper uotMapper;
	
	/**
	 * getUserOrganizationByParentId递归集合属性
	 */
	private List<UserOrganization> list=new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.soa.service.inter.UserOrganizationTreeService#getUserOrganizationTreeData(
	 * )
	 */
	@Override
	public List<UserOrganization> getUserOrganizationTreeData() {

		/**
		 * 查询人员组织树数据
		 */
		List<UserOrganization> userOrganizations = uotMapper.findAll();

		return userOrganizations;
	}
}
