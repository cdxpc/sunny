package com.sunny.core.persistence;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体类基类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Getter
@Setter
public abstract class Entity {
	
	protected String createBy;		// 创建人   （id:name 的方式保存，例如：U0102:admin）
	protected Date createTime;		// 创建时间
	protected String lastUpdateBy;	// 最后一次变更人（id:name 的方式保存，例如：U0102:admin）
	protected Date lastUpdateTime;	// 最后一次变更时间

}
