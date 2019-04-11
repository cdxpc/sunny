package com.sunny.module.common.job;
//package com.tlcb.mmps.module.dataclean.jobs;
//
//import java.util.Date;
//
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Service;
//
//import com.tlcb.mmps.utils.DateUtils;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@EnableScheduling
//public class DynamicScheduleJob implements SchedulingConfigurer {
//	
//	private String cron = "* 1 * * * ?"; // 从数据库获取
//	
//	private String dynamicCron() {
//		this.cron = "0/10 * * * * ?"; // 从数据库获取
//		return cron;
//	}
//
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//		scheduledTaskRegistrar.addTriggerTask(new Runnable() {
//			@Override
//			public void run() {
//				log.info("当前执行的表达式规则为：" + cron);
//				log.info("当前系统时间：" + DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
//			}
//		}, new Trigger() {
//			@Override
//			public Date nextExecutionTime(TriggerContext context) {
//				CronTrigger cronTrigger = new CronTrigger(dynamicCron());
//				return cronTrigger.nextExecutionTime(context);
//			}
//		});
//	}
//	
//}
