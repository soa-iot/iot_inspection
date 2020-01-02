package cn.zg.task;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zg.dao.TemporaryTaskDao;
import cn.zg.entity.PageInfo;
import cn.zg.entity.task.TemporaryTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemporaryTaskTest {
	
	@Autowired
	private TemporaryTaskDao taskDao;
	
	@Test
	public void selectTaskList() {
		PageInfo<TemporaryTask> info = taskDao.selectTaskCount(null);
		System.err.println("-------------");
		System.err.println(info);
		List<TemporaryTask> list = taskDao.selectTaskList(null, null, null);
		Date date = list.get(0).getCreateTime();
		System.err.println("-------------");
		System.err.println(list);
		System.err.println(date);
	}
}
