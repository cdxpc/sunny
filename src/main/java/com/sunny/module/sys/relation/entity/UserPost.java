package com.sunny.module.sys.relation.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_USER_POST")
public class UserPost {
	
	private String userId;
	private String postId;

}
