//package com.sunny.plugin.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2 // XXX 该注解至关重要
//public class SwaggerConfig {
//	
//	@Bean
//	public Docket createApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				   .apiInfo(apiInfo())
//				   .select()
//				   .apis(RequestHandlerSelectors.basePackage("com.sunny"))
//				   .paths(PathSelectors.any())
//				   .build();
//	}
//	
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Spring boot 中使用Swagger2来构建 Restful Api")
//				.description("更多Spring boot 相关学习文章，请关注作者博客：http://wangsaisai.cn")
//				.termsOfServiceUrl("http://wangsaisai.cn")
//				.contact(new Contact("cdxpc", "http://wangsaisai.cn", "cdxpc2018@163.com"))
//				.version("1.0.0v")
//				.build();
//	}
//
//}
