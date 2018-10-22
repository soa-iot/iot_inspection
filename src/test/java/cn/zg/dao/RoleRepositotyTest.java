package cn.zg.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.OrganizationRepository;
import cn.zg.dao.inter.RoleRepository;
import cn.zg.entity.daoEntity.Organization;
import cn.zg.entity.daoEntity.Role;
import cn.zg.start.H5PageGenerateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class RoleRepositotyTest {
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void findByEmpIdTest() {
		String empId = "0966154a-f8ad-418d-9079-e7904d72018e";
		List<Role> rList = roleRepository.findByEmpId( empId );
		System.out.println( rList );
	}	
	
}
