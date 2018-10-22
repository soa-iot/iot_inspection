package cn.zg.entity.daoEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * @ClassName: ProblrmInspection
 * @Description: 问题业务表实体类
 * @author zhugang
 * @date 2018年10月11日
 */
@Entity
@Table( name = "PROCESS_INSPECTION_PROBLEM")
public class ProblemInspection implements Serializable {

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 1L;

	private String piid;
	
	@NotBlank( message="上报人不能为空" )
	private String reporter;
	
	private Timestamp reportTime;
	
	@NotBlank( message="问题区域不能为空" )
	private String area;
	
	@NotBlank( message="属地单位不能为空" )
	private String positionUnit;
	
	@NotBlank( message="问题专业不能为空" )
	private String profession;
	
	@NotBlank( message="问题类型不能为空" )
	private String problemType;
	
	//@NotBlank( message="" )
	private String department;
	
	//@NotBlank( message="问题种类不能为空" )
	private String problemClass;
	
	private String problemPosition;
	
	@NotBlank( message="不安全行为状态不能为空" )
	private String unsafetyAction;
	
	private String unsafetyState;
	
	@NotBlank( message="具体行为不能为空" )
	private String detailAction;
	
	@NotBlank( message="问题描述不能为空" )
	private String problemDescribe;
	
	private String measures;
	
	private String preventMeasures;
	
	private Date planFinishedDate;
	
	private Date actualFinishedDate;
	
	private String idAccidentEvent;
	
	private String idHiddenEvent;
	
	private String problemState;
	
	private String currentProgress;
	
	private String responsers;
	
	private String emergence;
	
	private String rfId;
	
	private String equipmentPositionNum;
	
	private String remark1;
	
	private String remark2;
	
	private String remark3;
	
	private String remark4;
	
	private String remark5;

	public ProblemInspection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProblemInspection(String piid, String reporter, Timestamp reportTime, String area, String positionUNnit,
			String profession, String problemType, String department, String problemClass, String problemPosition,
			String unsafetyAction, String unsafetyState, String detailAction, String problemDescribe, String measures,
			String preventMeasures, Date planFinishedDate, Date actualFinishedDate, String idAccidentEvent,
			String idHiddenEvent, String problemState, String currentProgress, String responsers, String emergence,
			String rfId,String equipmentPositionNum,String remark1, String remark2, String remark3, 
			String remark4, String remark5) {
		super();
		this.piid = piid;
		this.reporter = reporter;
		this.reportTime = reportTime;
		this.area = area;
		this.positionUnit = positionUNnit;
		this.profession = profession;
		this.problemType = problemType;
		this.department = department;
		this.problemClass = problemClass;
		this.problemPosition = problemPosition;
		this.unsafetyAction = unsafetyAction;
		this.unsafetyState = unsafetyState;
		this.detailAction = detailAction;
		this.problemDescribe = problemDescribe;
		this.measures = measures;
		this.preventMeasures = preventMeasures;
		this.planFinishedDate = planFinishedDate;
		this.actualFinishedDate = actualFinishedDate;
		this.idAccidentEvent = idAccidentEvent;
		this.idHiddenEvent = idHiddenEvent;
		this.problemState = problemState;
		this.currentProgress = currentProgress;
		this.responsers = responsers;
		this.emergence = emergence;
		this.rfId = rfId;
		this.equipmentPositionNum = equipmentPositionNum;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
		this.remark4 = remark4;
		this.remark5 = remark5;
	}

	/**  
	 * @Title:  getPiid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Id
	public String getPiid() {
		return piid;
	}

	/**  
	 * @Title:  getReporter <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "REPORTER" )
	public String getReporter() {
		return reporter;
	}

	/**  
	 * @Title:  getReportTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Timestamp <BR>  
	 */
	@Column( name = "REPORTTIME" )
	public Timestamp getReportTime() {
		return reportTime;
	}

