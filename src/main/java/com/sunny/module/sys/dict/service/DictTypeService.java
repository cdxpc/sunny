package com.sunny.module.sys.dict.service;

import com.sunny.core.base.BaseService;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;

public interface DictTypeService extends BaseService<DictType, DictDto> {
	
	public boolean deleteBatch(String ids);

}
