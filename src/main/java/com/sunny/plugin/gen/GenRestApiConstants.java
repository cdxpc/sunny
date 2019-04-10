package com.sunny.plugin.gen;

import com.sunny.core.constant.RestApiConstants;

/**
 * gen module rest api 常量
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月18日
 * @since 1.0.0v
 */
public class GenRestApiConstants extends RestApiConstants {

	private static final String PLUGIN = "plugin/";

	// gen
	public static final String REST_API_GEN = "gen";
	public static final String REST_API_GEN_LIST = PLUGIN + REST_API_GEN + SP + LIST;
	public static final String REST_API_GEN_FORM = PLUGIN + REST_API_GEN + SP + FORM;

}
