package com.sunny.module.sys.role.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
@Table(name = "FT_SYS_ROLE")
public class Role extends DbEntity {
	
	@Id
	private String roleId;
	private String roleCode;
	private String roleName;
	private String remark;

}
