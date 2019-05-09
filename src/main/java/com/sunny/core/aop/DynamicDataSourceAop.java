// package com.sunny.core.aop;
//
// import lombok.extern.slf4j.Slf4j;
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.*;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * 功能描述：
//  *
//  * @author cdxpc <cdxpc2018@163.com>
//  * @date 2019/4/4
//  * @since 1.0.0v
//  */
// @Configuration
// @Aspect
// @Slf4j
// public class DynamicDataSourceAop {
//
//     private static final String ex = "execution(* com.sunny.module..*Mapper.*(..)) " +
//             "|| @annotation(org.apache.ibatis.annotations.InsertProvider) " +
//             "|| @annotation(org.apache.ibatis.annotations.DeleteProvider) " +
//             "|| @annotation(org.apache.ibatis.annotations.UpdateProvider) " +
//             "|| @annotation(org.apache.ibatis.annotations.SelectProvider)";
//
//     @Pointcut(ex)
//     public void point(){}
//
//     @Before(value = "point()")
//     public void before(JoinPoint joinPoint) throws Throwable {
//         // do something
//         log.error("before..." + joinPoint.getSignature().getName());
//     }
//
//     @After(value = "point()")
//     public void after(JoinPoint joinPoint) throws Throwable {
//         // do something
//         log.error("after..." + joinPoint.getSignature().getName());
//     }
//
// }
