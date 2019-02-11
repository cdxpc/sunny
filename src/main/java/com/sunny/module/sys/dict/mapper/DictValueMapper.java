package com.sunny.module.sys.dict.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictValue;

import tk.mybatis.mapper.common.IdsMapper;

public interface DictValueMapper extends BaseMapper<DictValue, DictDto>, IdsMapper<DictValue> {

}
