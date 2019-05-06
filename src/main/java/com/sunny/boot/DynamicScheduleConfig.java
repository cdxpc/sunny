package com.sunny.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 功能描述：动态数据源配置类
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 17:43
 * @since 1.0.0v
 */
@Configuration
public class DynamicScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean();
    }

}
