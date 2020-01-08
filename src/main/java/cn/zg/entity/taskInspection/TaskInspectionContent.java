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
 * @Description: 巡检设备实例全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class TaskInspectionContent implements Serializable{
	
	private Date taskStartTime;
	private String taskContentId;
	private String taskContentName;
	private String taskPointId;
	private String taskPointName;
	private String taskContentStarttime;
	private String taskContentEndtime;
	private String taskContentState;
	private Integer taskContentOrder;
	private String taskContentDesc;
	private String remarkone;
	private String remarktwo;
	private String remarkthree;
	private String remarkfour;
	private String planStartTime;
	private Integer planIntervalTime;
	private String equPositionNum;
	private String equName;
}
