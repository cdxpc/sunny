package com.sunny.module.dataclean.task;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.listener.AbstractApplicationListener;
import com.sunny.core.schedule.DynamicJobsHelper;
import com.sunny.core.schedule.model.ScheduleJob;
import com.sunny.module.dataclean.task.entity.Task;
import com.sunny.module.dataclean.task.mapper.TaskMapper;
import org.quartz.JobDataMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述：定时任务辅助类
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 19:37
 * @since 1.0.0v
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class TaskStartupInitHelper extends AbstractApplicationListener {

    private TaskMapper taskMapper;
    // private DataBaseMapper dataBaseMapper;
    // private RuleMapper ruleMapper;
    // private DbTableMapper dbTableMapper;
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void initBeans() {
        this.taskMapper = SpringContextHolder.getBean(TaskMapper.class);
        this.schedulerFactoryBean = SpringContextHolder.getBean(SchedulerFactoryBean.class);
    }

    @Override
    public void doAction() {
        List<Task> tasks = getTodoTasks();
        if(tasks != null && tasks.size() > 0) {
            tasks.forEach(task -> {
                ScheduleJob job = new ScheduleJob();
                job.setJobId(task.getTaskId());
                job.setJobName(task.getTaskName());
                job.setJobGroup(task.getTaskGroup());
                job.setJobStatus(task.getStatus());
                job.setConcurrent(true);
                job.setCronExpression(task.getCron());
                job.setBeanClass(task.getRuleClassName());
                job.setMethodName(task.getRuleMethodName());
                job.setJobData(createJobDataMap(task));
                DynamicJobsHelper.addJob(job, schedulerFactoryBean);
            });
        }
    }

    private List<Task> getTodoTasks() {
        Task t = Task.of();
        t.setStatus("1");
        t.setRunStatus("1");
        return taskMapper.select(t);
    }

    private JobDataMap createJobDataMap(Task t) {
        JobDataMap map = new JobDataMap();
        // 通过ruleClassName 和ruleMethodName 来定位一条规则
        map.put("json", t.getParamJson());
        return map;
    }

    @Override
    public boolean startupDo() {
        return false;
    }
}
