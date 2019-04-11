package com.sunny.module.db.table.service.impl;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.module.db.table.entity.DbTable;
import com.sunny.module.db.table.mapper.DbTableMapper;
import com.sunny.module.db.table.service.DbTableService;
import org.springframework.stereotype.Service;

@Service("dbTableService")
public class DbTableServiceImpl extends CudServiceImpl<DbTable, DbTableMapper> implements DbTableService {



}
