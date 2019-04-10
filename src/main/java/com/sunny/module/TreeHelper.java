package com.sunny.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunny.core.constant.PlatformConstants;
import com.sunny.module.sys.area.entity.Area;
import com.sunny.module.sys.menu.entity.Menu;
import com.sunny.module.sys.org.entity.Org;

/**
 * 树操作辅助类
 * 
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 *		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月18日
 * @since 1.0.0v
 */
public class TreeHelper {
	
	// 菜单树
	public static List<Map<String, Object>> toMenuTree(List<Menu> all, boolean isCheck, List<String> selecteds) {
		final List<Map<String, Object>> tree = new ArrayList<>();
		all.stream().filter(e -> PlatformConstants.STATUS_YES.equals(e.getStatus())).forEach(e -> {
			Map<String, Object> node = createNode(isCheck, e.getMenuId(), e.getParentId(), e.getMenuName(), selecteds);
			tree.add(node);
		});
		return tree;
	}
	
	// 机构树
	public static List<Map<String, Object>> toOrgTree(List<Org> all, boolean isCheck, List<String> selecteds) {
		final List<Map<String, Object>> tree = new ArrayList<>();
		all.stream().filter(e -> PlatformConstants.STATUS_YES.equals(e.getStatus())).forEach(e -> {
			Map<String, Object> node = createNode(isCheck, e.getOrgId(), e.getParentId(), e.getOrgName(), selecteds);
			tree.add(node);
		});
		return tree;
	}

	// 区域树
	public static List<Map<String, Object>> toAreaTree(List<Area> all, boolean isCheck, List<String> selecteds) {
		final List<Map<String, Object>> tree = new ArrayList<>();
		all.stream().filter(e -> PlatformConstants.STATUS_YES.equals(e.getStatus())).forEach(e -> {
			Map<String, Object> node = createNode(isCheck, e.getAreaId(), e.getParentId(), e.getAreaName(), selecteds);
			tree.add(node);
		});
		return tree;
	}
	
	private static Map<String, Object> createNode(boolean isCheck, String id, String pid, String name, List<String> selecteds) {
		Map<String, Object> node = new HashMap<>();
		node.put("id", id);
		node.put("pId", pid);
		node.put("name", name);
		node.put("title", name);
		if(isCheck) {
			node.put("checked", selecteds.contains(id + name));
		} else {
			node.put("checked", false);
		}
		return node;
	}

}
