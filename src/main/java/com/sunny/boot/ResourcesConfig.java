package com.sunny.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源文件 boot 配置
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	
	// 上传文件保存路径
	@Value("${sunny.upload.profile}")
    private String profile;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		/* 头像上传路径 */ // 加上这个反而访问不到，具体是什么原因待查
//		registry.addResourceHandler("/profile/**").addResourceLocations(profile);
		
	}

}
