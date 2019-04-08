package com.sunny.boot;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * mapper自动映射框架 boot 配置
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Configuration
@MapperScan(basePackages = {"com.sunny.*.**.mapper"}, value = "tk.mybatis.mapper.annotation", mapperHelperRef = "mapperHelper")
public class MyBatisConfig {
	
	@SuppressWarnings("rawtypes")
	@Bean(name = "mapperHelper") // 默认name的值就是方法名，所以如果定义和方法名一致，可以省去
	public MapperHelper mapperHelper() {
		Config config = new Config();
		List<Class> mappers = new ArrayList<>();
		mappers.add(Mapper.class);
		config.setMappers(mappers);
		
		MapperHelper mapperHelper = new MapperHelper();
		mapperHelper.setConfig(config);
		return mapperHelper;
	}

}
