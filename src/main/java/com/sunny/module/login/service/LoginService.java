package com.sunny.module.login.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.sunny.core.constant.PlatformConstants;
import com.sunny.module.login.UserStatus;
import com.sunny.module.sys.menu.entity.Menu;
import com.sunny.module.sys.menu.service.MenuService;
import com.sunny.module.sys.role.entity.Role;
import com.sunny.module.sys.role.service.RoleService;
import com.sunny.module.sys.user.entity.User;
import com.sunny.module.sys.user.service.UserService;

@Component
public class LoginService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	// loginName -> 值可以匹配登录账户，邮箱，手机号码
	public User login(String loginName, String password) {
		// 用户名或密码为空，给出错误提示
		if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
//			SystemLogUtils.log(loginName, PlatformConstants.LOGIN_FAIL, MessageUtils.message("user.loginName.password.is.null"));
			throw new UnknownAccountException();
		}
		// 用户名不在指定范围内 错误
		if (loginName.length() < PlatformConstants.USERNAME_MIN_LENGTH || loginName.length() > PlatformConstants.USERNAME_MAX_LENGTH) {
//			SystemLogUtils.log(loginName, PlatformConstants.LOGIN_FAIL, MessageUtils.message("user.loginName.not.match"));
			throw new UnknownAccountException();
		}
		// 密码如果不在指定范围内 错误
		if (password.length() < PlatformConstants.PASSWORD_MIN_LENGTH || password.length() > PlatformConstants.PASSWORD_MAX_LENGTH) {
//			SystemLogUtils.log(loginName, PlatformConstants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
			throw new UnknownAccountException();
		}
		// 查询用户信息
		User user = userService.findByLoginName(loginName);
		
		if(user == null) {
//			SystemLogUtils.log(loginName, PlatformConstants.LOGIN_FAIL, MessageUtils.message("user.not.exists", "用户不存在"));
			throw new AuthenticationException();
		} else if(UserStatus.LOCKED.getCode().equals(user.getStatus())) {
//			 SystemLogUtils.log(loginName, PlatformConstants.LOGIN_FAIL, MessageUtils.message("user.blocked", "用户被锁定"));
	         throw new LockedAccountException();
		}
		
//		SystemLogUtils.log(loginName, PlatformConstants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
		
		return user;
	}

	public Set<String> findRolesByUserId(String userId) {
		Set<String> roleCodes = new HashSet<>();
		List<Role> roles = roleService.findRolesByUserId(userId);
		if(CollectionUtils.isNotEmpty(roles)) {
			for(Role role : roles) {
				roleCodes.add(role.getRoleCode());
			}
		}
		return roleCodes;
	}

	public Set<String> findMenusByUserId(String userId) {
		// 使用set能够去重
		Set<String> perms = new HashSet<>();
		List<Menu> menus = menuService.findMenusByUserId(userId);
		if(CollectionUtils.isNotEmpty(menus)) {
			for(Menu menu : menus) {
				perms.addAll(Arrays.asList(menu.getPerms().trim().split(",")));
			}
		}
		return perms;
	}

	public void recordLoginInfo(User user) {
		// TODO Auto-generated method stub
		
	}
	

}
