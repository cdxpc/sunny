package com.sunny.core.constant;

public class RestApiConstants {
	
	// login、index、home...
	public static final String CAPTCHA = "captcha";
	public static final String LOGIN = "login";
	public static final String LOGOUT = "logout";
	public static final String DASHBOARD = "dashboard/";
	public static final String INDEX = "index";
	public static final String WELCOME = "welcome";
	
	// common
	public static final String SP = "/";
	public static final String SYS = "sys/";
	public static final String LIST = "list"; 		// 列表页面
	public static final String DATA = "data"; 		// 列表数据
	public static final String FORM = "form";		// 新增、编辑、查看页面
	public static final String ADD = "add";			// 新增页面
	public static final String EDIT = "edit";		// 编辑页面
	public static final String SAVE = "save";		// 保存按钮
	public static final String REMOVE = "remove"; 	// 删除按钮（单个、批量）
	public static final String ICON = "icon";		// 
	public static final String TREE = "tree";		// 树
	
	// user
	public static final String USER = "user";
	public static final String REST_API_USER = SYS + USER;
	public static final String REST_API_USER_LIST = REST_API_USER + SP + LIST;
	public static final String REST_API_USER_ADD = REST_API_USER + SP + ADD;
	public static final String REST_API_USER_EDIT = REST_API_USER + SP + EDIT;
	public static final String REST_API_USER_SAVE = REST_API_USER + SP + SAVE;
	public static final String REST_API_USER_REMOVE = REST_API_USER + SP + REMOVE;
	
	// role
	public static final String ROLE = "role";
	public static final String REST_API_ROLE = SYS + ROLE;
	public static final String REST_API_ROLE_LIST = REST_API_ROLE + SP + LIST;
	public static final String REST_API_ROLE_ADD = REST_API_ROLE + SP + ADD;
	public static final String REST_API_ROLE_EDIT = REST_API_ROLE + SP + EDIT;
	public static final String REST_API_ROLE_SAVE = REST_API_ROLE + SP + SAVE;
	public static final String REST_API_ROLE_REMOVE = REST_API_ROLE + SP + REMOVE;
	
	// org
	public static final String ORG = "org";
	public static final String REST_API_ORG = SYS + ORG;
	public static final String REST_API_ORG_LIST = REST_API_ORG + SP + LIST;
	public static final String REST_API_ORG_FORM = REST_API_ORG + SP + FORM;
	public static final String REST_API_ORG_ADD = REST_API_ORG + SP + ADD;
	public static final String REST_API_ORG_EDIT = REST_API_ORG + SP + EDIT;
	public static final String REST_API_ORG_SAVE = REST_API_ORG + SP + SAVE;
	public static final String REST_API_ORG_REMOVE = REST_API_ORG + SP + REMOVE;
	
	// menu
	public static final String MENU = "menu";
	public static final String REST_API_MENU = SYS + MENU;
	public static final String REST_API_MENU_LIST = REST_API_MENU + SP + LIST;
	public static final String REST_API_MENU_ADD = REST_API_MENU + SP + ADD;
	public static final String REST_API_MENU_EDIT = REST_API_MENU + SP + EDIT;
	public static final String REST_API_MENU_SAVE = REST_API_MENU + SP + SAVE;
	public static final String REST_API_MENU_REMOVE = REST_API_MENU + SP + REMOVE;
	public static final String REST_API_MENU_ICON = REST_API_MENU + SP + ICON;
	public static final String REST_API_MENU_TREE = REST_API_MENU + SP + TREE;
	
	// dict
	public static final String DICT = "dict";
	// type/value list
	public static final String REST_API_DICT_LIST = SYS + DICT + SP + LIST;
	// type
	public static final String DICT_TYPE = DICT + SP + "type";
	public static final String REST_API_DICT_TYPE = SYS + DICT_TYPE;
	public static final String REST_API_DICT_TYPE_FORM = REST_API_DICT_TYPE + SP + FORM;
	public static final String REST_API_DICT_TYPE_ADD = REST_API_DICT_TYPE + SP + ADD;
	public static final String REST_API_DICT_TYPE_EDIT = REST_API_DICT_TYPE + SP + EDIT;
	public static final String REST_API_DICT_TYPE_SAVE = REST_API_DICT_TYPE + SP + SAVE;
	public static final String REST_API_DICT_TYPE_REMOVE = REST_API_DICT_TYPE + SP + REMOVE;
	// value
	public static final String DICT_VALUE = DICT + SP + "value";
	public static final String REST_API_DICT_VALUE = SYS + DICT_VALUE;
	public static final String REST_API_DICT_VALUE_FORM = REST_API_DICT_VALUE + SP + FORM;
	public static final String REST_API_DICT_VALUE_ADD = REST_API_DICT_VALUE + SP + ADD;
	public static final String REST_API_DICT_VALUE_EDIT = REST_API_DICT_VALUE + SP + EDIT;
	public static final String REST_API_DICT_VALUE_SAVE = REST_API_DICT_VALUE + SP + SAVE;
	public static final String REST_API_DICT_VALUE_REMOVE = REST_API_DICT_VALUE + SP + REMOVE;
	
	
}
