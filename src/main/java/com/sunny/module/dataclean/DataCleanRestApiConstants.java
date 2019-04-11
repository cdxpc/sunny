package com.sunny.module.dataclean;

import com.sunny.core.constant.RestApiConstants;

/**
 * db data clean module rest api 常量
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 *         kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月18日
 * @since 1.0.0v
 */
public class DataCleanRestApiConstants extends RestApiConstants {

	private static final String DBCLEAN = "dataclean/";

	// rule
	private static final String RULE = "rule";
	public static final String REST_API_RULE = DBCLEAN + RULE;
	public static final String REST_API_RULE_LIST = REST_API_RULE + SP + LIST;
	public static final String REST_API_RULE_FORM = REST_API_RULE + SP + FORM;

	// task
	private static final String TASK = "task";
	public static final String REST_API_TASK = DBCLEAN + TASK;
	public static final String REST_API_TASK_LIST = REST_API_TASK + SP + LIST;
	public static final String REST_API_TASK_FORM = REST_API_TASK + SP + FORM;

}
