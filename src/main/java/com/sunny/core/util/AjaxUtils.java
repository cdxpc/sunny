package com.sunny.core.util;

import javax.servlet.http.HttpServletRequest;

import com.sunny.core.ServletHelper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AjaxUtils {
	
	/**
	 * 	判断是否是Ajax异步请求
	 * @param request
	 * @return
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
