package com.sunny.module.sys.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.core.ResponseJson;
import com.sunny.core.auth.Principal;
import com.sunny.core.auth.ShiroHelper;
import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.core.constant.RegxConstants;
import com.sunny.module.sys.user.entity.User;
import com.sunny.module.sys.user.mapper.UserMapper;
import com.sunny.module.sys.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends CudServiceImpl<User, UserMapper> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User create(User user, boolean allowNull) {
		user.setSalt("abcd=="); 	// 加密盐采用何种方式需要考虑下
		user.setPassword("123456"); // XXX 密码需要使用加密盐进行加密处理
		return super.create(user, allowNull);
	}

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

	@Override
	public ResponseJson restPwd(String userId) {
		// 获取当前登录的用户，判断是否为超级管理员，只有超级管理员才能有重置密码的权限
		Principal principal = ShiroHelper.getPrincipal();
		if(principal != null && "1".equals(principal.getUserType())) {
			User user = userMapper.selectByPrimaryKey(userId);
			if(user != null) {
//				String salt = user.getSalt();
				user.setPassword("123456"); // XXX 密码需要更加盐来进行加密
				userMapper.updateByPrimaryKeySelective(user);
				return ResponseJson.ok();
			}
		}
		return ResponseJson.noPerm(); // 非超级管理员，提示操作权限受限
	}
	
}
