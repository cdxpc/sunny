package com.sunny.module.sys.menu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.core.base.service.TreeServiceImpl;
import com.sunny.module.TreeHelper;
import com.sunny.module.sys.menu.entity.Menu;
import com.sunny.module.sys.menu.mapper.MenuMapper;
import com.sunny.module.sys.menu.service.MenuService;
import com.sunny.module.sys.relation.entity.RoleMenu;
import com.sunny.module.sys.relation.entity.UserRole;
import com.sunny.module.sys.relation.mapper.RoleMenuMapper;
import com.sunny.module.sys.relation.mapper.UserRoleMapper;

@Service("menuService")
public class MenuServiceImpl extends TreeServiceImpl<Menu, MenuMapper> implements MenuService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	@Override // 都是单表查询，不使用多表联查
	public List<Menu> findMenusByUserId(String userId) {
		List<Menu> menus = Collections.emptyList();
		UserRole ur = UserRole.of();
		ur.setUserId(userId);
		List<UserRole> urs = userRoleMapper.select(ur);
		if(CollectionUtils.isNotEmpty(urs)) {
			List<RoleMenu> rmAll = new ArrayList<>();
			for(UserRole ur_ : urs) {
				RoleMenu rm = RoleMenu.of();
				rm.setRoleId(ur_.getRoleId());
				// 通过roleId来获取对应的roleMenu
				List<RoleMenu> rms = roleMenuMapper.select(rm);
				if(CollectionUtils.isNotEmpty(rms)) {
					// 去重操作
					toRepeat(rmAll, rms);
				}
			}
			if(CollectionUtils.isNotEmpty(rmAll)) {
				String menuIds = "";
				for(RoleMenu rm : rmAll) {
					menuIds += "'" + rm.getMenuId() + "',";
				}
				menus = mapper.selectByIds(menuIds.substring(0, menuIds.length() - 1));
			}
		}
		return filter(menus);
	}
	
	// 去重
	private void toRepeat(List<RoleMenu> all, List<RoleMenu> part) {
		List<String> menuIds = new ArrayList<>();
		for (int i = 0; i < all.size(); i++) {
			menuIds.add(all.get(i).getMenuId());
		}
		for (int i = 0; i < part.size(); i++) {
			if(!menuIds.contains(part.get(i).getMenuId())) {
				all.add(part.get(i));
			}
		}
	}
	
	// 过滤，将状态为禁用、隐藏的数据过滤掉       通过代码过滤好还是数据库查询加过滤条件好？？？  XXX 需衡量
	private List<Menu> filter(List<Menu> menus) {
		List<Menu> newMenus = new ArrayList<>();
		if(menus != null && menus.size() > 0) {
			menus.stream().filter(m -> "1".equals(m.getStatus()) && "1".equals(m.getVisible())).forEach(m -> newMenus.add(m));
		}
		return newMenus;
	}

	@Override
	protected List<Map<String, Object>> toTree(List<Menu> all) {
		return TreeHelper.toMenuTree(all, false, null);
	}



}
