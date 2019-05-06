package com.sunny.core.listener;

import com.sunny.core.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 功能描述： spring 容器初始化完成刷新后监听器
 *          {@link ApplicationListener}
 *          {@link ContextRefreshedEvent}
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 18:13
 * @since 1.0.0v
 */
@Slf4j
public abstract class AbstractApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            log.info("application context: " + SpringContextHolder.getApplicationContext());
            initBeans();
            if(startupDo()) {
                doAction();
            }
        }
    }

    public abstract void initBeans();

    public abstract void doAction();

    // 设置程序每次启动的时候是否都执行后面的操作
    public boolean startupDo() {
        return true;
    }

}
