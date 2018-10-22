package cn.zg.entity.daoEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Organization
 * @Description: 人员组织结构实体类
 * @author zhugang
 * @date 2018年10月4日
 */
@Entity
@Table( name ="PROCESS_ORGANIZATION" )
public class Organization implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化   
	 */  
	private static final long serialVersionUID = 1L;
	
	private String orgId;
	
	private String orgName;

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organization(String orgId, String orgName) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
	}
	
	

	/**  
	 * @Title:  getOrgId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Id
	public String getOrgId() {
		return orgId;
	}

	/**  
	 * @Title:  getOrgName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "ORG_NAME" )
	public String getOrgName() {
		return orgName;
	}

	/**  
	 * @Title:  setOrgId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**  
	 * @Title:  setOrgName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgName=" + orgName + "]";
	}
	
	
}
