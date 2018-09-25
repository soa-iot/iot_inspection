package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: ProjectName1
 * @Description: 二级表头-巡检项目
 * @author zhugang
 * @date 2018年9月19日
 */
@Component
public class ProjectName1 implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 11L;

	private String title;
	
	private String colspan;
	
	private String align;

	public ProjectName1() {
		this.align = "center";
	}

	public ProjectName1(String title, String colspan, String align) {
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
		return "ProjectName1 [title=" + title + ", colspan=" + colspan + ", align=" + align + "]";
	}
	
	
}
