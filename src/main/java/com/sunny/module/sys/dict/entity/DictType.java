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
@Table(name = "FT_SYS_DICT_TYPE")
public class DictType extends DbEntity {
	
	@Id
	private String dictTypeId;
	private String dictTypeKey;
	private String dictTypeName;

}
