package com.sunny.module.db.database.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "T_DB_DATA_BASE")
@NoArgsConstructor(staticName = "of")
public class DataBase extends DbEntity {
	
	// 主键
	@Id
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
	private String password;
	// 是否本项目默认使用的数据库
	private String isDefault;

}
