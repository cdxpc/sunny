package com.sunny.boot;

import com.sunny.SpringBootStarter;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：放射辅助工具配置类
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/6 17:45
 * @since 1.0.0v
 */
@Configuration
public class ReflectionsConfig {

    @Value("${sunny.job.package}")
    private String scanPackage;

    @Bean
    public Reflections reflections() {
        String defPk = SpringBootStarter.class.getPackage().getName();
        String[] spk = {};
        if(scanPackage != null && !"".equals(scanPackage)) {
            spk = scanPackage.split(",;| "); // 分隔符：','、';'、'|'、' '（空格）
        }
        return new Reflections(ConfigurationBuilder.build(defPk, spk));
    }

}
