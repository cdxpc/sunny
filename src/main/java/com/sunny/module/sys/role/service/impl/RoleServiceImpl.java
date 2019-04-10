package com.sunny.module.sys.role.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.core.persistence.Changer;
import com.sunny.core.persistence.DbEntity;
import com.sunny.module.DataChangeHelper;
import com.sunny.module.sys.relation.dto.RelationDto;
import com.sunny.module.sys.relation.entity.UserRole;
import com.sunny.module.sys.relation.mapper.UserRoleMapper;
import com.sunny.module.sys.role.entity.Role;
import com.sunny.module.sys.role.mapper.RoleMapper;
import com.sunny.module.sys.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends CudServiceImpl<Role, RoleMapper> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
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

	@Override
	public boolean changeSort(Role role, Changer changer) {
		boolean flag = false;
		List<DbEntity> datas = DataChangeHelper.getInstance(roleMapper).changeSort(role, changer);
		if(datas != null && datas.size() > 0) {
			for (DbEntity e : datas) {
				flag = roleMapper.updateByPrimaryKeySelective((Role) e) > 0;
			}
		}
		return flag;
	}

	@Override
	public Map<String, Object> getRoleInUser(RelationDto rd, Page<Role> page) {
		Map<String, Object> result = new HashMap<>();
		List<Role> pageList = null;
		int rows = roleMapper.getRoleInUserCount(rd.getRoleCode(), rd.getRoleName(), rd.getUserId());
		if(rows > 0) {
			int start = (page.getPageNum() - 1) * page.getPageSize();
			int end = page.getPageSize();
			pageList = roleMapper.getRoleInUser(rd.getRoleCode(), rd.getRoleName(), rd.getUserId(), start, end);
		}
		result.put("total", rows);
		result.put("dataList", pageList);
		return result;
	}

	@Override
	public Map<String, Object> getRoleNotInUser(RelationDto rd, Page<Role> page) {
		Map<String, Object> result = new HashMap<>();
		List<Role> pageList = null;
		int rows = roleMapper.getRoleNotInUserCount(rd.getRoleCode(), rd.getRoleName(), rd.getUserId());
		if(rows > 0) {
			int start = (page.getPageNum() - 1) * page.getPageSize();
			int end = page.getPageSize();
			pageList = roleMapper.getRoleNotInUser(rd.getRoleCode(), rd.getRoleName(), rd.getUserId(), start, end);
		}
		result.put("total", rows);
		result.put("dataList", pageList);
		return result;
	}

	@Override
	public boolean bindOrUnbind(String roleId, String userId, String type) {
		UserRole ur = UserRole.of();
		ur.setUserId(userId);
		ur.setRoleId(roleId);
		if("bind".equals(type)) {
			return userRoleMapper.insert(ur) > 0;
		} else {
			return userRoleMapper.delete(ur) > 0;
		}
	}

}
