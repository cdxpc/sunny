package com.sunny.module.login.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunny.core.auth.Principal;
import com.sunny.core.auth.ShiroHelper;
import com.sunny.core.auth.token.CaptchaUsernamePasswordToken;
import com.sunny.module.login.service.LoginService;
import com.sunny.module.sys.user.entity.User;

public class SimpleLoginRealm extends AuthorizingRealm {
	
	@Autowired
	private LoginService loginService;

	/**
	 * 认证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CaptchaUsernamePasswordToken cupToken = (CaptchaUsernamePasswordToken) token;
		Principal principal = new Principal();
		principal.setLoginName(cupToken.getUsername());
		principal.setPassword(new String(cupToken.getPassword()));
		principal.setMobileLogin(cupToken.isMobile());
		// 更加登录名获取登录用户信息
		User user = loginService.login(principal.getLoginName(), principal.getPassword());
		if(user != null) {
			principal.setId(user.getUserId());
			principal.setRealName(user.getRealName());
			principal.setSalt(user.getSalt());
		}
		
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(principal, 
				user.getPassword(), Util.bytes(principal.getCredentialsSalt()), getName());
		
		// 认证成功之后更新数据库
		if(ShiroHelper.isAuthenticated()) {
			loginService.recordLoginInfo(user);
		}
		
		return authInfo;
	}

	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userId = ShiroHelper.getLoginUserId();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(loginService.findRolesByUserId(userId));
		info.setStringPermissions(loginService.findMenusByUserId(userId));
		return info;
	}

}
