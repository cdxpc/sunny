package com.sunny.core.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

import lombok.NonNull;

public interface BaseService<E, D> {
	
	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            所要保存的对象
	 * @param allowNull
	 *            是否运行插入空值
	 * @return
	 * @
	 */
	public E create(E entity, boolean allowNull);

	/**
	 * 更新对象
	 * 
	 * @param entity
	 *            所要保存的对象
	 * @param allowNull
	 *            是否运行插入空值
	 * @return
	 * @
	 */
	public boolean update(E entity, boolean allowNull);

	/**
	 * 删除对象
	 * 
	 * @param id
	 *            主键id
	 */
	public boolean delete(Serializable id);

	/**
	 * 批量删除对象
	 * 
	 * @param ids
	 *            主键ids
	 */
	public boolean deleteBatch(String ids);

	/**
	 * 根据id查找对象
	 * 
	 * @param id
	 *            查询传入的id
	 * @return 查询的对象
	 */
	public E findById(Serializable id);

	/**
	 * 获取集合记录数
	 * 
	 * @param e
	 *            查询条件对象
	 * @return 含数据对象的集合
	 */
	public int findCount(E e);

	/**
	 * 获取单个对象，如果查出了多个会抛出异常
	 * 
	 * @param e
	 *            查询条件对象
	 * @return 含数据对象的集合
	 */
	public E findOne(@NonNull E e);

	/**
	 * 获取集合列表
	 * 
	 * @param e
	 *            查询条件对象
	 * @return 含数据对象的集合
	 */
	public List<E> find(E e);

	/**
	 * 获取集合列表 (分页)
	 * 
	 * @param e
	 *            查询条件对象
	 * @param Page
	 *            分页及排序条件
	 * @return
	 * @
	 */
	public Map<String, Object> findPage(E e, Page<E> page);

}
