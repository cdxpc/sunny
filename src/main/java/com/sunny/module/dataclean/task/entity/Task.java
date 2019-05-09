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
	 * 	 新建任务：    任务状态status 1   未发布
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

	// 任务执行规则类
	private String ruleClassName;
	// 任务执行规则方法
	private String ruleMethodName;
	// 参数json串
	private String paramJson;
	// 任务描述
	private String remarks;

	/**
	 paramJson
	 {
		 "sources": [
			 "aaaaa",
			 "bbbbb"
		 ],
		 "tableAndColumn": {
			 "tableName": "t_text_001",
			 "andOr": "and",
			 "whereColumns": [
			 	{
				 	"columnName": "age",
				 	"columnValue": "23",
				 	"connector": ">="
				 },
				 {
				 	"columnName": "sex",
				 	"columnValue": "man",
				 	"connector": "="
			 	}
		 	]
		 },
		 "pages": 100
	 }
	 */
}
