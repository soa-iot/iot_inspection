package cn.zg.entity.task;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 临时任务实体类
 * @author Jiang, Hang
 * @date 2019-12-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryTask implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String taskID;                  //主键ID
	private String taskName;                //任务名称
	private String createPerson;            //下达人
	private String departmentName;   //下达部门名称
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;                //下达时间
	private Integer taskType;               //任务类型
	private String equNumber;               //设备位号
	private String equName;                 //设备名称
	private String taskContent;             //任务内容
	private String riskWarning;             //风险提示
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date requireFinishTime;         //要求完成时间
	private Integer remindMinute;           //提前提醒分钟数
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date actualFinishTime;          //实际完成时间
	private String executeDepartmentID;     //执行部门ID
	private String executePerson;           //执行人
	private String taskState;              //任务状态
	private Integer isProblem;              //是否存在问题
	private Integer isFinished;             //是否现场已处理
	private String resultDescribe;          //结果描述
	private String remark;                  //备注
}
