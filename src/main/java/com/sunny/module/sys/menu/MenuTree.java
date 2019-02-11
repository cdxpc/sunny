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
	 *            分类表
	 * @param typeId
	 *            传入的父节点ID
	 * @return String
	 */
	public static List<MenuDto> getChildPerms(List<MenuDto> list, String parentId) {
		List<MenuDto> returnList = new ArrayList<MenuDto>();
		for (Iterator<MenuDto> iterator = list.iterator(); iterator.hasNext();) {
			MenuDto t = (MenuDto) iterator.next();
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
	 * @param MenuDto
	 */
	private static void recursionFn(List<MenuDto> list, MenuDto t) {
		// 得到子节点列表
		List<MenuDto> childList = getChildList(list, t);
		t.setChildren(childList);
		for (MenuDto tChild : childList) {
			if (hasChild(list, tChild)) {
				// 判断是否有子节点
				Iterator<MenuDto> it = childList.iterator();
				while (it.hasNext()) {
					MenuDto n = (MenuDto) it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private static List<MenuDto> getChildList(List<MenuDto> list, MenuDto t) {

		List<MenuDto> tlist = new ArrayList<MenuDto>();
		Iterator<MenuDto> it = list.iterator();
		while (it.hasNext()) {
			MenuDto n = (MenuDto) it.next();
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
		return getChildList(list, t).size() > 0 ? true : false;
	}
}
