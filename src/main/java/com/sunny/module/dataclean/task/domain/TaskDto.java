package com.sunny.module.dataclean.task.domain;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto extends DtoEntity {
	
	private static final long serialVersionUID = 1L;

	// 任务ID
	private String taskId;
	// 任务名称
	private String taskName;
	// 任务组
	private String taskGroup;
	// 任务运行状态
	private String runStatus;
	// 执行参数
	private String params;
	// 执行方法
	private String taskMethod;
	
	// 数据库ID
	private String dbId;
	// 数据库名
	private String dbName;
	// 数据库表
	private String tableName;
	// 任务执行规则
	private String ruleId;
	// 任务执行规则
	private String ruleName;

}
