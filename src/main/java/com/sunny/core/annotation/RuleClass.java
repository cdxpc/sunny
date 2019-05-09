package com.sunny.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述： 规则类注解，标注在类上，被标注的类说明是个规则类
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 22:12
 * @since 1.0.0v
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RuleClass {

    String comment() default "";
}
