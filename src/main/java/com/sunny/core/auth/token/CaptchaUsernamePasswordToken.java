package com.sunny.core.auth.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