	/**  
	 * @Title:  getArea <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "AREA" )
	public String getArea() {
		return area;
	}

	/**  
	 * @Title:  getPositionUNnit <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "POSITIONUNIT" )
	public String getPositionUnit() {
		return positionUnit;
	}

	/**  
	 * @Title:  getProfession <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PROFESSION" )
	public String getProfession() {
		return profession;
	}

	/**  
	 * @Title:  getProblemType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PROBLEMTYPE" )
	public String getProblemType() {
		return problemType;
	}

	/**  
	 * @Title:  getDepartment <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "DEPARTMENT" )
	public String getDepartment() {
		return department;
	}

	/**  
	 * @Title:  getProblemClass <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PROBLEMCLASS" )
	public String getProblemClass() {
		return problemClass;
	}

	/**  
	 * @Title:  getProblemPosition <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PROBLEMPOSITION" )
	public String getProblemPosition() {
		return problemPosition;
	}

	/**  
	 * @Title:  getUnsafetyAction <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "UNSAFETYACTION" )
	public String getUnsafetyAction() {
		return unsafetyAction;
	}

	/**  
	 * @Title:  getUnsafetyState <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "UNSAFETYSTATE" )
	public String getUnsafetyState() {
		return unsafetyState;
	}

	/**  
	 * @Title:  getDetailAction <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "DETAILACTION" )
	public String getDetailAction() {
		return detailAction;
	}

	/**  
	 * @Title:  getProblemDescribe <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PROBLEMDESCRIBE" )
	public String getProblemDescribe() {
		return problemDescribe;
	}

	/**  
	 * @Title:  getMeasures <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "MEASURES" )
	public String getMeasures() {
		return measures;
	}

	/**  
	 * @Title:  getPreventMeasures <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PREVENTMEASURES" )
	public String getPreventMeasures() {
		return preventMeasures;
	}

	/**  
	 * @Title:  getPlanFinishedDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	@Column( name = "PLANFINISHEDDATE" )
	public Date getPlanFinishedDate() {
		return planFinishedDate;
	}

	/**  
	 * @Title:  getActualFinishedDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	@Column( name = "ACTUALFINISHEDDATE" )
	public Date getActualFinishedDate() {
		return actualFinishedDate;
	}

	/**  
	 * @Title:  getIdAccidentEvent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "ISACCIDENTEVENT" )
	public String getIdAccidentEvent() {
		return idAccidentEvent;
	}

	/**  
	 * @Title:  getIdHiddenEvent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "ISHIDDENEVENT" )
	public String getIdHiddenEvent() {
		return idHiddenEvent;
	}

	/**  
	 * @Title:  getProblemState <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "PROBLEMSTATE" )
	public String getProblemState() {
		return problemState;
	}

	/**  
	 * @Title:  getCurrentProgress <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "CURRENTPROGRESS" )
	public String getCurrentProgress() {
		return currentProgress;
	}

	/**  
	 * @Title:  getResponsers <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "RESPONSERS" )
	public String getResponsers() {
		return responsers;
	}

	/**  
	 * @Title:  getEmergence <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "EMERGENCE" )
	public String getEmergence() {
		return emergence;
	}
	
	/**  
	 * @Title:  getRfId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "RFID" )
	public String getRfId() {
		return rfId;
	}
	
	/**  
	 * @Title:  getRfId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "EQUIPMENTPOSITIONNUM" )
	public String getEquipmentPositionNum() {
		return equipmentPositionNum;
	}

	/**  
	 * @Title:  getRemark1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "REMARK1" )
	public String getRemark1() {
		return remark1;
	}

	/**  
	 * @Title:  getRemark2 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "REMARK2" )
	public String getRemark2() {
		return remark2;
	}

	/**  
	 * @Title:  getRemark3 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "REMARK3" )
	public String getRemark3() {
		return remark3;
	}

	/**  
	 * @Title:  getRemark4 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "REMARK4" )
	public String getRemark4() {
		return remark4;
	}

	/**  
	 * @Title:  getRemark5 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	@Column( name = "REMARK5" )
	public String getRemark5() {
		return remark5;
	}

	/**  
	 * @Title:  setPiid <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPiid(String piid) {
		this.piid = piid;
	}

	/**  
	 * @Title:  setReporter <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	/**  
	 * @Title:  setReportTime <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Timestamp <BR>  
	 */
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	/**  
	 * @Title:  setArea <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**  
	 * @Title:  setPositionUNnit <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPositionUnit(String positionUNnit) {
		this.positionUnit = positionUNnit;
	}

	/**  
	 * @Title:  setProfession <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**  
	 * @Title:  setProblemType <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	/**  
	 * @Title:  setDepartment <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**  
	 * @Title:  setProblemClass <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setProblemClass(String problemClass) {
		this.problemClass = problemClass;
	}

	/**  
	 * @Title:  setProblemPosition <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setProblemPosition(String problemPosition) {
		this.problemPosition = problemPosition;
	}

	/**  
	 * @Title:  setUnsafetyAction <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setUnsafetyAction(String unsafetyAction) {
		this.unsafetyAction = unsafetyAction;
	}

	/**  
	 * @Title:  setUnsafetyState <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setUnsafetyState(String unsafetyState) {
		this.unsafetyState = unsafetyState;
	}

	/**  
	 * @Title:  setDetailAction <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setDetailAction(String detailAction) {
		this.detailAction = detailAction;
	}

	/**  
	 * @Title:  setProblemDescribe <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	/**  
	 * @Title:  setMeasures <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setMeasures(String measures) {
		this.measures = measures;
	}

	/**  
	 * @Title:  setPreventMeasures <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setPreventMeasures(String preventMeasures) {
		this.preventMeasures = preventMeasures;
	}

	/**  
	 * @Title:  setPlanFinishedDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setPlanFinishedDate(Date planFinishedDate) {
		this.planFinishedDate = planFinishedDate;
	}

	/**  
	 * @Title:  setActualFinishedDate <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Date <BR>  
	 */
	public void setActualFinishedDate(Date actualFinishedDate) {
		this.actualFinishedDate = actualFinishedDate;
	}

