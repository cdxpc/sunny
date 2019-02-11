package com.sunny.module.sys.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
@Table(name = "FT_SYS_USER")
public class User extends DbEntity {
	
	@Id
	private String userId;
	private String loginName;
	private String password;
	private String salt;
	private String realName;
	private String gender;
	private String mobile;
	private String email;
	private String headImg;		// 头像
	private String userType; 	// 用户类型
	
	// 判断用户是否是超级管理员  （admin, plat, operator, test）
//	public boolean isAdmin(String userTypeId) {
//		return userTypeId != null && "admin".equalsIgnoreCase(userTypeId);
//	}

}
