package cn.zg.entity.daoEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name ="process_employee" )
public class Emploee implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化 
	 */  
	private static final long serialVersionUID = 1L;
	
	private String empId;
	
	private String empName;

	private String empPassword;

	private String empTelephoneNum;

	private Integer sex;

	public Emploee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Emploee(String empId, String empName, String empPassword,
			String empTelephoneNum, Integer sex) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empTelephoneNum = empTelephoneNum;
		this.sex = sex;
	}

	/**  
	 * @Title:  getEmpId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Id
	public String getEmpId() {
		return empId;
	}

	/**  
	 * @Title:  getEmpName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "EMP_NAME" )
	public String getEmpName() {
		return empName;
	}

	/**  
	 * @Title:  getEmpPassword <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "EMP_PASSWORD" )
	public String getEmpPassword() {
		return empPassword;
	}

	/**  
	 * @Title:  getEmpTelephoneNum <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "EMP_TELEPHONE_NUM" )
	public String getEmpTelephoneNum() {
		return empTelephoneNum;
	}

	/**  
	 * @Title:  getSex <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	@Column( name = "EMP_SEX" )
	public Integer getSex() {
		return sex;
	}

	/**  
	 * @Title:  setEmpId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**  
	 * @Title:  setEmpName <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**  
	 * @Title:  setEmpPassword <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	/**  
	 * @Title:  setEmpTelephoneNum <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setEmpTelephoneNum(String empTelephoneNum) {
		this.empTelephoneNum = empTelephoneNum;
	}

	/**  
	 * @Title:  setSex <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "Emploee [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword
				+ ", empTelephoneNum=" + empTelephoneNum + ", sex=" + sex + "]";
	}
	
	
	
}
