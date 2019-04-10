package com.sunny.module.sys.menu.dto;

import java.util.List;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto extends DtoEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String menuId;
	private String menuName;
	private String url;
	private String icon;
	private String menuType;
	private String perms;
	private String visible;

	private String parentId;	// 上级机构id
	private String parentName;
	private String treeNode;	// 树节点   1-树枝，  2-树叶     => 树枝不展示删除按钮，树叶展示删除按钮
	
	//private MenuDto parent;
	private List<MenuDto> children;

}
