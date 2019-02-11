package com.sunny.core.persistence;

import java.util.Date;

import lombok.Data;

@Data
public abstract class Entity {
	
	protected String createBy;		// 创建人   （id:name 的方式保存，例如：U0102:admin）
	protected Date createTime;		// 创建时间
	protected String lastUpdateBy;	// 最后一次变更人（id:name 的方式保存，例如：U0102:admin）
	protected Date lastUpdateTime;	// 最后一次变更时间

}
