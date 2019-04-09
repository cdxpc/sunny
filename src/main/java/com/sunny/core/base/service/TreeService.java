package com.sunny.core.base.service;

import java.util.List;
import java.util.Map;

/**
 * 树操作服务接口
 * 
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 *		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月18日
 * @since 1.0.0v
 */
public interface TreeService<E> {
	
	/**
	 * 获取某个顶级数据
	 * @param id
	 * @return
	 */
	E getRoot(String id);
	
	/**
	 * 获取顶级数据集
	 * @return
	 */
	List<E> getRoots(E e);
	
	/**
	 * 获取树数据
	 * @return list
	 */
	List<Map<String, Object>> getTreeData();

}
