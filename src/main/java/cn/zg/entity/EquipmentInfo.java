package cn.zg.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 设备信息实体类
 * 
 * @author Bru.Lo
 *
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Validated
public class EquipmentInfo implements Serializable {
	
	private String equID;
	private String welName;
	private String welUnit;
	private String equModel;
	private String equInstallPosition;
	private String equManufacturer;
	private String equPositionNum;
	private String meduimType;
	private String equWorkTemp;
	private String serialNum;
	private String equMemoOne;
	private String equipmentAttachURL;
	private String equName;
	
}
