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
	// 规则名称
	private String ruleName;
	// 规则表达式
	private String cron;
	// 规则策略类型
	private String ruleStrategyType;  // 按表达式定期执行  1、 删除失效数据    2、先迁移到指定数据库表，再删除
	// 规则类型
	private String ruleType; // 1、按天（指定天数）   2、 按月（每月的15号，删除本月之前的数据）
	// 失效时间天数   （按创建时间进行计算） eg: 30天   只保留30天内（含30天）的数据         10天   只保留10天内（含10天）的数据
	private String days;
	// 是否默认规则
	private String isDefault;
	// 描述
	private String remarks;
	// 被关联的次数
	private Integer bindCount;
}