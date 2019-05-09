package com.sunny.module.dataclean.rule.templelate;

import com.sunny.core.annotation.RuleClass;
import com.sunny.core.annotation.RuleMethod;
import com.sunny.module.dataclean.rule.RuleStartupInitHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Component;

/**
 * 功能描述： 默认数据源的规则定义在该类中进行定义
 *          {@link RuleStartupInitHelper}
 *          {@link RuleClass }
 *          {@link RuleMethod}
 *
 * @author keivn.chen <crsfyc-9@163.com>
 * @date 2019-05-07 19:32
 * @since 1.0.0v
 */
@Component
@RuleClass(comment = "本项目默认访问的数据源定时任务规则")
@Slf4j
public class DefaultDataSourceRule {

    @RuleMethod(comment = "默认执行方法", paramJosnTemp = "{\"sources\":[\"源数据源ID(操作默认数据源，可为空)\"]}")
    public void exe(JobDataMap params){
        log.error("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

}
