package com.sunny.module.sys.role.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.sys.role.dto.RoleDto;
import com.sunny.module.sys.role.entity.Role;

import tk.mybatis.mapper.common.IdsMapper;

public interface RoleMapper extends BaseMapper<Role, RoleDto>, IdsMapper<Role> {

}
