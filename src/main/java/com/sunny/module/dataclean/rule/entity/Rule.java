package com.sunny.module.dataclean.rule.entity;

import com.sunny.core.persistence.DbEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "T_DATA_CLEAN_RULE")
@NoArgsConstructor(staticName = "of")
public class Rule extends DbEntity {

	// 规则ID
	@Id
	private String ruleId;
	// 规则类
	private String ruleClassName;
	// 规则类介绍
	private String classComment;
	// 规则方法
	private String ruleMethodName;
	// 规则方法介绍
	private String methodComment;
	// 是否需要切换数据源
	private String dynamicDataSource;
	// 是否需要做多数据源访问
	private String multipleDataSource;
	// 被关联的次数
	private Integer bindCount;
	// 参数配置模板
	private String argsTemp;
}
