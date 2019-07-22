package cn.zg.entity.daoEntity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeterInspectionResult {
	private String id;

	private String schemeType;

	private String schemeName;

	private String requireId;

	private String requireContext;

	private String inspectionResult;

	private Date recordDay;

	private String taskInstId;

	private String standby1;

	private String standby2;

	private String standby3;

	
}