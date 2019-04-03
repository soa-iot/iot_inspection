package cn.zg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.zg.ElectricInspection;
import cn.zg.controller.mobile.OracleTableInfoController;
import cn.zg.dao.impl.TableDDLDao;
import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.entity.mobile.TableDataDDLInfo;
import cn.zg.service.impl.PurificationSchemeService;
import cn.zg.service.inter.mobile.OracleTableInfoInter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class TableDDLTest {
	@Autowired
	private OracleTableInfoController oc;

	
	@Test
	public void saveInspectValueContr(){	
		ResultJson<TableDataDDLInfo> a = oc.saveInspectValueContr();
		System.out.println(a.getState());
	}
	
}
