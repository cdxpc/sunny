package com.sunny.module.dataclean.task.domain;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto extends DtoEntity {
	
	private static final long serialVersionUID = 1L;

	private String taskId;
	// 任务名称
	private String taskName;
	// 任务组
	private String taskGroup;
	// 表达式
	private String cron;
	// 任务运行状态
	private String runStatus; //1 - 正常   2 - 异常     任务状态 status 1-未发布   2-运行中  3-已停止

	// 任务执行规则类
	private String ruleClassName;
	// 任务执行规则方法
	private String ruleMethodName;
	// 参数json
	private String paramJson;
	// 任务描述
	private String remarks;

}
