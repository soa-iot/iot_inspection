package cn.zg.entity.daoEntity;

public class MovingEquipmentConfig {
    private String id;

    private String schemeType;

    private String field;

    private String title;

    private Short width;

    private String type;

    private String fixed;

    private String sort;

    private Short colspan;

    private Short rowspan;

    private String classNum;

    private String sortNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed == null ? null : fixed.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public Short getColspan() {
        return colspan;
    }

    public void setColspan(Short colspan) {
        this.colspan = colspan;
    }

    public Short getRowspan() {
        return rowspan;
    }

    public void setRowspan(Short rowspan) {
        this.rowspan = rowspan;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum == null ? null : classNum.trim();
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum == null ? null : sortNum.trim();
    }
}