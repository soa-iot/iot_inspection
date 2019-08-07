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
	
	private Integer width;

	public Unit() {
		super();
		this.align = "center";
		this.width = 100;
	}

	public Unit(String title, String colspan, String align) {
		super();
		this.title = title;
		this.colspan = colspan;
		this.align = align;
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
		return "center";
	}

	/**  
	 * @Title:  getwidth <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getWidth() {
		return width;
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
		this.align = align;
	}

	/**  
	 * @Title:  setwidth <BR>  
	 * @Description: please write your description <BR>  
	 * @return: Integer <BR>  
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */ 
	@Override
	public String toString() {
		return "Unit [title=" + title + ", colspan=" + colspan + ", align=" + align + ", width=" + width + "]";
	}

	
}
