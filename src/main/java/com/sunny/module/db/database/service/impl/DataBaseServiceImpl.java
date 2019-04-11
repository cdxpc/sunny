package com.sunny.module.db.database.service.impl;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.module.db.database.entity.DataBase;
import com.sunny.module.db.database.mapper.DataBaseMapper;
import com.sunny.module.db.database.service.DataBaseService;
import org.springframework.stereotype.Service;

@Service("dataBaseService")
public class DataBaseServiceImpl extends CudServiceImpl<DataBase, DataBaseMapper> implements DataBaseService {



}
