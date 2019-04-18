package cn.zg.entity.daoEntity;

import java.util.Date;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings( "serial" )  
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class HeadConfigJSB {
	
	
	private String hsid;
	private String planId;
	private String projectName;
	private String requireid;
	private String colspan;
	private String remark1;
	private String remark2;
}
