package com.sunny.core.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
