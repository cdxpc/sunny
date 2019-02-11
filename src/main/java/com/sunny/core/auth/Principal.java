package com.sunny.core.auth;

import java.io.Serializable;

import lombok.Data;

@Data
public class Principal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String loginName;
	private String realName;
	private String password;
	private String salt;
	// 是否为手机端登录
	private boolean mobileLogin;
	
	// 此处应该和PasswordUtils盐加密方式保持一致
	public String getCredentialsSalt() {
		return this.loginName + this.salt;
	}

}
