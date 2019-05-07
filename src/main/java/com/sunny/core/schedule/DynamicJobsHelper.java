package com.sunny.core.schedule;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.schedule.factory.DisallowConcurrentQuartzJobFactory;
import com.sunny.core.schedule.factory.QuartzJobFactory;
import com.sunny.core.schedule.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.lang.reflect.Method;

@Slf4j
public class DynamicJobsHelper {
	
	public static final String STATUS_RUNNING = "1";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addJob(ScheduleJob job, SchedulerFactoryBean schedulerFactory) {
		if(job == null || !STATUS_RUNNING.equals(job.getJobStatus())) {
			return ;
		}
		
		String jobName = job.getJobName();
		String jobGroup = job.getJobGroup();
		Scheduler scheduler = schedulerFactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 如果不存在，则创建一个
			if(trigger == null) {
				Class clazz = job.isConcurrent() ? QuartzJobFactory.class : DisallowConcurrentQuartzJobFactory.class;
				JobDetail jobDetail = JobBuilder.newJob(clazz)
						                        .withIdentity(jobName, jobGroup)
						                        .withDescription(job.getDescription())
						                        .build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				trigger = TriggerBuilder.newTrigger()
				                        .withIdentity(triggerKey)
										.usingJobData(job.getJobData())
				                        .withDescription(job.getDescription())
				                        .withSchedule(cronScheduleBuilder)
				                        .build();
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				// Trigger 已存在
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				
				trigger = trigger.getTriggerBuilder()
						         .withIdentity(triggerKey)
						         .usingJobData(job.getJobData())
						         .withDescription(job.getDescription())
			                     .withSchedule(cronScheduleBuilder)
			                     .build();
				
				// 按更新后的重置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
			if(!job.isConcurrent()) {  // true是允许并发，所以当为 !false 的时候才需要将同一个任务暂停
				scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 * @param schedulerFactory
	 */
	public static void stopJob(String jobName, String jobGroup, SchedulerFactoryBean schedulerFactory) {
		Scheduler scheduler = schedulerFactory.getScheduler();
		try {
			scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
			if(log.isDebugEnabled()) {
				log.debug("定时任务group[" + jobGroup + "]name[" + jobName + "]已暂停执行计划！" );
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 恢复定时任务
	 * @param jobName
	 * @param jobGroup
	 * @param schedulerFactory
	 */
	public static void startJob(String jobName, String jobGroup, SchedulerFactoryBean schedulerFactory) {
		Scheduler scheduler = schedulerFactory.getScheduler();
		try {
			scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
			if(log.isDebugEnabled()) {
				log.debug("定时任务group[" + jobGroup + "]name[" + jobName + "]已恢复执行计划！" );
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 移除定时任务
	 * @param jobName
	 * @param jobGroup
	 * @param schedulerFactory
	 */
	public static void deleteJob(String jobName, String jobGroup, SchedulerFactoryBean schedulerFactory) {
		Scheduler scheduler = schedulerFactory.getScheduler();
		try {
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
			if(log.isDebugEnabled()) {
				log.debug("定时任务group[" + jobGroup + "]name[" + jobName + "]已移除执行计划！" );
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * @param scheduleJob
	 */
	public static void invokeMethod(ScheduleJob scheduleJob) {
		Object obj = null;
		Class<?> clazz = null;
		Method method = null;
		if(StringUtils.isNotEmpty(scheduleJob.getSpringBeanName())) {
			obj = SpringContextHolder.getBean(scheduleJob.getSpringBeanName());
		} else if(StringUtils.isNotEmpty(scheduleJob.getBeanClass())) {
			try {
				obj = SpringContextHolder.getBean(scheduleJob.getBeanClass());
				if(obj == null) {
					clazz = Class.forName(scheduleJob.getBeanClass());
					obj = clazz.newInstance();
				}
			} catch (ClassNotFoundException e) {
				log.error("类没找到：" + e.getMessage());
			} catch (Exception e) {
				log.error("对象实例化异常：" + e.getMessage());
			}
		}
		
		if(obj != null) {
			clazz = obj.getClass();
			try {
				if(scheduleJob.getJobData() != null) {
					method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), JobDataMap.class);
				} else {
					method = clazz.getDeclaredMethod(scheduleJob.getJobName());
				}
			} catch (NoSuchMethodException e) {
				log.error("方法未找到：" + e.getMessage());
			} 
		}
		
		if(method != null) {
			try {
				if(scheduleJob.getJobData() != null) {
					method.invoke(obj, scheduleJob.getJobData());
				} else {
					method.invoke(obj);
				}
			} catch (Exception e) {
				log.error("方法执行异常：" + e.getMessage());
			} 
		}
	}
	
}
