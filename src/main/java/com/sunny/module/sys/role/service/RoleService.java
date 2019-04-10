package com.sunny.module.sys.role.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sunny.core.base.service.BaseService;
import com.sunny.module.sys.relation.dto.RelationDto;
import com.sunny.module.sys.role.entity.Role;

public interface RoleService extends BaseService<Role>{

	List<Role> findRolesByUserId(String userId);

	Map<String, Object> getRoleInUser(RelationDto rd, Page<Role> page);

	Map<String, Object> getRoleNotInUser(RelationDto rd, Page<Role> page);

	boolean bindOrUnbind(String roleId, String userId, String type);

}
