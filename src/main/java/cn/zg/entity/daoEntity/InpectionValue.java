package cn.zg.entity.daoEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
//@Table( name = "CZ_TASK_INSPECTION_VALUE" )
public class InpectionValue implements Serializable {

	/**   
	 * @Fields serialVersionUID : 序列化   
	 */  
	private static final long serialVersionUID = 1L;
	
	@NotBlank( message = "主键不能为空或null" )
	private Integer ivid;
	
	@NotBlank( message = "方案id不能为空或null" )
	private String plan_id;
	
	@NotBlank( message = "记录时间不能为空或null" )
	private Date record_time;
	
	@NotBlank( message = "记录点位不能为空或null" )
	private String position_num ;
	
	@NotNull( message = "记录点位不能为null" )
	private String value ;
	
	private String unit ;
	
	private String remark1 ;
	
	private String remark2 ;
		
	public InpectionValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InpectionValue(Integer ivid, String plan_id, Date record_time, String position_num, String value,
			String unit, String remark1, String remark2) {
		super();
		this.ivid = ivid;
		this.plan_id = plan_id;
		this.record_time = record_time;
		this.position_num = position_num;
		this.value = value;
		this.unit = unit;
		this.remark1 = remark1;
		this.remark2 = remark2;
	}

	/**  
	 * @Title:  getIvid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "mseq" )
	@SequenceGenerator( name = "mseq", sequenceName = "sequence_ctiv" )
	public Integer getIvid() {
		return ivid;
	}

	/**  
	 * @Title:  getPlan_id <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "plan_id")
	public String getPlan_id() {
		return plan_id;
	}

	/**  
	 * @Title:  getRecord_time <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	@Column( name = "record_time")
	public Date getRecord_time() {
		return record_time;
	}

	/**  
	 * @Title:  getPosition_num <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "position_num")
	public String getPosition_num() {
		return position_num;
	}

	/**  
	 * @Title:  getValue <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "value")
	public String getValue() {
		return value;
	}

	/**  
	 * @Title:  getUnit <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "unit")
	public String getUnit() {
		return unit;
	}

	/**  
	 * @Title:  getRemark1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "remark1")
	public String getRemark1() {
		return remark1;
	}

	/**  
	 * @Title:  getRemark2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "remark2")
	public String getRemark2() {
		return remark2;
	}

	/**  
	 * @Title:  setIvid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setIvid(Integer ivid) {
		this.ivid = ivid;
	}

	/**  
	 * @Title:  setPlan_id <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	/**  
	 * @Title:  setRecord_time <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}

	/**  
	 * @Title:  setPosition_num <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPosition_num(String position_num) {
		this.position_num = position_num;
	}

	/**  
	 * @Title:  setValue <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**  
	 * @Title:  setUnit <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**  
	 * @Title:  setRemark1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	/**  
	 * @Title:  setRemark2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "InpectionValue [ivid=" + ivid + ", plan_id=" + plan_id + ", record_time=" + record_time
				+ ", position_num=" + position_num + ", value=" + value + ", unit=" + unit + ", remark1=" + remark1
				+ ", remark2=" + remark2 + "]";
	}
	
	
}