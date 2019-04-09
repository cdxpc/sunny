package com.sunny.boot;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.sunny.core.datasource.dynamic.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Configuration
public class DynamicDataSourceConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public DruidDataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
	public DataSource dynamicDataSource() {
		DruidDataSource dataSource = dataSource();
		Map<Object, Object> ds = new HashMap<>();
		ds.put("defaultDataSource", dataSource);
		DynamicDataSource dds = new DynamicDataSource();
		dds.setDefaultTargetDataSource(dataSource);
		dds.setTargetDataSources(ds);
		return dds;
	}

}
