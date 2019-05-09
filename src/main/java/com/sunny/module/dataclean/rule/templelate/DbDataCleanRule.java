package com.sunny.module.dataclean.rule.templelate;

import com.sunny.core.annotation.RuleClass;
import com.sunny.core.annotation.RuleMethod;
import com.sunny.core.util.json.JacksonUtils;
import com.sunny.module.dataclean.rule.RuleStartupInitHelper;
import com.sunny.module.dataclean.task.domain.TaskParams;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Component;

/**
 * 功能描述： 数据库数据清理规则定义类，该类会在程序启动是通过反射来构建一些规则数据
 *          {@link RuleStartupInitHelper}
 *          {@link RuleClass }
 *          {@link RuleClass }
 *
 * @author keivn.chen <crsfyc-9@163.com>
 * @date 2019-05-07 16:02
 * @since 1.0.0v
 */
@Component
@RuleClass(comment = "数据库数据清理规则")
@Slf4j
public class DbDataCleanRule {

    @RuleMethod(comment = "删除某张表的全部数据", dynamicDataSource = true)
    public void deleteAll(JobDataMap params) {
        String json = params.getString("json");
        // 这里面有任务执行是所需的全部参数信息
        TaskParams tp = JacksonUtils.getInstance(true).toBean(json, TaskParams.class);
        log.error("deleteAll - 删除某张表的全部数据...");
    }

    @RuleMethod(comment = "按指定条件删除某张表的数据", dynamicDataSource = true)
    public void onlyDeleteByCondition(JobDataMap params) {
        String json = params.getString("json");
        // 这里面有任务执行是所需的全部参数信息
        TaskParams tp = JacksonUtils.getInstance(true).toBean(json, TaskParams.class);
        log.error("onlyDeleteByCondition - 按指定条件删除某张表的数据..." + json);
    }

    @RuleMethod(comment = "按指定条件先备份，再删除某张表的数据", dynamicDataSource = true, multipleDataSource = true)
    public void backupAfterDeleteByCondition(JobDataMap params) {
        String json = params.getString("json");
        // 这里面有任务执行是所需的全部参数信息
        TaskParams tp = JacksonUtils.getInstance(true).toBean(json, TaskParams.class);
        log.error("backupAfterDeleteByCondition - 按指定条件先备份，再删除某张表的数据..." + json);
    }

}
