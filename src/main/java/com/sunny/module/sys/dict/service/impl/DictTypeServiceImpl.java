package com.sunny.module.sys.dict.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sunny.module.common.CudServiceImpl;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;
import com.sunny.module.sys.dict.mapper.DictTypeMapper;
import com.sunny.module.sys.dict.service.DictTypeService;

@Service("dictTypeService")
public class DictTypeServiceImpl extends CudServiceImpl<DictType, DictDto, DictTypeMapper> implements DictTypeService {
	
	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	@Override
	public boolean deleteBatch(String ids) {
		Assert.notNull(ids, "the ids is null.");
		return dictTypeMapper.deleteByIds(ids) > 0 ? true : false;
	}

}
