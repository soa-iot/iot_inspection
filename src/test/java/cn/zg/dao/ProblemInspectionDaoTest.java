package cn.zg.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.dao.inter.ProcessInsectionRepository;
import cn.zg.dao.inter.PurificationSchemeDao;
import cn.zg.entity.daoEntity.ProblemInspection;
import cn.zg.entity.daoEntity.Schemeposition;
import cn.zg.start.H5PageGenerateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class ProblemInspectionDaoTest {
	@Autowired
	private ProcessInsectionRepository processInsectionRepository;
	
	//@Test
	public void findAllByReporterTest() {
		String userName = "净班员";
		List<ProblemInspection> problemInspectionLists  =  
				processInsectionRepository.findAllByReporter( userName );
		//List<Schemeposition> list = psd.findAll();
		System.out.println( problemInspectionLists.size());
	}
	
	@Test
	public void findAllByReporterAndProblemtypeInAndSortTest() {
		String userName = "净班员";
		List<String> problemTypes = new ArrayList<String>();
		problemTypes.add( "机电仪问题" );
		problemTypes.add( "净化问题" );
		problemTypes.add( "其他问题" );
		problemTypes.add( "事故隐患问题" );
		Sort sort = new Sort( Sort.Direction.ASC, "problemType" );
		List<ProblemInspection> problemInspectionLists  =  
				processInsectionRepository
				.findAllByReporterAndProblemTypeIn( userName, problemTypes, sort);
		System.out.println( problemInspectionLists );
	}
}
