package cn.zg.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: ResultJson
 * @Description: 前后端交互json
 * @author zhugang
 * @date 2019年1月29日
 * @param <T>
 */
@SuppressWarnings( "serial" )
@Data
@Accessors( chain=true )
public class ResultJsonForTable<T> implements Serializable {
	/**   
	 * @Fields serialVersionUID : 序列化   
	 */  
	private static final long serialVersionUID = 812376774103405857L;

	/**   
	 * @Fields code : 返回状态变量
	 */  
	private int code;
	
	/**   
	 * @Fields msg : 返回消息内容
	 */  
	private String msg;
	
	/**   
	 * @Fields msg : 返回消息数量
	 */  
	private int count;
	
	/**   
	 * @Fields data : 返回数据  
	 */  
	private T data;
	
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:   无参构造  
	 * @param:    
	 */ 
	public ResultJsonForTable() {
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:    3个参数   
	 * @param:  @param code
	 * @param:  @param msg
	 * @param:  @param t  
	 */ 
	public ResultJsonForTable(int code, String msg, int count, T t) {
		this.code = code;
		this.count = count;
		this.msg = msg;
		this.data = t;
	}

	
}