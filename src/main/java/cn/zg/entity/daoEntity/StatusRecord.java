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
	












	private String IVID;
	
	private String PLAN_ID;
	
	private Date RECORD_TIME;
	
	private String POSITION_NUM ;
	
	private String VALUE ;
	
	private String UNIT ;
	
	private String REMARK1 ;
	
	private String REMARK2 ;
	
	private String STATE ;
	
	private String COMPLETOR ;
	
	private String REMARK3 ;
	
	private String REMARK4 ;
}
