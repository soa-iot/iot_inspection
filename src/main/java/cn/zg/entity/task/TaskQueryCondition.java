package cn.zg.entity.task;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 临时任务查询条件实体类
 * @author Jiang, Hang
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskQueryCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String createStartDate;         //任务下达开始时间
	private String createEndDate;           //任务下达结束时间
	private String requireStartDate;        //要求完成开始时间
	private String requireEndDate;          //要求完成结束时间
	private String finishStartDate;         //任务完成开始时间
	private String finishEndDate;           //任务完成结束时间
	private String taskName;                //任务名称
	private String taskState;               //任务状态
	private String createPerson;            //下达人
	private String executePerson;           //任务完成人
	private String orderField;              //排序字段
	private String orderName;               //排序类型   ASC-正序，DESC-倒序
}
