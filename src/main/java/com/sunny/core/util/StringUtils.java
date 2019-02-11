package com.sunny.core.util;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

}
