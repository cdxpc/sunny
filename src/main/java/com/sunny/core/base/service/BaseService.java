package com.sunny.core.base.service;

import com.github.pagehelper.Page;
import com.sunny.core.persistence.Changer;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 业务处理层基础接口
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 *
 * @param <E>
 */
public interface BaseService<E> {
	
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
	E create(E entity, boolean allowNull);

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
	boolean update(E entity, boolean allowNull);

	/**
	 * 删除对象
	 * 
	 * @param id
	 *            主键id
	 */
	boolean delete(Serializable id);

	/**
	 * 批量删除对象
	 * 
	 * @param ids
	 *            主键ids
	 */
	boolean deleteBatch(String ids);

	/**
	 * 根据id查找对象
	 * 
	 * @param id
	 *            查询传入的id
	 * @return 查询的对象
	 */
	E findById(Serializable id);

	/**
	 * 获取集合记录数
	 * 
	 * @param e
	 *            查询条件对象
	 * @return 含数据对象的集合
	 */
	int findCount(E e);

	/**
	 * 获取单个对象，如果查出了多个会抛出异常
	 * 
	 * @param e
	 *            查询条件对象
	 * @return 含数据对象的集合
	 */
	E findOne(@NonNull E e);

	/**
	 * 获取集合列表
	 * 
	 * @param e
	 *            查询条件对象
	 * @return 含数据对象的集合
	 */
	List<E> find(E e);

	/**
	 * 获取集合列表 (分页)
	 * 
	 * @param e
	 *            查询条件对象
	 * @param page
	 *            分页及排序条件
	 * @return 分页数据和记录数
	 */
	Map<String, Object> findPage(E e, Page<E> page);
	
	/**
	 * 数据交换
	 * 
	 * @param e
	 *            查询条件对象
	 * @param changer
	 *            数据交换对象
	 * @return 是否交换成功
	 */
	boolean changeSort(E e, Changer changer);

}
