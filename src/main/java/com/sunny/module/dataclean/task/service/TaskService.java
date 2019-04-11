package com.sunny.module.dataclean.task.service;

import com.github.pagehelper.Page;
import com.sunny.core.ResponseJson;
import com.sunny.core.base.service.BaseService;
import com.sunny.module.dataclean.task.domain.TaskDto;
import com.sunny.module.dataclean.task.entity.Task;

public interface TaskService extends BaseService<Task>{

	ResponseJson findList2(TaskDto dto,  Page<Task> page);

}
