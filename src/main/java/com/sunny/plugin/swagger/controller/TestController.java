package com.sunny.plugin.swagger.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@ApiResponses(value = {
		@ApiResponse(code=200, message="Success"),
		@ApiResponse(code=500, message="Server Error"),
})
@Controller
@RequestMapping("/test")
public class TestController {
	
	@ApiOperation(value = "list", notes = "获取列表")
	@GetMapping("list")
	public List<String> list() {
		return Arrays.asList("java1", "spark1", "hadoop1");
	}
	
	@ApiIgnore // 该方法忽略掉，不在api列表中显示
	@GetMapping("data")
	public List<String> data() {
		return Arrays.asList("java2", "spark2", "hadoop2");
	}

}
