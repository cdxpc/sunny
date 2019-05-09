package com.sunny.core.constant;

/**
 * 平台常量类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
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
	
	// 状态通用码值
	public static final String STATUS_YES = "1";   // 1-是
	public static final String STATUS_NO = "2";	// 2-否

	public static final String temp = "{\"sources\":[\"源数据源ID(操作默认数据源，可为空)\",\"目标数据源ID（操作单个数据源可为空）\"],\"tableAndColumn\":{\"tableName\":\"数据库表名\",\"andOr\":\"多个条件之间的连接方式，单个条件时，可为空\",\"whereColumns\":[{\"columnName\":\"列名1\",\"columnValue\":\"列值1\",\"connector\":\"条件方式(eg: >, <, >=, <=, !=等)\"},{\"columnName\":\"列名2\",\"columnValue\":\"列值2\",\"connector\":\"条件方式(eg: >, <, >=, <=, !=等)\"}]},\"pages\":\"每次操作数据量(eg: 100)\"}";

}
