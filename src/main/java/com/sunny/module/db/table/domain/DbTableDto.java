package com.sunny.module.db.table.domain;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbTableDto extends DtoEntity {

	private static final long serialVersionUID = 1L;
	
	private String id;
	// 表名
	private String tableName;
	// 表注释
	private String tableComment;
	
	// 列名
	private String columnName;
	// 列注释
	private String columnComment;
	// 列类型
	private String dataType;
	
	// 是否是表的列
	private String isColumn;
	
	// 是否已经生成过代码
	private String produced; // 1-是  2-否
	
	// 所属数据库
	private String dbId;
	private String dbName;

}
