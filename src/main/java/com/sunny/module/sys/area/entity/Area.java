package com.sunny.module.sys.area.entity;

import javax.persistence.Table;

import com.sunny.core.persistence.DbEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "FT_SYS_AREA")
public class Area extends DbEntity {
	
	private String areaId;
	private String areaCode;
	private String areaName;
	private String areaType; // 区域类型（1：国家；2：省份、直辖市、自治区；3：地级市；4：区、县；5：乡、镇；6：村、组）
	private Integer sort;

}
