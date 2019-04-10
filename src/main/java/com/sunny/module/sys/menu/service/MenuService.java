package com.sunny.module.sys.menu.service;

import com.sunny.core.base.service.BaseService;
import com.sunny.core.base.service.TreeService;
import com.sunny.module.sys.menu.entity.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu>, TreeService<Menu> {

	List<Menu> findMenusByUserId(String userId);

}
