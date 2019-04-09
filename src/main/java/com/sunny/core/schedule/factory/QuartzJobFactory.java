package com.sunny.core.schedule.factory;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunny.core.schedule.DynamicJobsHelper;
import com.sunny.core.schedule.model.ScheduleJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		log.error("QuartzJobFactory execute begin ...");
		ScheduleJob scheduleJob = (ScheduleJob) jobContext.getMergedJobDataMap().get("scheduleJob");
		DynamicJobsHelper.invokeMethod(scheduleJob);
		log.error("QuartzJobFactory execute end ...");
	}

}
