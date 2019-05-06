package com.sunny.module.dataclean.task;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.listener.AbstractApplicationListener;
import com.sunny.module.dataclean.rule.mapper.RuleMapper;
import com.sunny.module.dataclean.task.mapper.TaskMapper;
import com.sunny.module.db.database.mapper.DataBaseMapper;
import com.sunny.module.db.table.mapper.DbTableMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 功能描述：定时任务辅助类
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 19:37
 * @since 1.0.0v
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class TaskHelper extends AbstractApplicationListener {

    private TaskMapper taskMapper;
    private DataBaseMapper dataBaseMapper;
    private RuleMapper ruleMapper;
    private DbTableMapper dbTableMapper;
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void initBeans() {
        this.taskMapper = SpringContextHolder.getBean(TaskMapper.class);
        this.schedulerFactoryBean = SpringContextHolder.getBean(SchedulerFactoryBean.class);
    }

    @Override
    public void doAction() {


    }
}
