package com.sunny.core.auth;

import com.google.code.kaptcha.Constants;
import com.sunny.core.ResponseJson;
import com.sunny.core.ServletHelper;
import com.sunny.core.auth.token.CaptchaUsernamePasswordToken;
import com.sunny.core.constant.PlatformConstants;
import com.sunny.core.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 对shiro安全框架相关操作辅助类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShiroHelper {
	
	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	private static Session getSession() {
		return getSubject().getSession();
	}
	
	public static Principal getPrincipal() {
		if(isLogin()) {
			return (Principal) getSubject().getPrincipal();
		}
		return null;
	}
	
	public static boolean isLogin() {
		return getSubject().getPrincipal() != null;
	}
	
	public static String getLoginUserId() {
		return getPrincipal() != null ? getPrincipal().getId() : null;
	}
	
	public static boolean isAuthenticated() {
		return getSubject().isAuthenticated();
	}
	
	// public static boolean isPermitted(String permission) {
	// 	return getSubject().isPermitted(permission);
	// }
	
	// public static boolean isPermittedAll(String ...permissions) {
	// 	return getSubject().isPermittedAll(permissions);
	// }

	private static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	private static void removeSessionAttribute(Object key) {
		getSession().removeAttribute(key);
	}

	private static String getHost() {
		return getSession().getHost();
	}

	// 从shiro session中获取验证码
	private static String getKaptcha() {
		try {
			String key = Constants.KAPTCHA_SESSION_KEY;
			String kaptcha = getSessionAttribute(key) == null ? null : getSessionAttribute(key).toString();
			removeSessionAttribute(key);
			return kaptcha;
		} catch (Exception e) {
			return null;
		}
	}
	
	// 登录
	public static ResponseJson login(String loginName, String password, String captcha, Boolean rememberMe) {
		// 从session中获取验证码
		String kaptcha = getKaptcha();
		boolean captchaEnabled = ServletHelper.getBoolAttr(PlatformConstants.CAPTCHA_ENABLED);
		// 验证码校验是否开启，开启了则进行校验，未开启则不校验
		if(captchaEnabled) {
			// 验证码为空或验证码输入错误，返回错误提示
			if(StringUtils.isEmpty(kaptcha) || !captcha.equalsIgnoreCase(kaptcha)) {
				return ResponseJson.captchaError();
			}
		}
		try {
			CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(loginName, password);
			token.setHost(getHost());
			token.setRememberMe(rememberMe);
			token.setCaptcha(captcha);
			token.setCaptchaEnabled(captchaEnabled);
			// shiro subject login by token
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException e) {
			return ResponseJson.authcFail();
		} catch (LockedAccountException e) {
			return ResponseJson.accountLocked();
		} catch (ExcessiveAttemptsException e) {
			return ResponseJson.authcPasswordRetryOut();
		}
		return ResponseJson.ok();
	}
	
	// 退出
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

}
