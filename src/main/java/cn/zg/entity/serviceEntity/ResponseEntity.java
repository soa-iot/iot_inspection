
/**
 * <一句话功能描述>
 * <p>
 * @author 陈宇林
 * @version [版本号, 2019年4月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.zg.entity.serviceEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {

	private int code;// 数据状态

	private String msg;// 返回消息

	private int count;// 数据条数

	private T data;// 数据

}
