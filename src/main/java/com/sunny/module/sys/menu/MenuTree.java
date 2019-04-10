package com.sunny.module.sys.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sunny.module.sys.menu.dto.MenuDto;

/**
 * 权限数据处理
 */
public class MenuTree {

	/**
	 * 根据父节点的ID获取所有子节点
	 * 
	 * @param list
	 *            菜单list
	 * @param parentId
	 *            传入的父节点ID
	 * @return List
	 */
	public static List<MenuDto> getChildPerms(List<MenuDto> list, String parentId) {
		List<MenuDto> returnList = new ArrayList<>();
		for (Iterator<MenuDto> iterator = list.iterator(); iterator.hasNext();) {
			MenuDto t = iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId().equals(parentId)) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 * 
	 * @param list
	 * 		 	 菜单list
	 * @param dto
	 * 			 菜单dto
	 */
	private static void recursionFn(List<MenuDto> list, MenuDto dto) {
		// 得到子节点列表
		List<MenuDto> childList = getChildList(list, dto);
		dto.setChildren(childList);
		for (MenuDto tChild : childList) {
			if (hasChild(list, tChild)) {
				// 判断是否有子节点
				Iterator<MenuDto> it = childList.iterator();
				while (it.hasNext()) {
					MenuDto n = it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private static List<MenuDto> getChildList(List<MenuDto> list, MenuDto t) {

		List<MenuDto> tlist = new ArrayList<>();
		Iterator<MenuDto> it = list.iterator();
		while (it.hasNext()) {
			MenuDto n = it.next();
			if (n.getParentId().equals(t.getMenuId())) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private static boolean hasChild(List<MenuDto> list, MenuDto t) {
		return getChildList(list, t).size() > 0;
	}
}
