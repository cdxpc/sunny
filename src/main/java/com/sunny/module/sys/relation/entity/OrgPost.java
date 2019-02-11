package com.sunny.module.sys.relation.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_ORG_POST")
public class OrgPost {
	
	private String orgId;
	private String postId;

}
