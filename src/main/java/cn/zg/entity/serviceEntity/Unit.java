package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: ProjectName1
 * @Description: 四级表头-巡检数据单位
 * @author zhugang
 * @date 2018年9月19日
 */
@Component
public class Unit implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 11L;

	private String title;
	
	private String colspan;
	
	private String align;

	public Unit() {
		this.align = "center";
	}

	public Unit(String title, String colspan, String align) {
		super();
		this.title = title;
		this.colspan = colspan;
		this.align = align;
	}

	public String getTitle() {
		return title;
	}

	public String getColspan() {
		return colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	@Override
	public String toString() {
		return "Unit [title=" + title + ", colspan=" + colspan + ", align=" + align + "]";
	}

	
	
}
