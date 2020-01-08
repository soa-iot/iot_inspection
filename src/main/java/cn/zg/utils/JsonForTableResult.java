package cn.zg.utils;

import java.io.Serializable;

/**
 * @ClassName: JsonForTableResult<T>
 * @Description: layui表格数据格式json
 * @author Bru.Lo
 * @date 2019年12月24日
 */
public class JsonForTableResult<T> implements Serializable{

	private static final long serialVersionUID = 812376774103405857L;
	/**
	 * 状态属性:0表示成功
	 * 1或其他值表示失败
	 */
	private int code;
	/**
	 * 服务器处理成功,返回JSON数据
	 */
	private T data;
	/**
	 * 返回数据的总行数
	 */
	private int count;
	/**
	 * 返回提示信息
	 */
	private String msg;
	public static final int ERROR=1;
	public static final int SUCCESS=0;
	public JsonForTableResult() {
	}
	public JsonForTableResult(int code, T data, String msg,Integer count) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.count = count;
	}
	public JsonForTableResult(Throwable e){
		code=ERROR;
		data=null;
		msg=e.getMessage();
	}
	public JsonForTableResult(T data){
		code=SUCCESS;
		this.data=data;
		msg="";
	}
	public JsonForTableResult(
					int code,Throwable e){
		this.code=code;
		data=null;
		msg=e.getMessage();
	}
	
	public JsonForTableResult(Integer count,T data) {
		code=SUCCESS;
		this.data = data;
		this.count = count;
		msg="";
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", data=" + data + ", msg=" + msg + ", count=" + count + "]";
	}
}














