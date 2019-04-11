package com.sunny.module.sys.dict.service;

import com.sunny.core.base.service.BaseService;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;

public interface DictTypeService extends BaseService<DictType> {
	
	boolean deleteBatch(String ids);

	long findCountExSelf(DictDto dto);

}
