package com.sunny.core.auth;

import java.io.Serializable;

import lombok.Data;

/**
 * 对登陆用户信息进行部分存储的类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Data
public class Principal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String loginName;
	private String realName;
	private String password;
	private String userType;
	private String salt;
	// 是否为手机端登录
	private boolean mobileLogin;
	
	// 此处应该和PasswordUtils盐加密方式保持一致
	public String getCredentialsSalt() {
		return this.loginName + this.salt;
	}

}
