package com.sunny.module.sys.org.service.impl;

import com.sunny.core.base.service.TreeServiceImpl;
import com.sunny.module.TreeHelper;
import com.sunny.module.sys.org.entity.Org;
import com.sunny.module.sys.org.mapper.OrgMapper;
import com.sunny.module.sys.org.service.OrgService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("orgService")
public class OrgServiceImpl extends TreeServiceImpl<Org, OrgMapper> implements OrgService {

	@Override
	protected List<Map<String, Object>> toTree(List<Org> all) {
		return TreeHelper.toOrgTree(all, false, null);
	}



}
