package com.sunny.core.datasource;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源数据源类型-url、类型-驱动映射
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月25日
 * @since 1.0.0v
 */
public class DbType {
	
	// 数据源访问url映射集
	public static Map<String, String> urls = new HashMap<>();
	// 数据源驱动映射集
	public static Map<String, String> drivers = new HashMap<>();
	
	static {
		urls.put("1", "jdbc:mysql://%s:3306/%s");
		urls.put("2", "jdbc:oracle:thin:@//%s:1521/%s");
		
		drivers.put("1", "com.mysql.jdbc.Driver");
		drivers.put("2", "oracle.jdbc.driver.OracleDriver");
	}
	
	public static String getDriverByDbType(String type) {
		return drivers.get(type) == null ? drivers.get("oralce") : drivers.get(type);
	}
	
	public static String getUrlByDbType(String type) {
		return urls.get(type) == null ? urls.get("oralce") : urls.get(type);
	}
	
}
