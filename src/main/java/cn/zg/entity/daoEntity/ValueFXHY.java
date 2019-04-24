package cn.zg.entity.daoEntity;

import java.io.Serializable;
import java.sql.Date;

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
public class ValueFXHY implements Serializable{
	private String vfid;
	private String requireid;
	private String valueunit;
	private String valuename;
	private String equipcol;
	private Date recordtime;
	private String value;
	private String unit;
	private String remark1;
	private String remark2;
}
