package com.sunny.module.dataclean.task.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.dataclean.DataCleanRestApiConstants;
import com.sunny.module.dataclean.rule.service.RuleService;
import com.sunny.module.dataclean.task.domain.TaskDto;
import com.sunny.module.dataclean.task.entity.Task;
import com.sunny.module.dataclean.task.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(DataCleanRestApiConstants.REST_API_TASK)
public class TaskController extends AbstractInternalController<Task, TaskDto> {

	private TaskService taskService = SpringContextHolder.getBean(TaskService.class);
	private RuleService ruleService = SpringContextHolder.getBean(RuleService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);
	
	@Override
	protected ControllerHelper<Task, TaskDto> helper() {
		return ControllerHelper.all(taskService, Task.of(), new TaskDto(), DataCleanRestApiConstants.REST_API_TASK_LIST);
	}

	public String list() {
		return toView();
	}

	@PostMapping(RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(TaskDto dto) { 	// 数据列表
		return findListWithPage(dto);
		// if(domain == null) {
		// 	domain = helper().getDto();
		// }
		// return taskService.findList2(domain, super.initPage());
	}

	@GetMapping(RestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("task", helper().getDto());
		dict(model);
		return DataCleanRestApiConstants.REST_API_TASK_FORM;
	}

	@GetMapping(RestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("task", json.getRows());
		}
		dict(model);
		return DataCleanRestApiConstants.REST_API_TASK_FORM;
	}

	private void dict(Model model) {
		model.addAttribute("ruleClasses", ruleService.findAllGroup());
//		model.addAttribute("ruleTypes", dictHelper.getValuesByType("dict_clean_rule_type"));
//		model.addAttribute("ruleStrategyTypes", dictHelper.getValuesByType("dict_clean_rule_strategy_type"));
//		model.addAttribute("isDefaults", dictHelper.getValuesByType("dict_is_default"));
	}

	@PostMapping(RestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(TaskDto dto) {
		if(StringUtils.isEmpty(dto.getTaskId())) {
			dto.setTaskId(UuidUtils.uuid());
			return create(dto);
		}
		return update(dto.getTaskId(), dto);
	}

	@PostMapping(RestApiConstants.REMOVE)
	@ResponseBody
	public ResponseJson remove(String id) {
		return deleteById(id);
	}

}
