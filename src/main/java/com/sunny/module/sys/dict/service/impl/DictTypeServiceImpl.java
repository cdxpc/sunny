package com.sunny.module.sys.dict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.core.persistence.Changer;
import com.sunny.core.persistence.DbEntity;
import com.sunny.module.DataChangeHelper;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;
import com.sunny.module.sys.dict.mapper.DictTypeMapper;
import com.sunny.module.sys.dict.service.DictTypeService;

import tk.mybatis.mapper.entity.Example;

@Service("dictTypeService")
public class DictTypeServiceImpl extends CudServiceImpl<DictType, DictTypeMapper> implements DictTypeService {
	
	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	@Override
	public boolean deleteBatch(String ids) {
		Assert.notNull(ids, "the ids is null.");
		return dictTypeMapper.deleteByIds(ids) > 0;
	}

	@Override
	public boolean changeSort(DictType type, Changer changer) {
		boolean flag = false;
		List<DbEntity> datas = DataChangeHelper.getInstance(dictTypeMapper).changeSort(type, changer);
		if(datas != null && datas.size() > 0) {
			for (DbEntity e : datas) {
				flag = dictTypeMapper.updateByPrimaryKeySelective((DictType) e) > 0;
			}
		}
		return flag;
	}

	@Override
	public long findCountExSelf(DictDto dto) {
		Example example = new Example(DictType.class);
		example.createCriteria()
			   .andCondition("del_flag = ", dto.getDelFlag())
			   .andCondition("dict_type_key = ", dto.getDictTypeKey())
			   .andCondition("dict_type_id <> ", dto.getDictTypeId());
		return dictTypeMapper.selectCountByExample(example);
	}

}
