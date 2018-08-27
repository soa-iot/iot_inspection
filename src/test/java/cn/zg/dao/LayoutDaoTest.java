package cn.zg.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.ComponentsInstanceMapper;
import cn.zg.dao.inter.ComponentsInstanceMapper_extend;
import cn.zg.dao.inter.LayoutTemplateMapper;
import cn.zg.entity.daoEntity.LayoutTemplate;
import cn.zg.entity.serviceEntity.ComponentsInstanceInfo;
import cn.zg.start.H5PageGenerateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class LayoutDaoTest {
	@Autowired
	private LayoutTemplateMapper layoutTemplateMapper;

	@Autowired
	private ComponentsInstanceMapper_extend componentsInstanceMapper_extend;
	
	//@Test
	public void selectAll(){
		List<LayoutTemplate> list = new ArrayList<LayoutTemplate>();
		list = layoutTemplateMapper.selectAll();
		System.out.println(list);
	}
	
	@Test
	public void selectComponInsAndType() {
		List<ComponentsInstanceInfo> l = componentsInstanceMapper_extend.selectComponInsAndType();
		System.out.println(l);
	}
}
