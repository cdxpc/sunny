package com.sunny.module.sys.relation.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_ROLE_MENU")
public class RoleMenu {
	
	private String roleId;
	private String menuId;

}
