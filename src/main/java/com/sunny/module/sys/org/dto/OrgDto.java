package com.sunny.module.sys.org.dto;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrgDto extends DtoEntity {

	private static final long serialVersionUID = 1L;
	
	private String orgId;		// 机构主键id
	private String orgCode;		// 机构号
	private String orgName;		// 机构名称
	private String remarks;		// 机构说明
	private String orgMainCategroy; 	// 机构大类：  1集团，2事业群，3分公司、4部门、 5项目组、6团队...
	private String orgSubCategroy; 		// 机构细类：  xx部门、xx项目组、xx团队、 ...

	private String parentId;	// 上级机构id
	private String parentName;  // 上级机构名称
	private String treeNode;	// 树节点   1-树枝，  2-树叶     => 树枝不展示删除按钮，树叶展示删除按钮

}
