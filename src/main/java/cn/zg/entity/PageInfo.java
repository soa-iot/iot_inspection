package cn.zg.entity;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页实体类
 * @author Jiang,Hang
 * @date 2019-12-25
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int page;     //当前页数
	private int limit;    //每页条数
	private int count;    //数据总数
	private int finishCount;     //完成条数
	private int unFinishCount;    //未完成条数
	private int overFinishCount;  //超期未完成条数
	private List<T> list;   //数据集合
}
