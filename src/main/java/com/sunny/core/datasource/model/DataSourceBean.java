package com.sunny.core.datasource.model;

import com.sunny.core.datasource.DbType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
@ToString
public class DataSourceBean {  // 动态数据源参数bean
	
	public void setUrl(String type, String ip, String dbName) {
		this.url = String.format(DbType.getUrlByDbType(type), ip, dbName);
	}
	
	public void setDriverClassName(String type) {
		this.driverClassName = DbType.getDriverByDbType(type);
	}
	
	// spring bean name
	private String springBeanName;
	// 数据库url
	private String url;
	// 数据库驱动
	private String driverClassName;
	// 登录名
	private String username;
	// 登录密码
	private String password;
//	@Value("${filters}")
//	private String filters;
//	@Value("${maxActive}")
//	private int maxActive;
//	@Value("${initialSize}")
//	private int initialSize;
//	@Value("${maxWait}")
//	private long maxWait;
//	@Value("${minIdle}")
//	private int minIdle;
//	@Value("${timeBetweenEvictionRunsMillis}")
//	private long timeBetweenEvictionRunsMillis;
//	@Value("${minEvictableIdleTimeMillis}")
//	private long minEvictableIdleTimeMillis;
//	@Value("${validationQuery}")
//	private String validationQuery;
//	@Value("${testWhileIdle}")
//	private boolean testWhileIdle;
//	@Value("${testOnBorrow}")
//	private boolean testOnBorrow;
//	@Value("${testOnReturn}")
//	private boolean testOnReturn;
//	@Value("${maxOpenPreparedStatements}")
//	private int maxOpenPreparedStatements;
//	@Value("${removeAbandoned}")
//	private boolean removeAbandoned;
//	@Value("${removeAbandonedTimeout}")
//	private long removeAbandonedTimeout;
//	@Value("${logAbandoned}")
//	private boolean logAbandoned;

}
