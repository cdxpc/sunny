package com.sunny.module.sys.relation.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_USER_ORG")
public class UserOrg {
	
	private String userId;
	private String orgId;

}
