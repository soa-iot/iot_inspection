
/**
 * <一句话功能描述>
 * <p> 响应实体类
 * @author 陈宇林
 * @version [版本号, 2019年6月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.entity;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Validated
public class ResponseObject<T> implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = 1L;

	private int code;// 响应编码

	private String msg;// 返回消息

	private T data;// 返回数据

}
