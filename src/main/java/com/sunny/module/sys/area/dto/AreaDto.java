package com.sunny.module.sys.area.dto;

import com.sunny.core.persistence.DtoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaDto extends DtoEntity {

	private static final long serialVersionUID = 1L;

	private String areaId;
	private String areaCode;
	private String areaName;
	private String areaType; // 区域类型（1：国家；2：省份、直辖市、自治区；3：地级市；4：区、县；5：乡、镇；6：村、组）

	private String parentId;
	private String parentName;
	private String treeNode;

}
