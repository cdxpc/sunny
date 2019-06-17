package com.sunny.module.dataclean.task.service.impl;

import com.github.pagehelper.Page;
import com.sunny.core.ResponseJson;
import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.module.dataclean.task.domain.TaskDto;
import com.sunny.module.dataclean.task.entity.Task;
import com.sunny.module.dataclean.task.mapper.TaskMapper;
import com.sunny.module.dataclean.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskServiceImpl extends CudServiceImpl<Task, TaskMapper> implements TaskService {
	
	@Autowired
	private TaskMapper taskMapper;

	@Override
	public ResponseJson findList2(TaskDto dto, Page<Task> page) {
		ResponseJson json = ResponseJson.noData();
		// int total = taskMapper.findCount2(domain.getTaskName(), domain.getDbId(), domain.getRuleId());
		// if(total > 0) {
		// 	int start = (page.getPageNum() -1) * page.getPageSize();
		// 	int pageSize = page.getPageSize();
		// 	List<TaskDto> tasks = taskMapper.findList2(domain.getTaskName(), domain.getDbId(), domain.getRuleId(), start, pageSize);
		// 	json = ResponseJson.ok();
		// 	json.setPageNow(page.getPageNum());
		// 	json.setPageSize(pageSize);
		// 	json.setPages(total / pageSize + ((total % pageSize == 0) ? 0 : 1));
		// 	json.setTotal(total);
		// 	json.setRows(tasks);
		// }
		return json;
	}



}
