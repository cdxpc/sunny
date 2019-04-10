package com.sunny.plugin.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("rest")
@ApiIgnore
public class SwaggerController {
	
	@GetMapping("apis")
	public String toRestApis() {
		return "redirect:/swagger-ui.html";
	}

}
