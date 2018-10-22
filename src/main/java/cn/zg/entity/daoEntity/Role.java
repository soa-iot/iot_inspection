package cn.zg.entity.daoEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name ="PROCESS_ROLE" )
public class Role implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 1L;

	private String rolId;
	
	private String rolName;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String rolId, String rolName) {
		super();
		this.rolId = rolId;
		this.rolName = rolName;
	}

	/**  
	 * @Title:  getRolId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Id
	public String getRolId() {
		return rolId;
	}

	/**  
	 * @Title:  getRolName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "ROL_NAME" )
	public String getRolName() {
		return rolName;
	}

	/**  
	 * @Title:  setRolId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRolId(String rolId) {
		this.rolId = rolId;
	}

	/**  
	 * @Title:  setRolName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "Role [rolId=" + rolId + ", rolName=" + rolName + "]";
	}
	
	
}
