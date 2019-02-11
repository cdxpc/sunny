package com.sunny.core.util;

public class Converts {

	public static String toStr(Object value, String defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		if (value instanceof String) {
			return (String) value;
		}
		return value.toString();
	}

	public static String toStr(Object value) {
		return toStr(value, null);
	}

	public static Integer toInt(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		final String valueStr = toStr(value);
		if (StringUtils.isEmpty(valueStr)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Integer toInt(Object value) {
		return toInt(value, null);
	}
	
	/**
     * 	转换为boolean<br>
     *  String支持的值为：true、false、yes、ok、no，1,0 如果给定的值为空，或者转换失败，返回默认值<br>
     * 	转换失败不会报错
     * 
     * @param value 被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
	public static Boolean toBoolean(Object value, Boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		String valueStr = toStr(value, null);
		if (StringUtils.isEmpty(valueStr)) {
			return defaultValue;
		}
		valueStr = valueStr.trim().toLowerCase();
		switch (valueStr) {
		case "true":
			return true;
		case "false":
			return false;
		case "yes":
			return true;
		case "ok":
			return true;
		case "no":
			return false;
		case "1":
			return true;
		case "0":
			return false;
		default:
			return defaultValue;
		}
	}

    /**
     * 	转换为boolean<br>
     * 	如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * 	转换失败不会报错
     * 
     * @param value 被转换的值
     * @return 结果
     */
	public static Boolean toBoolean(Object value) {
		return toBoolean(value, null);
	}

}