	/**  
	 * @Title:  setIdAccidentEvent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setIdAccidentEvent(String idAccidentEvent) {
		this.idAccidentEvent = idAccidentEvent;
	}

	/**  
	 * @Title:  setIdHiddenEvent <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setIdHiddenEvent(String idHiddenEvent) {
		this.idHiddenEvent = idHiddenEvent;
	}

	/**  
	 * @Title:  setProblemState <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setProblemState(String problemState) {
		this.problemState = problemState;
	}

	/**  
	 * @Title:  setCurrentProgress <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setCurrentProgress(String currentProgress) {
		this.currentProgress = currentProgress;
	}

	/**  
	 * @Title:  setResponsers <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setResponsers(String responsers) {
		this.responsers = responsers;
	}

	/**  
	 * @Title:  setEmergence <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setEmergence(String emergence) {
		this.emergence = emergence;
	}
	
	

	/**  
	 * @Title:  setRfId <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRfId(String rfId) {
		this.rfId = rfId;
	}

	/**  
	 * @Title:  setEquipmentPositionNum <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setEquipmentPositionNum(String equipmentPositionNum) {
		this.equipmentPositionNum = equipmentPositionNum;
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
	 * @Title:  setRemark3 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**  
	 * @Title:  setRemark4 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	/**  
	 * @Title:  setRemark5 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "ProblemInspection [piid=" + piid + ", reporter=" + reporter + ", reportTime=" + reportTime + ", area="
				+ area + ", PositionUNnit=" + positionUnit + ", profession=" + profession + ", problemType="
				+ problemType + ", Department=" + department + ", problemClass=" + problemClass + ", problemPosition="
				+ problemPosition + ", unsafetyAction=" + unsafetyAction + ", unsafetyState=" + unsafetyState
				+ ", detailAction=" + detailAction + ", problemDescribe=" + problemDescribe + ", measures=" + measures
				+ ", preventMeasures=" + preventMeasures + ", planFinishedDate=" + planFinishedDate
				+ ", actualFinishedDate=" + actualFinishedDate + ", idAccidentEvent=" + idAccidentEvent
				+ ", idHiddenEvent=" + idHiddenEvent + ", problemState=" + problemState + ", currentProgress="
				+ currentProgress + ", responsers=" + responsers + ", emergence=" + emergence + ", remark1=" + remark1
				+ ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", remark5=" + remark5
				+ "]";
	}
	
	
}
