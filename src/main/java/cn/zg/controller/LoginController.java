package cn.zg.controller;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.LoginServiceInter;

/**
 * @ClassName: LoginController
 * @Description: 登录控制层
 * @author zhugang
 * @date 2018年10月6日
 */
@RestController
@RequestMapping( "/process/employee" )
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger( LoginController.class );
	
	@Autowired
	private LoginServiceInter loginServiceInter;
	
	/**   
	 * @Title: getEmployee   
	 * @Description: 根据用户姓名查询用户信息   
	 * @param: @return      
	 * @return: ResultJson<Emploee>        
	 */ 

	@GetMapping( value="/{userName}/{userPassword}" )
	//@RequestMapping(value="/{userName}/{password}",method= RequestMethod.GET)
	public ResultJson<Emploee> getEmployeeByUserName ( 
			@PathVariable( "userName" ) @NotBlank String userName ,
			@PathVariable( "userPassword" ) @NotBlank String userPassword 
			){
		Emploee emploee = loginServiceInter.loginCheck( userName, userPassword );
		if( emploee != null ) {
			return new ResultJson<Emploee>( 0 , "登录成功", emploee );
		}else {
			return new ResultJson<Emploee>( 1 , "登录失败，用户名或密码错误", null );
		}
	}
}
