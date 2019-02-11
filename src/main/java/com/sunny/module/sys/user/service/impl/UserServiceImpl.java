package com.sunny.module.sys.user.service.impl;

import org.springframework.stereotype.Service;

import com.sunny.core.constant.RegxConstants;
import com.sunny.module.common.CudServiceImpl;
import com.sunny.module.sys.user.dto.UserDto;
import com.sunny.module.sys.user.entity.User;
import com.sunny.module.sys.user.mapper.UserMapper;
import com.sunny.module.sys.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends CudServiceImpl<User, UserDto, UserMapper> implements UserService {

	@Override
	public User findByLoginName(String loginName) {
		// 先根据登录名查询
		User user = byloginName(loginName);
		// 再匹配手机号码
		if(user == null) {
			user = byMobile(loginName);
		}
		// 在匹配邮箱
		if(user == null) {
			user = byEmail(loginName);
		}
		return user;
	}
	
	private User byloginName(String loginName) {
		User u = User.of();
		u.setLoginName(loginName);
		return findOne(u);
	}

	// 使用手机号码登录
	private User byMobile(String loginName) {
		if(loginName.matches(RegxConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
			User u = User.of();
			u.setMobile(loginName);
			return findOne(u);
		}
		return null;
	}
	
	// 使用注册邮箱登录
	private User byEmail(String loginName) {
		if(loginName.matches(RegxConstants.EMAIL_PATTERN)) {
			User u = User.of();
			u.setEmail(loginName);
			return findOne(u);
		}
		return null;
	}
	
}
