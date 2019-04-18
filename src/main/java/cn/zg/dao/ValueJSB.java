package cn.zg.dao;

import java.util.Date;

import org.springframework.validation.annotation.Validated;

import cn.zg.entity.daoEntity.HeadConfigJSB;
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
public class ValueJSB {
	private String ivid;
	private String planId;
	private String planNum;
	private String unitType;
	private String unitName;
	private String equipcol;
	private Date recordTime;
	private String value;
	private String unit;
	private String requireid;
	private String remark1;
	private String remark2;
}
