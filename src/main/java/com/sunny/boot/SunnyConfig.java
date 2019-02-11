package com.sunny.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="sunny")
@Getter
@Setter
public class SunnyConfig {
	
	private Map<String, String> pros = new HashMap<>();

}
