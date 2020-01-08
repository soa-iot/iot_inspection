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
 * @Description: 巡检项实例全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class TaskInspectionRequire implements Serializable{
	private String taskRequireId;
	private String taskContentId;
	private Integer taskRequireType;
	private String taskRequireContext;
	private String taskRequireResDesc;
	private String taskCcsParaId;
	private String taskEquPositionNum;
	private String taskRequireItem;
	private String taskRequireUnit;
	private String taskRequireMix;
	private String taskRequireMax;
	private String taskRequireRecordtime;
	private String requireDesc;
	private String taskRequireResult;
	private Date taskRequireStarttime;
	private Date taskRequireEndtime;
	private Integer taskRequireOrder;
	private String remarkone;
	private String remarktwo;
	private String remarkthree;
	private String remarkfour;
	private String taskRequireSymbol;
	private String taskResultStatus;
	private Integer mulchoice;
	private String opcValue;
	private String opcPoint;

}
