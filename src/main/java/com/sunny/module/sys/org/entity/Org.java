package com.sunny.module.sys.org.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_ORG")
public class Org extends DbEntity {
	
	@Id
	private String orgId;		// 机构主键id
	private String orgCode;		// 机构号
	private String parentId;	// 上级机构id
	private String orgName;		// 机构名称
	private String remarks;		// 机构说明
	
	private String orgMainCategroy; 	// 机构大类：  集团，事业群，分公司、部门、 ...
	private String orgSubCategroy; 		// 机构细类：  xx部门、xx项目组、xx团队、 ...

}
