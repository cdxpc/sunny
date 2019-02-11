package com.sunny.module.sys.menu.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.sys.menu.dto.MenuDto;
import com.sunny.module.sys.menu.entity.Menu;

import tk.mybatis.mapper.common.IdsMapper;

public interface MenuMapper extends BaseMapper<Menu, MenuDto>, IdsMapper<Menu> {

}
