package cn.zg.entity.daoEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: Schemeposition
 * @Description: 净化方案实体类
 * @author zhugang
 * @date 2018年9月18日
 */

@Entity
@Table( name = "CZ_INSPECTION_HEADCONFIG" )
public class Schemeposition implements Serializable{
	
	/**   
	 * @Fields serialVersionUID : 序列化   
	 */  
	private static final long serialVersionUID = 1111L;

	private String id;
	
	private String inspectionName;
	
	private String checkPosition;
	
	private Integer firstColspan;
	
	private String projectName1;
	
	private Integer secondColspan1;
	
	private String projectName2;
	
	private Integer secondColspan2;
	
	private String positionNum;
	
	private String unit;
	
	private Integer fourthColspan;
	
	private String dataRange;
	
	private Integer fivthColspan;

	public Schemeposition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Schemeposition(String id, String inspectionName, String checkPosition, Integer firstColspan,
			String projectName1, Integer secondColspan1, String projectName2, Integer secondColspan2,
			String positionNum, String unit, Integer fourthColspan, String dataRange, Integer fivthColspan) {
		super();
		this.id = id;
		this.inspectionName = inspectionName;
		this.checkPosition = checkPosition;
		this.firstColspan = firstColspan;
		this.projectName1 = projectName1;
		this.secondColspan1 = secondColspan1;
		this.projectName2 = projectName2;
		this.secondColspan2 = secondColspan2;
		this.positionNum = positionNum;
		this.unit = unit;
		this.fourthColspan = fourthColspan;
		this.dataRange = dataRange;
		this.fivthColspan = fivthColspan;
	}

	@Id
	public String getId() {
		return id;
	}

	@Column( name = "INSPECTIONNAME" )
	public String getInspectionName() {
		return inspectionName;
	}

	@Column( name = "CHECKPOSITON" )
	public String getCheckPosition() {
		return checkPosition;
	}

	@Column( name = "FIRSTCOLSPAN" )
	public Integer getFirstColspan() {
		return firstColspan;
	}

	@Column( name = "PROJECTNAME1" )
	public String getProjectName1() {
		return projectName1;
	}

	@Column( name = "SECONDCOLSPAN1" )
	public Integer getSecondColspan1() {
		return secondColspan1;
	}

	@Column( name = "PROJECTNAME2" )
	public String getProjectName2() {
		return projectName2;
	}

	@Column( name = "SECONDCOLSPAN2" )
	public Integer getSecondColspan2() {
		return secondColspan2;
	}

	@Column( name = "POSITIONNUM" )
	public String getPositionNum() {
		return positionNum;
	}

	@Column( name = "UNIT" )
	public String getUnit() {
		return unit;
	}

	@Column( name = "FOURTHCOLSPAN" )
	public Integer getFourthColspan() {
		return fourthColspan;
	}

	@Column( name = "DATARANGE" )
	public String getDataRange() {
		return dataRange;
	}

	@Column( name = "FIVTHCOLSPAN" )
	public Integer getFivthColspan() {
		return fivthColspan;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}

	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}

	public void setFirstColspan(Integer firstColspan) {
		this.firstColspan = firstColspan;
	}

	public void setProjectName1(String projectName1) {
		this.projectName1 = projectName1;
	}

	public void setSecondColspan1(Integer secondColspan1) {
		this.secondColspan1 = secondColspan1;
	}

	public void setProjectName2(String projectName2) {
		this.projectName2 = projectName2;
	}

	public void setSecondColspan2(Integer secondColspan2) {
		this.secondColspan2 = secondColspan2;
	}

	public void setPositionNum(String positionNum) {
		this.positionNum = positionNum;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setFourthColspan(Integer fourthColspan) {
		this.fourthColspan = fourthColspan;
	}

	public void setDataRange(String dataRange) {
		this.dataRange = dataRange;
	}

	public void setFivthColspan(Integer fivthColspan) {
		this.fivthColspan = fivthColspan;
	}

	@Override
	public String toString() {
		return "Schemeposition [id=" + id + ", inspectionName=" + inspectionName + ", checkPosition=" + checkPosition
				+ ", firstColspan=" + firstColspan + ", projectName1=" + projectName1 + ", secondColspan1="
				+ secondColspan1 + ", projectName2=" + projectName2 + ", secondColspan2=" + secondColspan2
				+ ", positionNum=" + positionNum + ", unit=" + unit + ", fourthColspan=" + fourthColspan
				+ ", dataRange=" + dataRange + ", fivthColspan=" + fivthColspan + "]";
	}
	
	
}
