package com.sunny.module.sys.user.service;

import com.sunny.core.base.BaseService;
import com.sunny.module.sys.user.dto.UserDto;
import com.sunny.module.sys.user.entity.User;

public interface UserService extends BaseService<User, UserDto> {

	User findByLoginName(String loginName);

}
