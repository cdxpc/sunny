package com.sunny.module.sys.menu.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

}
