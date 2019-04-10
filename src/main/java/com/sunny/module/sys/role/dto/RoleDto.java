package com.sunny.module.sys.role.dto;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto extends DtoEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	private String roleCode;
	private String roleName;
	private String remark;

}
