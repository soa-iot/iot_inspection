package cn.zg.entity.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;


public class TableDataDDLInfo implements Serializable{
	
	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 1L;

	private Map<String,String> ddlMap;
	
	private Map<String,List<Map<String,Object>>> infoMap;
	
	

	public TableDataDDLInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableDataDDLInfo(Map<String, String> ddlMap, Map<String, List<Map<String, Object>>> infoMap) {
		super();
		this.ddlMap = ddlMap;
		this.infoMap = infoMap;
	}

	/**  
	 * @Title:  getDdlMap <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Map<String,String> <BR>  
	 */
	public Map<String, String> getDdlMap() {
		return ddlMap;
	}

	/**  
	 * @Title:  getInfoMap <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Map<String,List<Map<String,Object>>> <BR>  
	 */
	public Map<String, List<Map<String, Object>>> getInfoMap() {
		return infoMap;
	}

	/**  
	 * @Title:  setDdlMap <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Map<String,String> <BR>  
	 */
	public void setDdlMap(Map<String, String> ddlMap) {
		this.ddlMap = ddlMap;
	}

	/**  
	 * @Title:  setInfoMap <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Map<String,List<Map<String,Object>>> <BR>  
	 */
	public void setInfoMap(Map<String, List<Map<String, Object>>> infoMap) {
		this.infoMap = infoMap;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "TableDataDDLInfo [ddlMap=" + ddlMap + ", infoMap=" + infoMap + "]";
	}

	
}
