package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Requireid implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 1111111L;

	private String field;
	
	private String title;
	
	private String align;
	
	private Integer minWidth;

	public Requireid() {
		this.align = "center";
		this.minWidth = 100;
	}

	public Requireid(String field, String title, String align, Integer minWidth) {
		super();
		this.field = field;
		this.title = title;
		this.align = align;
		this.minWidth = minWidth;
	}

	public String getField() {
		return field;
	}

	public String getTitle() {
		return title;
	}

	public String getAlign() {
		return align;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	public void setField(String colspan) {
		this.field = colspan;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	@Override
	public String toString() {
		return "Requireid [field=" + field + ", title=" + title + ", align=" + align + ", minWidth=" + minWidth + "]";
	}

	
}
