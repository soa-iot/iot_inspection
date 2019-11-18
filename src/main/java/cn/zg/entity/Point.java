package cn.zg.entity;

import java.util.Date;

import org.springframework.validation.annotation.Validated;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Validated
public class Point  {

	
	private String task_point_id;
	private String task_id;
	private String task_name;
	private String task_point_name;
	private Date task_point_starttime;
	private String task_pointorder;
	
	
}
