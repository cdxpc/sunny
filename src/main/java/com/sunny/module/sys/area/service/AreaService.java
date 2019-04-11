package com.sunny.module.sys.area.service;

import com.sunny.core.base.service.BaseService;
import com.sunny.module.sys.area.entity.Area;

public interface AreaService extends BaseService<Area> {

    // 获取顶级区域
    Area getRoot();

}
