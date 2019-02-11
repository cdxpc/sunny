package com.sunny.module.sys.dict.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;

import tk.mybatis.mapper.common.IdsMapper;

public interface DictTypeMapper extends BaseMapper<DictType, DictDto>, IdsMapper<DictType> {

}
