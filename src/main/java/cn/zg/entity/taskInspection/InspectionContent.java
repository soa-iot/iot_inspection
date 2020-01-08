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
 * @Description: 巡检设备模板全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class InspectionContent implements Serializable{

	private String pointName;
	private String contentId;
	private String contentName;
	private String contentCreator;
	private Date contentCreateTime;
	private String remark;
	private String remarkone;
	private String remarktwo;
	private String remarkthree;
	private Integer ordernum;
	private String equPositionNum;
	private String equName;

}
