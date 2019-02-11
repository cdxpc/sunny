package com.sunny.module.sys.dict.dto;

import com.sunny.core.persistence.DtoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictDto extends DtoEntity {

	private static final long serialVersionUID = 1L;
	
	// type
	private String dictTypeId;	// 字典类型id
	private String dictTypeKey;	// 字典类型key
	private String dictTypeName;// 字典类型名称
	
	// value
	private String dictValueId;	// 字典值id
	private String dictLabel;	// 字典标签
	private String dictValue;	// 字典值
	private String cssClass;	// 样式
	private String isDefault;	// 是否默认
	
}
