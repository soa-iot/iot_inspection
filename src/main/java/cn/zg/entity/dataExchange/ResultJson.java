package cn.zg.entity.dataExchange;

import java.io.Serializable;

public class ResultJson<T> implements Serializable {
	/**   
	 * @Fields serialVersionUID : 序列化   
	 */  
	private static final long serialVersionUID = 812376774103405857L;
	
	/**   
	 * @Fields SUCCESS : 成功  
	 */  
	public final static int SUCCESS = 0;
	
	/**   
	 * @Fields ERROR : 失败 
	 */  
	public final static int ERROR = 1;
	
	/**   
	 * @Fields state : 返回状态变量
	 */  
	private int state;
	
	/**   
	 * @Fields message : 返回消息内容
	 */  
	private String message;
	
	/**   
	 * @Fields data : 返回数据  
	 */  
	private T data;
	
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:   无参构造  
	 * @param:    
	 */ 
	public ResultJson() {
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:  一个参数数字
	 * @param:  @param state  
	 */ 
	public ResultJson(int state) {
		if(state == 0) {
			this.state = SUCCESS;
			this.message = "";
			this.data = null;
		}else if( state == -1) {
			this.state = ERROR;
			this.message = "";
			this.data = null;
		}else {
			this.state = SUCCESS;
			this.message = state + "";
			this.data = null;
		}
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:   一个参数  异常e 
	 * @param:  @param e    
	 */ 
	public ResultJson(Throwable e) {
		this.state = ERROR;
		this.message = e.getMessage();
		this.data = null;
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:    一个参数 数据 
	 * @param:  @param t  
	 */ 
	public ResultJson(T t) {
		this.state = SUCCESS;
		this.message = "";
		this.data = t;
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:    两个参数   
	 * @param:  @param state 
	 * @param:  @param message  
	 */ 
	public ResultJson(int state, String message) {
		this.state = state;
		this.message = message;
		this.data = null;
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:    两个参数    
	 * @param:  @param state
	 * @param:  @param e  
	 */ 
	public ResultJson(int state, Throwable e) {
		this.state = state;
		this.message = e.getMessage();
		this.data = null;
	}
	
	/**   
	 * @Title:  ResultJson   
	 * @Description:     两个参数    
	 * @param:  @param state
	 * @param:  @param t  
	  
	public ResultJson(int state, T t) {
		this.state = state;
		this.message = "";
		this.data = t;
	}
	*/
	/**   
	 * @Title:  ResultJson   
	 * @Description:    3个参数   
	 * @param:  @param state
	 * @param:  @param message
	 * @param:  @param t  
	 */ 
	public ResultJson(int state, String message, T t) {
		this.state = state;
		this.message = message;
		this.data = t;
	}

	public int getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultJson [state=" + state + ", message=" + message + ", data=" + data + "]";
	}		
}
