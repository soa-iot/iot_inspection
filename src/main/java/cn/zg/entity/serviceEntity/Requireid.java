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
	
	private Integer width;

	public Requireid() {
		this.align = "center";
		this.width = 100;
	}

	public Requireid(String field, String title, String align, Integer width) {
		super();
		this.field = field;
		this.title = title;
		this.align = align;
		this.width = width;
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

	public Integer getWidth() {
		return width;
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

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Requireid [field=" + field + ", title=" + title + ", align=" + align + ", width=" + width + "]";
	}

	
}
