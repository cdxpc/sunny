package com.sunny.module.sys.dict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.core.persistence.Changer;
import com.sunny.core.persistence.DbEntity;
import com.sunny.module.DataChangeHelper;
import com.sunny.module.sys.dict.entity.DictValue;
import com.sunny.module.sys.dict.mapper.DictValueMapper;
import com.sunny.module.sys.dict.service.DictValueService;

@Service("dictValueService")
public class DictValueServiceImpl extends CudServiceImpl<DictValue, DictValueMapper> implements DictValueService {
	
	@Autowired
	private DictValueMapper dictValueMapper;

	@Override
	public boolean deleteBatch(String ids) {
		Assert.notNull(ids, "the ids is null.");
		return dictValueMapper.deleteByIds(ids) > 0;
	}

	@Override
	public boolean changeSort(DictValue value, Changer changer) {
		boolean flag = false;
		List<DbEntity> datas = DataChangeHelper.getInstance(dictValueMapper).changeSort(value, changer);
		if(datas != null && datas.size() > 0) {
			for (DbEntity e : datas) {
				flag = dictValueMapper.updateByPrimaryKeySelective((DictValue) e) > 0;
			}
		}
		return flag;
	}

}
