package com.sunny.core.base.service;

import com.sunny.core.base.BaseMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
public abstract class TreeServiceImpl<E, M extends BaseMapper<E>> extends CudServiceImpl<E, M> implements TreeService<E> {

	@Override
	public List<Map<String, Object>> getTreeData() {
		List<E> all = this.mapper.selectAll();
		return toTree(all);
	}
	
	protected abstract List<Map<String, Object>> toTree(List<E> all);

	@Override
	public E getRoot(String id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	// @Override
	// public List<E> getRoots(E e) {
	// 	return this.mapper.select(e);
	// }

}
