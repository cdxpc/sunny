package com.sunny.module.dataclean.task.entity;

import com.sunny.core.persistence.DbEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "T_DATA_CLEAN_TASK")
@NoArgsConstructor(staticName = "of")
public class Task extends DbEntity {
	
	/**
	 * 任务状态变化：
	 * 	   新建任务：    任务状态status 1   未发布      
	 *   启动任务：    任务状态status 2   运行中     
	 *   停止任务：    任务状态status 3   已停止      
	 *   移除任务：   
	 */

    // 任务ID
	@Id
    private String taskId;
	// 任务名称
	private String taskName;
	// 任务组
	private String taskGroup;
	// 表达式
	private String cron;
	// 任务运行状态
	private String runStatus; //1 - 正常   2 - 异常     任务状态 status 1-未发布   2-运行中  3-已停止
	
    // 数据库ID
    private String dbId;
    // 数据库表
    private String tableName;
	// 任务执行规则
	private String ruleId;
}
