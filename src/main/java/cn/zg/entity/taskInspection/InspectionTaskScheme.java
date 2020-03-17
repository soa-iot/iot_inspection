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
 * @Description: 方案实例全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class InspectionTaskScheme implements Serializable{
	
	private String rn;
	private String taskId;
	private String taskteamId;
	private String taskteamName;
	private String schemeId;
	private String equUnit;
	private String schemeName;
	private String piid;
	private String taskName;
	private String taskDescribe;
	private String taskResult;
	private String taskState;
	private Date taskDeadline;
	private String isdownload;
	private Date taskStartTime;
	private String taskFinisher;
	private Date taskEndworkDate;
	private String plannedCompletionTime;
    private String reminderTime;
    private String remarkone;
    private String remarktwo;
    private String remarkthree;
    private String remarkfour;
    private String schemeType;
}
                   