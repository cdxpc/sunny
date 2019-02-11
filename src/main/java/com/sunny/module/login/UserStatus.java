package com.sunny.module.login;

import lombok.Getter;

public enum UserStatus {
	
	VALID("1", "正常"),
	LOCKED("2", "锁定"),
	;
	
	@Getter
	String code;
	@Getter
	String info;
	
	UserStatus(String code, String info) {
		this.code = code;
		this.info = info;
	}
}
