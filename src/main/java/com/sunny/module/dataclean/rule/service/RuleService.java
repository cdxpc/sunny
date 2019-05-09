package com.sunny.module.dataclean.rule.service;

import com.sunny.core.base.service.BaseService;
import com.sunny.module.dataclean.rule.entity.Rule;

import java.util.List;

public interface RuleService extends BaseService<Rule> {

    List<Rule> findAllGroup();
}
