package com.sunny.module.common.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.sunny.core.SpringContextHolder;

@Component
public class DynamicJobs {
	
	@PostConstruct
	public void init() {
		SchedulerFactoryBean schedulerFactoryBean = SpringContextHolder.getBean(SchedulerFactoryBean.class);
		// 1、
		
		// 2、
		
		// 3、
		
		// ...
	}
	
}
