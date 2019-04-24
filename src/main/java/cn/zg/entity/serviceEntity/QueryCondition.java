
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
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryCondition {
	
	private String schemeType;//方案类型
	
	private String schemeRemark;//方案标志
	
	private String recordDate;//日期

}
