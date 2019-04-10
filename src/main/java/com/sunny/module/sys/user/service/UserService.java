package com.sunny.module.sys.user.service;

import com.sunny.core.ResponseJson;
import com.sunny.core.base.service.BaseService;
import com.sunny.module.sys.user.entity.User;

public interface UserService extends BaseService<User> {

	User findByLoginName(String loginName);

	ResponseJson restPwd(String userId);

}
