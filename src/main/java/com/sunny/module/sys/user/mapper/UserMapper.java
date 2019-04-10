package com.sunny.module.sys.user.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.sys.user.entity.User;
import tk.mybatis.mapper.common.IdsMapper;

public interface UserMapper extends BaseMapper<User>, IdsMapper<User> {

}
