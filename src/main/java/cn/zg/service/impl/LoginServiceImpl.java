package cn.zg.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.dao.inter.EmployeeRepository;
import cn.zg.dao.inter.OrganizationRepository;
import cn.zg.dao.inter.RoleRepository;
import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.Organization;
import cn.zg.entity.daoEntity.Role;
import cn.zg.service.inter.LoginServiceInter;
import cn.zg.utils.globalUtils.GlobalUtil;

/**
 * @ClassName: LoginServiceImpl
 * @Description: 业务层实现
 * @author zhugang
 * @date 2018年10月4日
 */
@Service
public class LoginServiceImpl implements LoginServiceInter {
	private static Logger logger = LoggerFactory.getLogger( LoginServiceImpl.class );
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/**   
	 * @Title: checkEmployeeInfo   
	 * @Description: 登录验证，并设置session，cookie
	 * @param: @return      
	 * @return: boolean        
	 */  
	public Emploee loginCheck( String empName, String empPassword ) {
		List<Emploee> emploees = employeeRepository.findByEmpName( empName );
		System.out.println(emploees.toString());
		Emploee emploee = emploees.get( 0 );
		if( emploee == null ) {
			logger.debug( "用户不存在" );
			GlobalUtil.throwRuntimeException( "用户不存在" );
		}
		
		/*
		 * 验证,绑定cookie
		 */		
		if( checkLoginInfo( emploee, empName, empPassword ) ) {
			//获取cookie信息：用户角色、组织、权限
			List<Organization> orgList = organizationRepository.findByEmpId( emploee.getEmpId() );
			logger.debug( "orgList:" + orgList.toString() );
			List<Role> rolList = roleRepository.findByEmpId( emploee.getEmpId() );
			logger.debug( "rolList:" + rolList.toString() );	
			
			//绑定cookie信息：用户名字、角色、组织、权限
			GlobalUtil.addCookie( "userName", empName );
			if( orgList != null  && orgList.size() > 0) {
				String orgCookieStr = "";
				for( int i = 0; i < orgList.size(); i++ ) {
					orgCookieStr = orgList.get(i).getOrgName() + ",";
				}
				orgCookieStr = orgCookieStr.substring( 
						0 , orgCookieStr.length()-1 );
				GlobalUtil.addCookie( "userOrganization", orgCookieStr );
			}
			if( rolList != null  && rolList.size() > 0) {
				String rolCookieStr = "";
				for( int i = 0; i < rolList.size(); i++ ) {
					rolCookieStr = rolList.get(i).getRolName() + ",";
				}
				rolCookieStr = rolCookieStr.substring( 
						0 , rolCookieStr.length()-1 );
				GlobalUtil.addCookie( "userRole", rolCookieStr );
			}
		}		
		return emploee;
	}


	/**   
	 * @Title: checkLoginInfo   
	 * @Description: 验证登录名和密码  
	 * @param: @param employee
	 * @param: @param empName
	 * @param: @param empPassword
	 * @param: @return      
	 * @return: boolean        
	 */  
	private boolean checkLoginInfo( Emploee employee, String empName,
			String empPassword) {
		if( empName.trim().equals( employee.getEmpName().trim() ) 
				&& empPassword.trim().equals( employee.getEmpPassword().trim() )) {
			return true;
		}
		return false;
	}
	
	
}
