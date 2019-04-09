package com.sunny.core.filter;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sunny.core.constant.PlatformConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证码校验过滤器
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Slf4j
@Component
public class CaptchaFilter extends AccessControlFilter {

	// 是否开启验证码
	@Value("${sunny.shiro.captcha.enabled}")
	private boolean captchaEnabled;
	// 验证码类型
	@Value("${sunny.shiro.captcha.type}")
	private String captchaType;

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		request.setAttribute(PlatformConstants.CAPTCHA_ENABLED, captchaEnabled);
		request.setAttribute(PlatformConstants.CAPTCHA_TYPE, captchaType);
		// 日志打印request中attr里面的内容
		Enumeration<String> names = request.getAttributeNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			if(log.isDebugEnabled()) {
				log.debug(name + "=>" + request.getAttribute(name));
			}
		}
		return super.onPreHandle(request, response, mappedValue);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return false;
	}

}
