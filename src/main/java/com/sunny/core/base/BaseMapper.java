package com.sunny.core.base;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface BaseMapper<Entity, Dto> extends Mapper<Entity> {

}
