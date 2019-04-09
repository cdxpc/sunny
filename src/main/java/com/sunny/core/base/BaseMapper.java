package com.sunny.core.base;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * dao mapper 基类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 *
 * @param <Entity>  数据库实体映射类
 */
@RegisterMapper
public interface BaseMapper<Entity> extends Mapper<Entity> {

}
