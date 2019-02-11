package com.sunny.core.util;

import java.util.UUID;

public class UuidUtils {
	
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
