package com.sunny.module.sys.area.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sunny.module.sys.area.service.AreaService;

@Controller
public class AreaController {
	
	@Autowired
	private AreaService areaService;

}
