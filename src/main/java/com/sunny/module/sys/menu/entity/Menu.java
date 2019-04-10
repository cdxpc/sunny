package com.sunny.module.sys.menu.entity;

import com.sunny.core.persistence.DbEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
@Table(name = "FT_SYS_MENU")
public class Menu extends DbEntity {
	
	@Id
	private String menuId;
	private String menuName;
	private String url;
	private String icon;
	private String menuType;
	private String perms;
	private String visible;

	private String parentId;
	private String treeNode;	// 树节点   1-树枝，  2-树叶     => 树枝不展示删除按钮，树叶展示删除按钮

}
