package com.sunny.module.sys.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.module.sys.role.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;

}
