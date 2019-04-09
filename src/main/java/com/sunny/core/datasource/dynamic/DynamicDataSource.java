package com.sunny.core.datasource.dynamic;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.sunny.core.datasource.DynamicDataSourceHelper;
import com.sunny.core.datasource.model.DataSourceBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源路由
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年3月25日
 * @since 1.0.0v
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {
	
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			if(log.isDebugEnabled()) {
				log.debug("当前spring context容器中已经实例化的bean对象名称为：==>" + name);
			}
		}
	}

	@Override
	protected Object determineCurrentLookupKey() {
		// 1、获取手动设置的数据源参数
		Map<String, DataSourceBean> dsKeyValue = DataSourceContextHolder.getDynamicDataSource();
		if(dsKeyValue == null) {
			return null;
		}
		try {
			// 2、获取AbstractRoutingDataSource中的targetDataSources属性值
			Map<Object, Object> targetSourceMap = DynamicDataSourceHelper.getTargetDataSources(this);
			if(targetSourceMap != null && targetSourceMap.size() > 0) {
				synchronized (this) {
					/*
					 * 3、判断targetSourceMap中是否存在需要使用的数据源
					 * 	如果存在，则直接返回
					 *  如果不存在，则在spring容器中实例化一个对应的数据源bean
					 */
					Entry<String, DataSourceBean> entry = dsKeyValue.entrySet().iterator().next();
					String dsKey = entry.getKey();
					if(!targetSourceMap.keySet().contains(dsKey)) {
						// 3.1 在spring容器中创建一个数据源bean对象
						DataSourceBean sourceBean = entry.getValue();
						if(log.isDebugEnabled()) {
							log.debug(dsKey + " -> " + sourceBean);
						}
						if(sourceBean != null) {
							Object dataSource = DynamicDataSourceHelper.createDynamicDataSource(DataSourceBean.class, sourceBean, context, dsKey, false);
							// 3.2 创建好了bean之后，将其放入到targetSourceMap中
							targetSourceMap.put(dsKey, dataSource);
							/*
							 *  3.3 通知spring容器有bean的更新
							 *  主要更新AbstractRoutingDataSource的resolvedDefaultDataSource属性
							 *  更新完之后AbstractRoutingDataSource的determineTargetDataSource方法才能找到新的数据源信息，
							 *  代码如下：
							 *    Object lookupKey = determineCurrentLookupKey();
							 *    DataSource dataSource = (DataSource)resolvedDataSources.get(lookupKey);
							 */
							super.afterPropertiesSet();
						}
					}
					return dsKey;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
