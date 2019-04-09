package com.sunny.core.persistence;

import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据库实体类基类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Getter
@Setter
public abstract class DbEntity extends Entity {
	
	// 排序字段
	@OrderBy
	private Integer sort;
	// 记录删除标志（Y-逻辑删除， N-未删除）
	protected String delFlag = "N"; // 默认操作未删除的数据
	// 记录状态标志（1-有效，2-锁定）
	protected String status;
	
	/**
	 * 删除标志和状态标志的区别：
	 * 	1、删除的数据无论状态为何种状态均不能操作，已删除的数据无法恢复为未删除
	 * 		Y-已删除，不可恢复，不可访问
	 * 		N-未删除，可访问，可删除（一旦变成删除状态则该数据不可逆转）
	 *  2、所有操作的数据必须为未删除的数据，未删除的数据状态标志的两种状态可以任意切换，但是可访问的程度与状态又密切关系（数据可以逆转）
	 *  	1-有效，所有功能有效，可以锁定
	 *  	2-锁定，部分功能有效，可以恢复
	 */

}
