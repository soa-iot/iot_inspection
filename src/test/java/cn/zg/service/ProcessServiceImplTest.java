package cn.zg.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.start.H5PageGenerateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H5PageGenerateApplication.class })
@WebAppConfiguration
public class ProcessServiceImplTest {
	@Autowired
	private RuntimeService runtimeService;
	
	@Test
	public void processInstanceTest() {
		String id = "20";
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId( id )
				.singleResult();
		if( processInstance != null ) {
			System.out.println( processInstance.toString() );
		} 
		
	}
}
