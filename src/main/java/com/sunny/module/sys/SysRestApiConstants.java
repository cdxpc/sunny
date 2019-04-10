package com.sunny.module.sys;

import com.sunny.core.constant.RestApiConstants;

/**
 * sys module rest api 常量
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月18日
 * @since 1.0.0v
 */
public class SysRestApiConstants extends RestApiConstants {

	private static final String SYS = "sys/";

	// user
	private static final String USER = "user";
	public static final String REST_API_USER = SYS + USER;
	public static final String REST_API_USER_LIST = REST_API_USER + SP + LIST;
	public static final String REST_API_USER_FORM = REST_API_USER + SP + FORM;

	// role
	private static final String ROLE = "role";
	public static final String REST_API_ROLE = SYS + ROLE;
	public static final String REST_API_ROLE_LIST = REST_API_ROLE + SP + LIST;
	public static final String REST_API_ROLE_FORM = REST_API_ROLE + SP + FORM;

	// org
	private static final String ORG = "org";
	public static final String REST_API_ORG = SYS + ORG;
	public static final String REST_API_ORG_LIST = REST_API_ORG + SP + LIST;
	public static final String REST_API_ORG_FORM = REST_API_ORG + SP + FORM;
	public static final String REST_API_ORG_TREE = REST_API_ORG + SP + TREE_VIEW;

	// post
	private static final String POST = "post";
	public static final String REST_API_POST = SYS + POST;
	public static final String REST_API_POST_LIST = REST_API_POST + SP + LIST;
	public static final String REST_API_POST_FORM = REST_API_POST + SP + FORM;
	public static final String REST_API_POST_TREE = REST_API_POST + SP + TREE_VIEW;

	// menu
	private static final String MENU = "menu";
	public static final String REST_API_MENU = SYS + MENU;
	public static final String REST_API_MENU_LIST = REST_API_MENU + SP + LIST;
	public static final String REST_API_MENU_FORM = REST_API_MENU + SP + FORM;
	public static final String REST_API_MENU_TREE = REST_API_MENU + SP + TREE_VIEW;

	// dict
	private static final String DICT = "dict";
	// type/value list
	public static final String REST_API_DICT_LIST = SYS + DICT + SP + LIST;
	// type
	private static final String DICT_TYPE = DICT + SP + "type";
	public static final String REST_API_DICT_TYPE = SYS + DICT_TYPE;
	public static final String REST_API_DICT_TYPE_FORM = REST_API_DICT_TYPE + SP + FORM;
	// value
	private static final String DICT_VALUE = DICT + SP + "value";
	public static final String REST_API_DICT_VALUE = SYS + DICT_VALUE;
	public static final String REST_API_DICT_VALUE_FORM = REST_API_DICT_VALUE + SP + FORM;

	// area
	private static final String AREA = "area";
	public static final String REST_API_AREA = SYS + AREA;
	public static final String REST_API_AREA_LIST = REST_API_AREA + SP + LIST;
	public static final String REST_API_AREA_FORM = REST_API_AREA + SP + FORM;
	public static final String REST_API_AREA_TREE = REST_API_AREA + SP + TREE_VIEW;
}
