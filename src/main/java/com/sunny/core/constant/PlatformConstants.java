package com.sunny.core.constant;

public class PlatformConstants {

	/**
	 * UTF-8 字符集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * 通用成功标识
	 */
//	public static final String SUCCESS = "S";

	/**
	 * 通用失败标识
	 */
//	public static final String FAIL = "F";

	/**
	 * 登录成功
	 */
	public static final String LOGIN_SUCCESS = "Success";

	/**
	 * 注销
	 */
	public static final String LOGOUT = "Logout";

	/**
	 * 登录失败
	 */
	public static final String LOGIN_FAIL = "Error";

	/**
	 * 自动去除表前缀
	 */
	public static final String AUTO_REOMVE_PRE = "true";

	/**
	 * 当前记录起始索引
	 */
	public static final String PAGE_NUM = "pageNum";

	/**
	 * 每页显示记录数
	 */
	public static final String PAGE_SIZE = "pageSize";

	/**
	 * 排序列
	 */
	public static final String ORDER_BY_COLUMN = "orderByColumn";

	/**
	 * 排序的方向 "desc" 或者 "asc".
	 */
	public static final String IS_ASC = "isAsc";
	
	/**
	 * 验证码是否开启  "true" 或者 "false".
	 */
	public static final String CAPTCHA_ENABLED = "captchaEnabled";
	
	/**
	 * 验证码类型  "char" 或者 "math".
	 */
	public static final String CAPTCHA_TYPE = "captchaType";
	
	/**
	 * 日期格式.
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 用户名长度限制
	 */
	public static final int USERNAME_MIN_LENGTH = 2;
	public static final int USERNAME_MAX_LENGTH = 20;

	/**
	 * 密码长度限制
	 */
	public static final int PASSWORD_MIN_LENGTH = 5;
	public static final int PASSWORD_MAX_LENGTH = 20;

}
