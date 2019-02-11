package com.sunny.module.sys.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.module.sys.menu.service.MenuService;

@RestController
public class MenuController {
	
	@Autowired
	private MenuService menuService;

}
