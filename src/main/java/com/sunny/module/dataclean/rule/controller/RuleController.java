package com.sunny.module.dataclean.rule.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.dataclean.DataCleanRestApiConstants;
import com.sunny.module.dataclean.rule.dto.RuleDto;
import com.sunny.module.dataclean.rule.entity.Rule;
import com.sunny.module.dataclean.rule.service.RuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(DataCleanRestApiConstants.REST_API_RULE)
public class RuleController extends AbstractInternalController<Rule, RuleDto> {

	private RuleService ruleService = SpringContextHolder.getBean(RuleService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);

	@Override
	protected ControllerHelper<Rule, RuleDto> helper() {
		return ControllerHelper.all(ruleService, Rule.of(), new RuleDto(), DataCleanRestApiConstants.REST_API_RULE_LIST);
	}

	public String list() {
		return toView();
	}

	@PostMapping(RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(RuleDto dto) { 	// 数据列表
		return findListWithPage(dto);
	}

	@GetMapping(RestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("rule", helper().getDto());
		dict(model);
		return DataCleanRestApiConstants.REST_API_RULE_FORM;
	}

	@GetMapping(RestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("rule", json.getRows());
		}
		dict(model);
		return DataCleanRestApiConstants.REST_API_RULE_FORM;
	}

	private void dict(Model model) {
		model.addAttribute("ruleTypes", dictHelper.getValuesByType("dict_clean_rule_type"));
		model.addAttribute("ruleStrategyTypes", dictHelper.getValuesByType("dict_clean_rule_strategy_type"));
		model.addAttribute("isDefaults", dictHelper.getValuesByType("dict_is_default"));
	}

	@PostMapping(RestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(RuleDto dto) {
		if(StringUtils.isEmpty(dto.getRuleId())) {
			dto.setRuleId(UuidUtils.uuid());
			return create(dto);
		}
		return update(dto.getRuleId(), dto);
	}

	@PostMapping(RestApiConstants.REMOVE)
	@ResponseBody
	public ResponseJson remove(String id) {
		return deleteById(id);
	}

	@PostMapping("ruleMethods")
	@ResponseBody
	public ResponseJson ruleMethods(String ruleClassName) {
		Rule r = Rule.of();
		r.setRuleClassName(ruleClassName);
		List<Rule> rules = ruleService.find(r);
		ResponseJson json = ResponseJson.ok();
		json.setRows(rules);
		return json;
	}

	@PostMapping("ruleTemp")
	@ResponseBody
	public ResponseJson ruleMethods(String ruleClassName, String ruleMethodName) {
		Rule r = Rule.of();
		r.setRuleClassName(ruleClassName);
		r.setRuleMethodName(ruleMethodName);
		Rule rule = ruleService.findOne(r);
		ResponseJson json = ResponseJson.ok();
		json.setRows(rule);
		return json;
	}
	
}
