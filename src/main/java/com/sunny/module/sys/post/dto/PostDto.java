package com.sunny.module.sys.post.dto;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto extends DtoEntity {

	private static final long serialVersionUID = 1L;
	
	private String postId;		// 岗位主键id
	private String postCode;	// 岗位号
	private String postName;	// 岗位名称
	private String remarks;		// 岗位说明

}
