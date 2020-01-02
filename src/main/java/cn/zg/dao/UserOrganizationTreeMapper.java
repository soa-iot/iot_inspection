package cn.zg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zg.entity.UserOrganization;

@Mapper
public interface UserOrganizationTreeMapper {
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<UserOrganization> findAll(); 

}