package com.sunny.module.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.sunny.core.base.BaseMapper;
import com.sunny.core.base.BaseService;

import lombok.NonNull;

public abstract class QueryServiceImpl<E, D, M extends BaseMapper<E, D>> implements BaseService<E, D> {

	@Autowired
	protected M mapper;
	
	@Override
	public E findById(Serializable id) {
		Assert.notNull(id, "the id is null.");
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int findCount(E e) {
		return mapper.selectCount(e);
	}
	
	@Override
	public E findOne(@NonNull E e) {
		return mapper.selectOne(e);
	}

	@Override
	public List<E> find(E e) {
		if(e == null) {
			return mapper.selectAll();
		}
		return mapper.select(e);
	}
	
	@Override
	public Map<String, Object> findPage(E e, Page<E> page) {
		Map<String, Object> result = new HashMap<>();
		List<E> pageList = null;
		int rows = this.findCount(e);
		if(rows > 0) {
			pageList = mapper.selectByRowBounds(e, new RowBounds((page.getPageNum() -1) * page.getPageSize(), page.getPageSize()));
		}
		result.put("total", rows);
		result.put("dataList", pageList);
		return result;
	}
	
}
