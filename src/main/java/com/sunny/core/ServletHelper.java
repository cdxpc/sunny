package com.sunny.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sunny.core.constant.PlatformConstants;
import com.sunny.core.util.Converts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * servlet 操作辅助类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServletHelper {
	
	/**
	 * 	获取请求中的参数对象
	 * 
	 * @return
	 */
	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return (ServletRequestAttributes) requestAttributes;
	}

	/**
	 * 	获取请求对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * 	获取响应对象
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return getRequestAttributes().getResponse();
	}

	/**
	 * 	获取请求的session对象
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	// ============ attributes ================
	
	/**
	 * 	获取指定参数  - String
	 */
	public static String getAttr(String name) {
		return getAttr(name, null);
	}

	/**
	 * 	获取指定参数，如果为空，使用默认值
	 */
	public static String getAttr(String name, String defValue) {
		return Converts.toStr(getRequest().getAttribute(name), defValue);
	}
	
	/**
	 * 	获取指定参数  - Integer
	 */
	public static Integer getIntAttr(String name) {
		return getIntAttr(name, null);
	}

	/**
	 * 	获取指定参数，如果为空，使用默认值
	 */
	public static Integer getIntAttr(String name, Integer defValue) {
		return Converts.toInt(getRequest().getAttribute(name), defValue);
	}
	
	/**
	 * 	获取指定参数  - boolean
	 */
	public static boolean getBoolAttr(String name) {
		return getBoolAttr(name, false);
	}
	
	/**
	 * 	获取指定参数，如果为空，使用默认值
	 */
	public static boolean getBoolAttr(String name, boolean defValue) {
		Boolean bool = Converts.toBoolean(getRequest().getAttribute(name), defValue);
		return bool == null ? defValue : bool;
	}
	
	// ============ parameters ================

	/**
	 * 	获取指定参数  - String
	 */
	public static String getParameter(String name) {
		return getParameter(name, null);
	}

	/**
	 * 	获取指定参数，如果为空，使用默认值
	 */
	public static String getParameter(String name, String defValue) {
		return Converts.toStr(getRequest().getParameter(name), defValue);
	}

	/**
	 * 	获取指定参数  - Integer
	 */
	public static Integer getIntParameter(String name) {
		return getIntParameter(name, null);
	}

	/**
	 * 	获取指定参数，如果为空，使用默认值
	 */
	public static Integer getIntParameter(String name, Integer defValue) {
		HttpServletRequest request = getRequest();
		String parameter = request.getParameter(name);
		return Converts.toInt(parameter, defValue);
//		return Converts.toInt(getRequest().getParameter(name), defValue);
	}
	
	/**
	 * 	获取指定参数  - boolean
	 */
	public static boolean getBoolParameter(String name) {
		return getBoolParameter(name, false);
	}
	
	/**
	 * 	获取指定参数，如果为空，使用默认值
	 */
	public static boolean getBoolParameter(String name, boolean defValue) {
		Boolean bool = Converts.toBoolean(getRequest().getParameter(name), defValue);
		return bool == null ? defValue : bool;
	}

	/**
	 * 	渲染到客户端
	 * @param string
	 * @return String
	 */
	public static String renderString(String string) {
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("application/json");
			response.setCharacterEncoding(PlatformConstants.UTF8);
			response.getWriter().print(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
