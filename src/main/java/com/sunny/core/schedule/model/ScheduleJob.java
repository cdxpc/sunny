package com.sunny.core.schedule.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleJob {
	
	private String jobId;
	private String jobName;
	private String jobGroup;
	private String cronExpression;
	private String jobStatus;
	private String description;
	private String beanClass; // 包名+类名
	private String springBeanName; // spring bean name
	private String methodName; // 执行的方法名
	private String jobData;
	private String isConcurrent; // 是否有状态
	
}
