package cn.zg.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.EmployeeRepository;
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.Emploee;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.start.H5PageGenerateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class EmployeeRepositotyTest {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//@Test
	public void findByEmpNameTest() {
		String empName = "小红";
		List<Emploee> e =  employeeRepository.findByEmpName( empName );
		System.out.println( e.toString() );
	}
	
	@Test
	public void findByRoleNameTest() {
		String roleNameLike = "净化%班班长";
		List<Emploee> e =  employeeRepository.findByRoleName(roleNameLike);
		System.out.println( e.toString() );
	}	
	
}
