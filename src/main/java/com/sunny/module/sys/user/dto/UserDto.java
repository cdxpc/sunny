package com.sunny.module.sys.user.dto;

import java.util.Date;

import com.sunny.core.persistence.DtoEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends DtoEntity {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String loginName;
//	private String password;
//	private String salt;
	private String realName;
	private String gender;
	private String mobile;
	private String email;
	private String headImg;		// 头像
	private String userType; 	// 用户类型
	private String remark;
	private String lastLoginIp;
	private Date lastLoginDate;
	
	private String orgId;   // 机构id
	private String orgName; // 机构名称

}
