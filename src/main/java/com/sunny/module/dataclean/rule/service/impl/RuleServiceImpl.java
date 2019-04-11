package com.sunny.module.dataclean.rule.service.impl;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.module.dataclean.rule.entity.Rule;
import com.sunny.module.dataclean.rule.mapper.RuleMapper;
import com.sunny.module.dataclean.rule.service.RuleService;
import org.springframework.stereotype.Service;

@Service("ruleService")
public class RuleServiceImpl extends CudServiceImpl<Rule, RuleMapper> implements RuleService {



}
