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

	private Integer minWidth;
	

	public ProjectName1() {
		super();
		this.minWidth = 100;
		this.align = "center";
	}

	/**  
	 * @Title:  getTitle <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getTitle() {
		return title;
	}

	/**  
	 * @Title:  getColspan <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getColspan() {
		return colspan;
	}

	/**  
	 * @Title:  getAlign <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getAlign() {
		return align;
	}

	/**  
	 * @Title:  getMinWidth <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getMinWidth() {
		return minWidth;
	}

	/**  
	 * @Title:  setTitle <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**  
	 * @Title:  setColspan <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	/**  
	 * @Title:  setAlign <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setAlign(String align) {
		this.align = "center";
	}

	/**  
	 * @Title:  setMinWidth <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "ProjectName1 [title=" + title + ", colspan=" + colspan + ", align=" + align + ", minWidth=" + minWidth
				+ "]";
	}
	
}
