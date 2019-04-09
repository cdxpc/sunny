package com.sunny.core;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * spring 容器操作辅助类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Service
@Lazy(false)
@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
	
	private static ApplicationContext context = null;
	
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) context.getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return (T) context.getBean(requiredType);
	}

	@Override
	public void destroy() throws Exception {
		clearHolder();
	}

	private static void clearHolder() {
		if (log.isDebugEnabled()) {
			log.debug("清除SpringContextHolder中的ApplicationContext:" + context);
		}
		context = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		log.debug("注入ApplicationContext到SpringContextHolder:{}", context);
		if (context != null) {
			log.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + context);
		}
		SpringContextHolder.context = context;
	}
	
	private static void assertContextInjected() {
		Validate.validState(context != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.", new Object[0]);
	}

}
