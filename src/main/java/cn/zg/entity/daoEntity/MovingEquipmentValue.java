package cn.zg.entity.daoEntity;

public class MovingEquipmentValue {
	private String id;

	private String schemeRemark;

	private String requireId;

	private String paraValue;

	private String unit;

	private String positionNum;

	private String equName;

	private String standby1;

	private String standby2;

	private String standby3;

	private String recordDate;

	private String paraName;

	private String taskRequireContext;

	private String standValue;

	private String taskInstId;

	/**
	 * @return the taskInstId
	 */
	public String getTaskInstId() {
		return taskInstId;
	}

	/**
	 * @param taskInstId
	 *            the taskInstId to set
	 */
	public void setTaskInstId(String taskInstId) {
		this.taskInstId = taskInstId;
	}

	/**
	 * @return the taskRequireContext
	 */
	public String getTaskRequireContext() {
		return taskRequireContext;
	}

	/**
	 * @return the standValue
	 */
	public String getStandValue() {
		return standValue;
	}

	/**
	 * @param taskRequireContext
	 *            the taskRequireContext to set
	 */
	public void setTaskRequireContext(String taskRequireContext) {
		this.taskRequireContext = taskRequireContext;
	}

	/**
	 * @param standValue
	 *            the standValue to set
	 */
	public void setStandValue(String standValue) {
		this.standValue = standValue;
	}

	/**
	 * @return the paraName
	 */
	public String getParaName() {
		return paraName;
	}

	/**
	 * @param paraName
	 *            the paraName to set
	 */
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getSchemeRemark() {
		return schemeRemark;
	}

	public void setSchemeRemark(String schemeRemark) {
		this.schemeRemark = schemeRemark == null ? null : schemeRemark.trim();
	}

	public String getRequireId() {
		return requireId;
	}

	public void setRequireId(String requireId) {
		this.requireId = requireId == null ? null : requireId.trim();
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue == null ? null : paraValue.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getPositionNum() {
		return positionNum;
	}

	public void setPositionNum(String positionNum) {
		this.positionNum = positionNum == null ? null : positionNum.trim();
	}

	public String getEquName() {
		return equName;
	}

	public void setEquName(String equName) {
		this.equName = equName == null ? null : equName.trim();
	}

	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1 == null ? null : standby1.trim();
	}

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2 == null ? null : standby2.trim();
	}

	public String getStandby3() {
		return standby3;
	}

	public void setStandby3(String standby3) {
		this.standby3 = standby3 == null ? null : standby3.trim();
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MovingEquipmentValue [id=" + id + ", schemeRemark=" + schemeRemark + ", requireId=" + requireId
				+ ", paraValue=" + paraValue + ", unit=" + unit + ", positionNum=" + positionNum + ", equName="
				+ equName + ", standby1=" + standby1 + ", standby2=" + standby2 + ", standby3=" + standby3
				+ ", recordDate=" + recordDate + ", paraName=" + paraName + ", taskRequireContext=" + taskRequireContext
				+ ", standValue=" + standValue + ", taskInstId=" + taskInstId + "]";
	}

}