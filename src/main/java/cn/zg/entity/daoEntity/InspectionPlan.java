package cn.zg.entity.daoEntity;


import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: InspectionPlan
 * @Description: 巡检计划实体类
 * @author Bru.Lo
 * @date 2019年12月24日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class InspectionPlan implements Serializable{
	
	private String rn;
	private String planID;
	private String planName;
	private String schemeID;
	private String schemeName;
	private String planCycle;
	private String planStartTime;
	private String planIntervalTime;
	private String planDesc;
	private String planExpectTime;
	private String planNoticeTime;
	private String planState;
	private String planValue;
	private String remark;
	private String remarkOne;
	private String remarkTwo;
	private String remarkThree;
	private String equUnit;

}
