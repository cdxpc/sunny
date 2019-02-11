package com.sunny.core.util;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * javaBean 属性相互拷贝辅助类
 * 
 * @author kevin.chen crsfyc-9@163.com   
 * @since 1.0.0.0 
 */
@Slf4j
public class JavaBeanUtils {
	
	public static void copyBean2Bean(Object tobean, Object databean) throws Exception {
		BeanUtils.copyProperties(tobean, databean);
	}
	
	public static void copyBeanNotNull2Bean(Object tobean, Object databean) throws Exception {
		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(databean);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			// String type = origDescriptors[i].getPropertyType().toString();
			if ("class".equals(name)) {
				continue; // No point in trying to set an object's class
			}
			if (PropertyUtils.isReadable(databean, name)
					&& PropertyUtils.isWriteable(tobean, name)) {
				try {
					Object value = PropertyUtils.getSimpleProperty(databean, name);
					if (isNotEmpty(value)) {
						BeanUtils.copyProperty(tobean, name, value);
					}
				} catch (Exception e) {
					log.error("the exception in [BeanUtils.class#copyBeanNotNull2Bean]" ,e);
				}
			}
		}
	}
	
	/**
	 * 判断一个对象是不是null 或   ""
	 * @param obj
	 * @return
	 */
	private static boolean isNotEmpty(Object obj) {
		if(obj == null) return false;
		if(obj instanceof Boolean) {
			return ((Boolean) obj).booleanValue();
		}
		if(obj instanceof String) {
			return StringUtils.isNotEmpty(obj.toString());
		}
		if(obj instanceof Collection<?>) {
			Collection<?> c = (Collection<?>) obj;
			return c.size() > 0;
		}
		if(obj instanceof Map<?, ?>) {
			Map<?, ?> m = (Map<?, ?>) obj;
			return m.size() > 0;
		}
		return true;
	}

}
