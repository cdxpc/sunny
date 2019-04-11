package com.sunny.module.db;

import com.sunny.core.constant.RestApiConstants;

/**
 * db module rest api 常量
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 *         kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月28日
 * @since 1.0.0v
 */
public class DbRestApiConstants extends RestApiConstants {

	private static final String DB = "db/";

	// dataBase
	private static final String DATA_BASE = "dataBase";
	public static final String REST_API_DATA_BASE = DB + DATA_BASE;
	public static final String REST_API_DATA_BASE_LIST = REST_API_DATA_BASE + SP + LIST;
	public static final String REST_API_DATA_BASE_FORM = REST_API_DATA_BASE + SP + FORM;

	// table
	private static final String TABLE = "table";
	public static final String REST_API_TABLE = DB + TABLE;
	public static final String REST_API_TABLE_LIST = REST_API_TABLE + SP + LIST;
	public static final String REST_API_TABLE_FORM = REST_API_TABLE + SP + FORM;

}
