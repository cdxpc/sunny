package com.sunny.module.sys.dict.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sunny.module.common.CudServiceImpl;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictValue;
import com.sunny.module.sys.dict.mapper.DictValueMapper;
import com.sunny.module.sys.dict.service.DictValueService;

@Service("dictValueService")
public class DictValueServiceImpl extends CudServiceImpl<DictValue, DictDto, DictValueMapper> implements DictValueService {
	
	@Autowired
	private DictValueMapper dictValueMapper;

	@Override
	public boolean deleteBatch(String ids) {
		Assert.notNull(ids, "the ids is null.");
		return dictValueMapper.deleteByIds(ids) > 0 ? true : false;
	}

}
