package com.sunny.module.sys.menu.service;

import java.util.List;

import com.sunny.core.base.BaseService;
import com.sunny.module.sys.menu.dto.MenuDto;
import com.sunny.module.sys.menu.entity.Menu;

public interface MenuService extends BaseService<Menu, MenuDto> {

	List<Menu> findMenusByUserId(String userId);

}
