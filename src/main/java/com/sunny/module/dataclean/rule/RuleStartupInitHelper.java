package com.sunny.module.dataclean.rule;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.annotation.RuleClass;
import com.sunny.core.annotation.RuleMethod;
import com.sunny.core.listener.AbstractApplicationListener;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.dataclean.rule.entity.Rule;
import com.sunny.module.dataclean.rule.mapper.RuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 功能描述： 程序启动时自动初始化规则记录辅助类
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
        // 需要插入的规则数据
        List<Rule> needInsert = new ArrayList<>();

        // 通过反射类获取定义好的任务规则类和方法
        Set<Class<?>> ruleClasses = ref.getTypesAnnotatedWith(RuleClass.class);
        if(ruleClasses != null && ruleClasses.size() > 0) {
            ruleClasses.forEach(rc -> {
                String className = rc.getCanonicalName();
                RuleClass rcAnn = rc.getAnnotation(RuleClass.class);
                // 类的描述介绍信息
                String classComment = rcAnn.comment();
                Method[] methods = rc.getDeclaredMethods();
                if(methods != null && methods.length > 0) {
                    Stream.of(methods).filter(m -> m.getAnnotation(RuleMethod.class) != null).forEach(m -> {
                        RuleMethod rmAnn = m.getAnnotation(RuleMethod.class);
                        String methodComment = rmAnn.comment();
                        String dynamicDataSource = rmAnn.dynamicDataSource() ? "1" : "2";
                        String multipleDataSource = rmAnn.multipleDataSource() ? "1" : "2";
                        String methodName = m.getName();
                        // 根据className和methodName 来定位一条规则（类名和方法名也不一定对，不同包下类名和方法都相同的话会有问题，但目前先不考虑）
                        Rule r = Rule.of();
                        r.setRuleClassName(className);
                        r.setRuleMethodName(methodName);
                        Rule rule = ruleMapper.selectOne(r);
                        if(rule == null) {
                            r.setRuleId(UuidUtils.uuid());
                            r.setBindCount(0);
                            r.setStatus("1");
                            setValue(r, classComment,methodComment ,dynamicDataSource ,multipleDataSource ,rmAnn.paramJosnTemp());
                            needInsert.add(r);
                        } else {
                            setValue(rule, classComment,methodComment ,dynamicDataSource ,multipleDataSource ,rmAnn.paramJosnTemp());
                            needInsert.add(rule);
                        }
                    });
                }
            });
        }

        // 删除全部的规则数据，重新插入需要的数据，这里的删除操必须放在这个位置，不能放在方法开始的地方，切记
        ruleMapper.delete(null);

        // 重新插入数据，老的有用数据的主键ID不会发生变化
        if(needInsert.size() > 0) {
            needInsert.forEach(r -> ruleMapper.insertSelective(r));
        }
    }

    private void setValue(Rule rule, String classComment, String methodComment, String dynamicDataSource, String multipleDataSource, String argsTemp) {
        rule.setClassComment(classComment);
        rule.setMethodComment(methodComment);
        rule.setDynamicDataSource(dynamicDataSource);
        rule.setMultipleDataSource(multipleDataSource);
        rule.setArgsTemp(argsTemp);
    }

    @Override
    public boolean startupDo() {
        return startupDo;
    }

    /**
     * 可能存在的问题描述如下：
     *      如果规则被任务使用了（即bindCount > 0）,
     *      而某次代码调整中改动了这个被绑定过的规则类中方法的方法名，
     *      则在项目启动的时候会将老的规则删除掉，
     *      并使用新的规则，
     *      而绑定的任务在执行的时候会报方法未找到的错误
     *  建议：
     *      规则类中的方法名如果定义好了之后就不要随便去变更，可以变更comment的内容，但不要改类名和方法名
     */


}
