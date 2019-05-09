package com.sunny.core.annotation;

import com.sunny.core.constant.PlatformConstants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述： 规则方法注解，标注在方法上，被标注的方法说明是个规则方法
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 22:13
 * @since 1.0.0v
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RuleMethod {

    boolean dynamicDataSource() default false; // 是否需要切换数据源

    boolean multipleDataSource() default false; // 是否需要访问多个数据源

    String comment() default "";

    String paramJosnTemp() default PlatformConstants.temp;

}
