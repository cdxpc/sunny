package com.sunny.core.util;

import java.util.UUID;

/**
 * uuid 操作工具类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
public class UuidUtils {
	
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
