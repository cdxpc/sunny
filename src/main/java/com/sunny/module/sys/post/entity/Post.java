package com.sunny.module.sys.post.entity;

import com.sunny.core.persistence.DbEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_POST")
public class Post extends DbEntity {
	
	@Id
	private String postId;		// 岗位主键id
	private String postCode;	// 岗位号
	private String postName;	// 岗位名称
	private String remarks;		// 岗位说明
	
}
