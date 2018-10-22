package cn.zg.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.LoginServiceInter;
import cn.zg.start.H5PageGenerateApplication;
import cn.zg.utils.globalUtils.GlobalUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class LoginServiceTest {
	@Autowired
	private LoginServiceInter loginServiceInter;
	
	@Test
	public void loginCheckTest() {
		Emploee e = loginServiceInter.loginCheck( "小红", "123");
		GlobalUtil.showCookie();
		System.out.println( e );
		
	}
	
}
