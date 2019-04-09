package com.sunny.core.auth.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登陆用户token
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	
	private String captcha;
	private boolean captchaEnabled;
	private boolean isMobile = false;
	
	public CaptchaUsernamePasswordToken(String username, String password) {
		super(username, password);
	}

}
