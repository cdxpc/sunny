package com.sunny.core.persistence;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Changer extends Entity {
	
	// 是否上移
	private boolean up;
	// 是否涉及到上一页，或下一页内容
	private String action;
	// 当前页
	private Integer pageNum;
	// 当前每页显示条数
	private Integer pageSize;
	// 当前上下移动的下标标识号
	private Integer index;
	// 是否需要使用分页
	private boolean needPage;
	
}
