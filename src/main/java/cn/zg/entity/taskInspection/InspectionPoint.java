package cn.zg.entity.taskInspection;


import java.io.Serializable;
import java.util.Date;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: InspectionPlan
 * @Description: 巡检点模板全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class InspectionPoint implements Serializable{
	
	private String rn;
	private String pointId;
	private String pointName;
	private String pointNum;
	private String pointRfid;
	private String pointCreator;
	private Date pointCreateTime;
	private String pointDesc;
	private String remarkone;
	private String remarktwo;
	private String remarkthree;
	private String remarkfour;
	private Integer ordernum;
}
                   