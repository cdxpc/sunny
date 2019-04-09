package com.sunny.core.util;

import javax.servlet.http.HttpServletRequest;

import com.sunny.core.ServletHelper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ajax 操作工具类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AjaxUtils {
	
	/**
	 * 	判断是否是Ajax异步请求
	 * @return boolean
	 */
	public static boolean isAjaxRequest() {
		HttpServletRequest request = ServletHelper.getRequest();
		String accept = request.getHeader("accept");
		if (accept != null && accept.indexOf("application/json") != -1) {
			return true;
		}
		String xRequestedWith = request.getHeader("X-Requested-With");
		if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
			return true;
		}
		String uri = request.getRequestURI();
		if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
			return true;
		}
		String ajax = request.getParameter("__ajax");
		if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
			return true;
		}
		return false;
	}

}
