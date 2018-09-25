package cn.zg.entity.dataExchange.mobile;

import java.io.Serializable;

import org.json.JSONArray;

/**
 * 
 * @ClassName: activeDataTable
 * @Description: 移动端动态数据对象
 * @author zhugang
 * @date 2018年9月25日
 */
public class ActiveDataTable implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化   
	 */  
	private static final long serialVersionUID = 1L;
	
	private String action;
	
	private String tableName;
	
	private JSONArray dataList;

	public ActiveDataTable(String action, String tableName, JSONArray dataList) {
		super();
		this.action = action;
		this.tableName = tableName;
		this.dataList = dataList;
	}

	public ActiveDataTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAction() {
		return action;
	}

	public String getTableName() {
		return tableName;
	}

	public JSONArray getDataList() {
		return dataList;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setDataList(JSONArray dataList) {
		this.dataList = dataList;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "activeDataTable [action=" + action + ", tableName=" + tableName + ", dataList=" + dataList + "]";
	}	
	
}
