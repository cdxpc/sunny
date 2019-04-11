package com.sunny.module.db.database.domain;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBaseDto extends DtoEntity {

	private static final long serialVersionUID = 1L;
	
	// 主键
	private String dbId;
	// 数据库拥有者
	private String owner;
	// 数据库名称
	private String dbName;
	// 数据库类型
	private String dbType; // 做成字典
	// 数据库IP地址
	private String dbIp;
	// 数据源登录名
	private String username;
	// 数据源登录密码
	private String password;  // XXX 是否需要屏蔽该字段？
	// 是否本项目默认使用的数据库
	private String isDefault;

}
