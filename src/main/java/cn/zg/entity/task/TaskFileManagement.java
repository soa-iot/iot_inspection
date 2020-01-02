package cn.zg.entity.task;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 临时任务文件管理实体类
 * @author Jiang, Hang
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFileManagement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fID;                         //主键ID
	private String taskID;                      //任务主键ID
	private Integer fileClass;                  //文件类型
	private String fileOriginalName;           //文件原始名
	private String filePath;                   //文件路径
	private String uploadPerson;              //上传人
	private Date uploadTime;                //上传时间

}
