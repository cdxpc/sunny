package com.sunny.core.schedule.factory;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunny.core.schedule.DynamicJobsHelper;
import com.sunny.core.schedule.model.ScheduleJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class DisallowConcurrentQuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		log.error("DisallowConcurrentQuartzJobFactory execute begin ...");
		ScheduleJob scheduleJob = (ScheduleJob) jobContext.getMergedJobDataMap().get("scheduleJob");
		DynamicJobsHelper.invokeMethod(scheduleJob);
		log.error("DisallowConcurrentQuartzJobFactory execute end ...");
	}

}
