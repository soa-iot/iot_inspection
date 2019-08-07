package cn.zg.entity.daoEntity;

public class ProcessControlParaConfig {
    private String id;

    private String paraNameCn;

    private String paraNameEn;

    private String paraUnit;

    private String gasClass1;

    private String gasClass2;

    private String indicatorSymbol;

    private String indicatorUpperLimit;

    private String indicatorLowerLimit;

    private String paraType;

    private String standby1;

    private String standby2;

    private String standby3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParaNameCn() {
        return paraNameCn;
    }

    public void setParaNameCn(String paraNameCn) {
        this.paraNameCn = paraNameCn == null ? null : paraNameCn.trim();
    }

    public String getParaNameEn() {
        return paraNameEn;
    }

    public void setParaNameEn(String paraNameEn) {
        this.paraNameEn = paraNameEn == null ? null : paraNameEn.trim();
    }

    public String getParaUnit() {
        return paraUnit;
    }

    public void setParaUnit(String paraUnit) {
        this.paraUnit = paraUnit == null ? null : paraUnit.trim();
    }

    public String getGasClass1() {
        return gasClass1;
    }

    public void setGasClass1(String gasClass1) {
        this.gasClass1 = gasClass1 == null ? null : gasClass1.trim();
    }

    public String getGasClass2() {
        return gasClass2;
    }

    public void setGasClass2(String gasClass2) {
        this.gasClass2 = gasClass2 == null ? null : gasClass2.trim();
    }

    public String getIndicatorSymbol() {
        return indicatorSymbol;
    }

    public void setIndicatorSymbol(String indicatorSymbol) {
        this.indicatorSymbol = indicatorSymbol == null ? null : indicatorSymbol.trim();
    }

    public String getIndicatorUpperLimit() {
        return indicatorUpperLimit;
    }

    public void setIndicatorUpperLimit(String indicatorUpperLimit) {
        this.indicatorUpperLimit = indicatorUpperLimit == null ? null : indicatorUpperLimit.trim();
    }

    public String getIndicatorLowerLimit() {
        return indicatorLowerLimit;
    }

    public void setIndicatorLowerLimit(String indicatorLowerLimit) {
        this.indicatorLowerLimit = indicatorLowerLimit == null ? null : indicatorLowerLimit.trim();
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType == null ? null : paraType.trim();
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
}