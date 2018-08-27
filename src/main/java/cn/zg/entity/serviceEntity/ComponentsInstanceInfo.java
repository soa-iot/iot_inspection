package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import cn.zg.entity.daoEntity.ComponentsType;

public class ComponentsInstanceInfo implements Serializable {
	private static final long serialVersionUID = 188L;

    private String ciid;

    private String componentsInstanceName;

    private String componentsInstanceUrl;

    private String ctid;

    private String liid;

    private ComponentsType componentsType;

	public ComponentsInstanceInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComponentsInstanceInfo(String ciid, String componentsInstanceName, String componentsInstanceUrl, String ctid,
			String liid, ComponentsType componentsType) {
		super();
		this.ciid = ciid;
		this.componentsInstanceName = componentsInstanceName;
		this.componentsInstanceUrl = componentsInstanceUrl;
		this.ctid = ctid;
		this.liid = liid;
		this.componentsType = componentsType;
	}

	public String getCiid() {
		return ciid;
	}

	public String getComponentsInstanceName() {
		return componentsInstanceName;
	}

	public String getComponentsInstanceUrl() {
		return componentsInstanceUrl;
	}

	public String getCtid() {
		return ctid;
	}

	public String getLiid() {
		return liid;
	}

	public ComponentsType getComponentsType() {
		return componentsType;
	}

	public void setCiid(String ciid) {
		this.ciid = ciid;
	}

	public void setComponentsInstanceName(String componentsInstanceName) {
		this.componentsInstanceName = componentsInstanceName;
	}

	public void setComponentsInstanceUrl(String componentsInstanceUrl) {
		this.componentsInstanceUrl = componentsInstanceUrl;
	}

	public void setCtid(String ctid) {
		this.ctid = ctid;
	}

	public void setLiid(String liid) {
		this.liid = liid;
	}

	public void setComponentsType(ComponentsType componentsType) {
		this.componentsType = componentsType;
	}

	@Override
	public String toString() {
		return "ComponentsInstanceInfo [ciid=" + ciid + ", componentsInstanceName=" + componentsInstanceName
				+ ", componentsInstanceUrl=" + componentsInstanceUrl + ", ctid=" + ctid + ", liid=" + liid
				+ ", componentsType=" + componentsType + "]";
	}
    
    
}