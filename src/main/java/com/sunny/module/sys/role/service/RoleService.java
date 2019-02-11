package com.sunny.module.sys.role.service;

import java.util.List;

import com.sunny.core.base.BaseService;
import com.sunny.module.sys.role.dto.RoleDto;
import com.sunny.module.sys.role.entity.Role;

public interface RoleService extends BaseService<Role, RoleDto>{

	List<Role> findRolesByUserId(String userId);

}
