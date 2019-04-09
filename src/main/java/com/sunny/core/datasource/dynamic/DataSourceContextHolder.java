package com.sunny.core.datasource.dynamic;

import java.util.HashMap;
import java.util.Map;

import com.sunny.core.datasource.model.DataSourceBean;

/**
 * 与当前线程绑定的动态数据源映射
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月25日
 * @since 1.0.0v
 */
public class DataSourceContextHolder {
	
	private static final ThreadLocal<Map<String, DataSourceBean>> threadLocal = new ThreadLocal<>();
	private static Map<String, DataSourceBean> currentDataSource = null;
	
	/**
	 * 获取动态数据源
	 * @return
	 */
	public static Map<String, DataSourceBean> getDynamicDataSource() {
		return threadLocal.get();
	}

	/**
	 * 设置动态数据源
	 * @param source
	 */
	public static void setDynamicDataSource(DataSourceBean dataSourceBean) {
		if(dataSourceBean != null) {
			currentDataSource = new HashMap<>();
			currentDataSource.put(dataSourceBean.getSpringBeanName(), dataSourceBean);
			threadLocal.set(currentDataSource);
		}
	}
	
	/**
	 * 清除动态数据源，则使用默认的数据源
	 */
	public static void toDefault() {
		threadLocal.remove();
	}
	
}
