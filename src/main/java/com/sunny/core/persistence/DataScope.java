package com.sunny.core.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 数据操作域
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DataScope {

	C(1), // 创建
	U(2), // 更新
	D(3), // 删除
	R(4), // 读取
	;

	@Getter
	@NonNull
	Integer code;

}
