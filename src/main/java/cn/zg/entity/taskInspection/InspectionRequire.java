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
 * @Description: 巡检项模板全表实体类
 * @author Bru.Lo
 * @date 2019年12月30日
 */
@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class InspectionRequire implements Serializable{
	private String requireId;
	private Integer requireType;
	private String requireContext;
	private String requireResDesc;
	private String ccsParaId;
	private String equPositionNum;
	private String requireItem;
	private String requireUnit;
	private String requireMix;
	private String requireMax;
	private String requireRecordtime;
	private String requireCreator;
	private Date requireCreatetime;
	private String requireDesc;
	private String remarkone;
	private String remarktwo;
	private String remarkthree;
	private String remarkfour;
	private Integer ordernum;
	private String contentId;
	private String requireSymbol;
	private String positionPrefix;
	private Integer mulchoice;

}
