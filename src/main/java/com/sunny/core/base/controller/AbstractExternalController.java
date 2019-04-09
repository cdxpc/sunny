package com.sunny.core.base.controller;

import java.io.Serializable;

import com.github.pagehelper.Page;
import com.sunny.core.ResponseJson;
import com.sunny.core.persistence.DbEntity;
import com.sunny.core.persistence.DtoEntity;

public abstract class AbstractExternalController<E extends DbEntity, D extends DtoEntity> extends AbstractController<E, D> {
	
	@Override
	public ResponseJson doCreate(D dto) throws Exception {
		return getResponseJson(); //  权限暂不开放
	}
	
	@Override
	protected ResponseJson doUpdate(Serializable id, D dto) throws Exception {
		return getResponseJson(); //  权限暂不开放
	}
	
	@Override
	protected ResponseJson doDeleteById(Serializable id) {
		return getResponseJson(); //  权限暂不开放
	}
	
	@Override
	protected ResponseJson doDeleteByIds(String ids) {
		return getResponseJson(); //  权限暂不开放
	}

	@Override
	protected ResponseJson doFindById(Serializable id) throws Exception {
		return getResponseJson(); //  权限暂不开放
	}

	@Override
	protected ResponseJson doFindAll() throws Exception {
		return getResponseJson(); //  权限暂不开放
	}

	@Override
	protected ResponseJson doFindListNoPage(D dto) throws Exception {
		return getResponseJson(); //  权限暂不开放
	}

	@Override
	protected ResponseJson doFindListWithPage(D dto, Page<E> page) throws Exception {
		return getResponseJson(); //  权限暂不开放
	}
	
	@Override
	protected ResponseJson doFindCount(D dto) throws Exception {
		return getResponseJson(); //  权限暂不开放
	}
	
	protected ResponseJson getResponseJson() {
		return ResponseJson.noPerm();
	}
}
