package com.sunny.core.util;

import java.util.List;

/**
 * javaBean 属性相互拷贝处理器
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
public class BeanConvertUtils {

	public static <E, D> E Dto2Entity(E entity, D dto) throws Exception {
		if (dto != null) {
			JavaBeanUtils.copyBean2Bean(entity, dto);
		}
		return entity;
	}

	public static <E, D> List<E> Dtos2Entitys(List<E> entitys, Class<? extends E> clazz, List<D> dtos)
			throws Exception {
		if (dtos != null && dtos.size() > 0) {
			for (D dto : dtos) {
				entitys.add(Entity2Dto(clazz.newInstance(), dto));
			}
		}
		return entitys;
	}

	public static <E, D> D Entity2Dto(D dto, E entity) throws Exception {
		if (entity != null) {
			JavaBeanUtils.copyBean2Bean(dto, entity);
		}
		return dto;
	}

	public static <E, D> List<D> Entitys2Dtos(List<D> dtos, Class<? extends D> clazz, List<E> entitys)
			throws Exception {
		if (entitys != null && entitys.size() > 0) {
			for (E entity : entitys) {
				dtos.add(Entity2Dto(clazz.newInstance(), entity));
			}
		}
		return dtos;
	}

	// ===================================

	public static <E, D> E DtoNotNull2Entity(E entity, D dto) throws Exception {
		if (dto != null) {
			JavaBeanUtils.copyBeanNotNull2Bean(entity, dto);
		}
		return entity;
	}

	public static <E, D> List<E> DtosNotNull2Entitys(List<E> entitys, Class<? extends E> clazz, List<D> dtos)
			throws Exception {
		if (dtos != null && dtos.size() > 0) {
			for (D dto : dtos) {
				entitys.add(DtoNotNull2Entity(clazz.newInstance(), dto));
			}
		}
		return entitys;
	}

	public static <E, D> D EntityNotNull2Dto(D dto, E entity) throws Exception {
		if (entity != null) {
			JavaBeanUtils.copyBeanNotNull2Bean(dto, entity);
		}
		return dto;
	}

	public static <E, D> List<D> EntitysNotNull2Dtos(List<D> dtos, Class<? extends D> clazz, List<E> entitys)
			throws Exception {
		if (entitys != null && entitys.size() > 0) {
			for (E entity : entitys) {
				dtos.add(EntityNotNull2Dto(clazz.newInstance(), entity));
			}
		}
		return dtos;
	}

}
