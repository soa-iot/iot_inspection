package cn.zg.entity.daoEntity;

import java.util.Date;

public class InspectionScheme {
    private String schemeId;

    private String schemeName;

    private String equUnit;

    private String schemeType;

    private String schemeCreator;

    private Date schemeCreateTime;

    private String schemeDesc;

    private String remark;

    private String remarkone;

    private String remarktwo;

    private String remarkthree;

    private String enable;

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    public String getEquUnit() {
        return equUnit;
    }

    public void setEquUnit(String equUnit) {
        this.equUnit = equUnit == null ? null : equUnit.trim();
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    public String getSchemeCreator() {
        return schemeCreator;
    }

    public void setSchemeCreator(String schemeCreator) {
        this.schemeCreator = schemeCreator == null ? null : schemeCreator.trim();
    }

    public Date getSchemeCreateTime() {
        return schemeCreateTime;
    }

    public void setSchemeCreateTime(Date schemeCreateTime) {
        this.schemeCreateTime = schemeCreateTime;
    }

    public String getSchemeDesc() {
        return schemeDesc;
    }

    public void setSchemeDesc(String schemeDesc) {
        this.schemeDesc = schemeDesc == null ? null : schemeDesc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemarkone() {
        return remarkone;
    }

    public void setRemarkone(String remarkone) {
        this.remarkone = remarkone == null ? null : remarkone.trim();
    }

    public String getRemarktwo() {
        return remarktwo;
    }

    public void setRemarktwo(String remarktwo) {
        this.remarktwo = remarktwo == null ? null : remarktwo.trim();
    }

    public String getRemarkthree() {
        return remarkthree;
    }

    public void setRemarkthree(String remarkthree) {
        this.remarkthree = remarkthree == null ? null : remarkthree.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }
}