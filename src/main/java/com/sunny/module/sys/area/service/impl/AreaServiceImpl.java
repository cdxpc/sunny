package com.sunny.module.sys.area.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.module.sys.area.entity.Area;
import com.sunny.module.sys.area.mapper.AreaMapper;
import com.sunny.module.sys.area.service.AreaService;

@Service("areaService")
public class AreaServiceImpl extends CudServiceImpl<Area, AreaMapper> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Area getRoot() {
        return areaMapper.selectByPrimaryKey("g001");
    }



}
