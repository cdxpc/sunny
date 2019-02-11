package com.sunny.module.sys.menu.dto;

import java.util.List;

import com.sunny.core.persistence.DtoEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuDto extends DtoEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String menuId;
	private String menuName;
	private String url;
	private String icon;
	private String menuType;
	private String perms;
	private String visible;
	
	private String parentId;
	
	private MenuDto parent;
	
	private List<MenuDto> children;

}
