package com.sunny.module.dataclean.rule;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.listener.AbstractApplicationListener;
import com.sunny.module.dataclean.rule.mapper.RuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 19:54
 * @since 1.0.0v
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 10)
@Slf4j
public class RuleStartupInitHelper extends AbstractApplicationListener {

    @Value("${sunny.rule.startup.do}")
    private boolean startupDo;

    private RuleMapper ruleMapper;
    private Reflections ref;

    @Override
    public void initBeans() {
        this.ruleMapper = SpringContextHolder.getBean(RuleMapper.class);
        this.ref = SpringContextHolder.getBean(Reflections.class);
    }

    @Override
    public void doAction() {

    }

    @Override
    public boolean startupDo() {
        return startupDo;
    }
}
