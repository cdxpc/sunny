package com.sunny.module.dataclean.rule.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.dataclean.rule.entity.Rule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RuleMapper extends BaseMapper<Rule>{

    @Select("SELECT t.rule_class_name ruleClassName, t.rule_method_name ruleMethodName, t.dynamic_data_source dynamicDataSource, t.multiple_data_source multipleDataSource FROM t_data_clean_rule t GROUP BY t.rule_class_name")
    List<Rule> findAllGroup();

}
