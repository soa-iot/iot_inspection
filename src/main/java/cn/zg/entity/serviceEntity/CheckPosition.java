package cn.zg.entity.serviceEntity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: CheckPosition
 * @Description: 表格第一级表头信息
 * @author zhugang
 * @date 2018年9月19日
 */
@Component
public class CheckPosition implements Serializable{

	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 1111L;

	private String title;
	
	private String colspan;

	private String align;
	
	private Integer width;

	public CheckPosition() {
		super();
		this.align = "center";
		// TODO Auto-generated constructor stub
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
	 * @return: String <BR>  
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
	 * @Title:  setMinWidth <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
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
		return "CheckPosition [title=" + title + ", colspan=" + colspan + ", align=" + align + ", width=" + width
				+ "]";
	}

	
}
