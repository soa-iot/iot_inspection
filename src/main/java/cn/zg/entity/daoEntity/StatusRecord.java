package cn.zg.entity.daoEntity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class StatusRecord implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	private Long ivid;
	
	private String plan_id;
	
	private Date record_time;
	
	private String position_num ;
	
	private String value ;
	
	private String unit ;
	
	private String remark1 ;
	
	private String remark2 ;
	
	private String state ;
	
	private String completor ;
	
	private String remark3 ;
	
	private String remark4 ;
}
