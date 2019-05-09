package com.sunny.core.schedule.model;

import lombok.Getter;
import lombok.Setter;
import org.quartz.JobDataMap;

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
	private String methodName; // 执行的方法名
	private JobDataMap jobData;
	private boolean concurrent = false; // 是否允许并发执行同一个任务   true: 允许  false: 不允许
	
}
