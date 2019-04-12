package com.sunny.core.base.controller;

import com.github.pagehelper.Page;
import com.sunny.core.ResponseJson;
import com.sunny.core.base.service.BaseService;
import com.sunny.core.persistence.DbEntity;
import com.sunny.core.persistence.DtoEntity;
import com.sunny.core.util.BeanConvertUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AbstractInternalController<E extends DbEntity, D extends DtoEntity> extends AbstractController<E, D> {
	
	@Override
	public ResponseJson doCreate(D dto) throws Exception {
		E entity = BeanConvertUtils.Dto2Entity(getEntity(), dto);
		entity = getService().create(entity, ALLOW_NULL);
		ResponseJson responseJson = ResponseJson.created();
		responseJson.setRows(BeanConvertUtils.Entity2Dto(dto, entity));
		return responseJson;
	}
	
	@Override
	public ResponseJson doUpdate(Serializable id, D dto) throws Exception {
		ResponseJson responseJson = ResponseJson.notFound();
		boolean flag = false;
		// 先通过uuid查询下更新的数据是否存在，存在则更新，否则直接返回
		E entity = getService().findById(id);
		if(entity != null) {
			// 将需要更新的内容从dto覆盖到entity中去
			entity = BeanConvertUtils.Dto2Entity(entity, dto);
			// 更新数据库
			flag = getService().update(entity, ALLOW_NULL);
		}
		if(flag){				
			responseJson = ResponseJson.ok();
		}
		responseJson.setRows(flag);
		return responseJson;
	}

	@Override
	public ResponseJson doDeleteById(Serializable id) {
		ResponseJson responseJson = ResponseJson.notFound();
		boolean flag = getService().delete(id);
		if(flag){				
			responseJson = ResponseJson.ok();
		}
		responseJson.setRows(flag);
		return responseJson;
	}
	
	@Override
	protected ResponseJson doDeleteByIds(String ids) {
		ResponseJson responseJson = ResponseJson.notFound();
		boolean flag = getService().deleteBatch(ids);
		if(flag){				
			responseJson = ResponseJson.ok();
		}
		responseJson.setRows(flag);
		return responseJson;
	}

	@Override
	public ResponseJson doFindById(Serializable id) throws Exception {
		ResponseJson responseJson = ResponseJson.notFound();
		E entity = getService().findById(id);
		if(entity != null) {
			responseJson = ResponseJson.ok();
			responseJson.setRows(BeanConvertUtils.Entity2Dto(getDto(), entity));
		}
		return responseJson;
	}

	@Override
	public ResponseJson doFindAll() throws Exception {
		ResponseJson responseJson = ResponseJson.noData();
		List<E> all = getService().find(null);
		if(!CollectionUtils.isEmpty(all)) {
			responseJson = ResponseJson.ok();
		}
		responseJson.setRows(BeanConvertUtils.Entitys2Dtos(new ArrayList<>(), getDtoClass(), all));
		return responseJson;
	}
	
	@Override
	public ResponseJson doFindListNoPage(D dto) throws Exception {
		ResponseJson responseJson = ResponseJson.noData();
		E entity;
		if(ALLOW_NULL) {
			entity = BeanConvertUtils.Dto2Entity(getEntity(), dto);
		} else {
			entity = BeanConvertUtils.DtoNotNull2Entity(getEntity(), dto);
		}
		List<E> list = getService().find(entity);
		if(!CollectionUtils.isEmpty(list)) {
			responseJson = ResponseJson.ok();
		}
		responseJson.setRows(BeanConvertUtils.Entitys2Dtos(new ArrayList<>(), getDtoClass(), list));
		return responseJson;
	}
	
	@Override
	public ResponseJson doFindListWithPage(D dto, Page<E> page) throws Exception {
		List<E> list = Collections.emptyList();
		ResponseJson responseJson = ResponseJson.noData();
		E entity;
		if(ALLOW_NULL) {
			entity = BeanConvertUtils.Dto2Entity(getEntity(), dto);
		} else {
			entity = BeanConvertUtils.DtoNotNull2Entity(getEntity(), dto);
		}
		Map<String, Object> map = getService().findPage(entity, page);
		int total = (int) map.get("total");
		if(total > 0) {
			list = (List<E>) map.get("dataList");
			responseJson = ResponseJson.ok();
			responseJson.setPageNow(page.getPageNum());
			int pageSize = page.getPageSize();
			responseJson.setPageSize(pageSize);
			responseJson.setPages(total / pageSize + ((total % pageSize == 0) ? 0 : 1));
			responseJson.setTotal(total);
		}
		responseJson.setRows(BeanConvertUtils.Entitys2Dtos(new ArrayList<>(), getDtoClass(), list));
		return responseJson;
	}
	
	@Override
	public ResponseJson doFindCount(D dto) throws Exception {
		ResponseJson responseJson = ResponseJson.ok();
		E entity;
		if(ALLOW_NULL) {
			entity = BeanConvertUtils.Dto2Entity(getEntity(), dto);
		} else {
			entity = BeanConvertUtils.DtoNotNull2Entity(getEntity(), dto);
		}
		int count = getService().findCount(entity);
		responseJson.setTotal(count);
		return responseJson;
	}
	
	private BaseService<E> getService() {
		return helper().getService();
	}
	
	private E getEntity() {
		return helper().getEntity();
	}
	
	private D getDto() {
		return helper().getDto();
	}
	
	private Class<D> getDtoClass() {
		return (Class<D>) getDto().getClass();
	}

}
