package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * 
 * @ClassName: PositionNum
 * @Description: 七级表头-现场、中控巡检位号
 * @author zhugang
 * @date 2018年9月19日
 */
@Component
public class PositionNum implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 1111111L;

	private String field;
	
	private String title;
	
	private String align;
	
	private Integer minWidth;

	public PositionNum() {
		this.align = "center";
		this.minWidth = 80;
	}

	public PositionNum(String field, String title, String align, Integer minWidth) {
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

	public void setField(String field) {
		this.field = field;
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
		return "PositionNum [field=" + field + ", title=" + title + ", align=" + align + ", minWidth=" + minWidth + "]";
	}

	
}
