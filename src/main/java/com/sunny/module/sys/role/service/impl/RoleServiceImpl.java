package com.sunny.module.sys.role.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.module.common.CudServiceImpl;
import com.sunny.module.sys.relation.entity.UserRole;
import com.sunny.module.sys.relation.mapper.UserRoleMapper;
import com.sunny.module.sys.role.dto.RoleDto;
import com.sunny.module.sys.role.entity.Role;
import com.sunny.module.sys.role.mapper.RoleMapper;
import com.sunny.module.sys.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends CudServiceImpl<Role, RoleDto, RoleMapper> implements RoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public List<Role> findRolesByUserId(String userId) {
		List<Role> roles = Collections.emptyList();
		UserRole ur = UserRole.of();
		ur.setUserId(userId);
		// 通过userId查询用户角色关系中间表，来获取该用户拥有的角色
		List<UserRole> urs = userRoleMapper.select(ur);
		if(CollectionUtils.isNotEmpty(urs)) {
			String roleIds = "";
			for (UserRole userRole : urs) {
				roleIds += "'" + userRole.getRoleId() + "',";
			}
			// 通过roleIds来获取对应的roles
			roles = mapper.selectByIds(roleIds.substring(0, roleIds.length() - 1));
		}
		return roles;
	}

}
