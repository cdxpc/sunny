package com.sunny.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * sunny平台自定义 boot 配置
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Component
@ConfigurationProperties(prefix="sunny")
@Getter
@Setter
public class SunnyConfig {
	
	private Map<String, String> pros = new HashMap<>();

}
