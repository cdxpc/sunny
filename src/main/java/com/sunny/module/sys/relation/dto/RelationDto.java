package com.sunny.module.sys.relation.dto;

import lombok.Data;

@Data
public class RelationDto {
	
	// 用户
	private String userId;
	private String userCode;
	private String realName;
	// 角色
	private String roleId;
	private String roleCode;
	private String roleName;
	// 机构
	private String orgId;
	private String orgCode;
	private String orgName;
	// 岗位
	private String postId;
	private String postCode;
	private String postName;
	// 菜单
	private String menuId;
	private String menuName;

}
