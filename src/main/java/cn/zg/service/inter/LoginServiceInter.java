package cn.zg.service.inter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.entity.daoEntity.Emploee;


/**
 * @ClassName: LoginServiceInter
 * @Description: 业务层接口
 * @author zhugang
 * @date 2018年10月4日
 */

public interface LoginServiceInter {
	/**   
	 * @Title: loginCheck   
	 * @Description: 登录验证，并设置session，cookie   
	 * @param: @param empName
	 * @param: @param empPassword
	 * @param: @return      
	 * @return: boolean        
	 */  
	public Emploee loginCheck( String empName, String empPassword );
}
