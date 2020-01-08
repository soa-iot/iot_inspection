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
 * @Description: 巡检点实例全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class TaskInspectionPoint implements Serializable{
	
	private String rn;
	private String taskPointId;
	private String taskId;
	private String taskName;
	private String taskPointName;
	private String taskPointNum;
	private String taskPointRfid;
	private Date taskPointStarttime;
	private Date taskPointEndtime;
	private String taskPointState;
	private Integer taskPointOrder;
	private String taskPointDesc;
	private String remarkone;
	private String remarktwo;
	private String remarkthree;
    private String remarkfour;
}
                   