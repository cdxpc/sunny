package com.sunny.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源文件boot配置
 * @author tlbank
 *
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	
	// 上传文件保存路径
	@Value("${sunny.upload.profile}")
    private String profile;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		/** 头像上传路径 */ // 加上这个反而访问不到，具体是什么原因待查
//		registry.addResourceHandler("/profile/**").addResourceLocations(profile);

		/** swagger配置 */
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
