package com.sunny.module.sys.org.service.impl;

import org.springframework.stereotype.Service;

import com.sunny.module.common.CudServiceImpl;
import com.sunny.module.sys.org.dto.OrgDto;
import com.sunny.module.sys.org.entity.Org;
import com.sunny.module.sys.org.mapper.OrgMapper;
import com.sunny.module.sys.org.service.OrgService;

@Service("orgService")
public class OrgServiceImpl extends CudServiceImpl<Org, OrgDto, OrgMapper> implements OrgService {

}
