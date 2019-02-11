package com.sunny.module.sys.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.module.sys.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

}
