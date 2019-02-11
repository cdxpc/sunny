package com.sunny.module.sys.area.service.impl;

import org.springframework.stereotype.Service;

import com.sunny.module.common.CudServiceImpl;
import com.sunny.module.sys.area.dto.AreaDto;
import com.sunny.module.sys.area.entity.Area;
import com.sunny.module.sys.area.mapper.AreaMapper;
import com.sunny.module.sys.area.service.AreaService;

@Service("areaService")
public class AreaServiceImpl extends CudServiceImpl<Area, AreaDto, AreaMapper> implements AreaService {

}
