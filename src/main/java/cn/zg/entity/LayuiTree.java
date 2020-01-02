package cn.zg.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * layui树形结构实体类
 * @author Hang
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class LayuiTree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String label;
	private String id;
	private List<LayuiTree> children;
	
	public LayuiTree(String label){
		this.label = label;
	}
	
	public LayuiTree(String label, String id){
		this.label = label;
		this.id = id;
	}
}
