package com.sunny.module.sys.dict.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(staticName = "of")
@Table(name = "FT_SYS_DICT_VALUE")
public class DictValue extends DbEntity {
	
	@Id
	private String dictValueId;	// 字典主键id
	private String dictLabel;	// 字典标签
	private String dictValue;	// 字典值
	private String dictTypeId; 	// 字典类型id  外键
	private String dictTypeKey;	// 字典key  冗余字段
	private String cssClass;	// 样式
	private String isDefault;	// 是否默认

}
