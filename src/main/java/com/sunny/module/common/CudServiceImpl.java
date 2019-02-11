package com.sunny.module.common;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sunny.core.base.BaseMapper;

@Transactional(rollbackFor = Exception.class)
public abstract class CudServiceImpl<E, D, M extends BaseMapper<E, D>> extends QueryServiceImpl<E, D, M> {

	protected int rowAff;
	protected boolean flag = false;

	@Override
	public E create(E entity, boolean allowNull) {
		Assert.notNull(entity, "the entity object is null.");
		if(allowNull) {
			return mapper.insert(entity) > 0 ? entity : null;
		}
		return mapper.insertSelective(entity) > 0 ? entity : null;
	}

	@Override
	public boolean update(E entity, boolean allowNull) {
		Assert.notNull(entity, "the entity object is null.");
		if(allowNull) {
			return mapper.updateByPrimaryKey(entity) > 0 ? true : false;
		}
		return mapper.updateByPrimaryKeySelective(entity) > 0 ? true : false;
	}

	@Override
	public boolean delete(Serializable id) {
		Assert.notNull(id, "the id is null.");
		return mapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}
	
	@Override
	public boolean deleteBatch(String ids) {
		// 需要用到批量删除的方法，由子类来覆盖
		throw new UnsupportedOperationException("需要用到批量删除的方法，子类方法必须实现<IdsMapper>接口，并覆盖此方法！");
	}
	
}
