package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * 
 * @ClassName: Position
 * @Description: 六级表头-中控、现场
 * @author zhugang
 * @date 2018年9月19日
 */

@Component
public class Position implements Serializable{

	/**   
	 * @Fields serialVersionUID :  序列化 
	 */  
	private static final long serialVersionUID = 11L;
	
	private String title;
	
	private String align;
	
	private String colspan;
	
	private Integer minWidth;

	public Position() {
		super();
		this.title = "现场";
		this.align = "center";
		this.colspan = "1";
		this.minWidth = 100;
	}
	
	public Position( String title ) {
		super();
		this.title = title;
		this.align = "center";
		this.colspan = "1";
		this.minWidth = 80;
	}

	public Position(String title, String align) {
		super();
		this.title = title;
		this.align = "center";
		this.colspan = "1";
		this.minWidth = 80;
	}

	public String getTitle() {
		return title;
	}

	public String getAlign() {
		return align;
	}

	public String getColspan() {
		return colspan;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	@Override
	public String toString() {
		return "Position [title=" + title + ", align=" + align + ", colspan=" + colspan + ", minWidth=" + minWidth
				+ "]";
	}

	
}
