package com.sunny.core.base.controller;

import java.io.Serializable;

import com.github.pagehelper.Page;
import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.persistence.DataScope;
import com.sunny.core.persistence.DbEntity;
import com.sunny.core.persistence.DtoEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 访问控制层公共抽象类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 *
 * @param <E>
 * @param <D>
 */
public abstract class AbstractController<E extends DbEntity, D extends DtoEntity> extends BaseController<E> {
	
	protected abstract ControllerHelper<E, D> helper();

	protected abstract ResponseJson doCreate(D dto) throws Exception;
	protected abstract ResponseJson doUpdate(Serializable id, D dto) throws Exception;
	protected abstract ResponseJson doDeleteById(Serializable id);
	protected abstract ResponseJson doDeleteByIds(String ids);
	protected abstract ResponseJson doFindById(Serializable id) throws Exception;
	protected abstract ResponseJson doFindAll() throws Exception;
	protected abstract ResponseJson doFindListNoPage(D dto) throws Exception;
	protected abstract ResponseJson doFindListWithPage(D dto, Page<E> page) throws Exception;
	protected abstract ResponseJson doFindCount(D dto) throws Exception;

	@GetMapping(RestApiConstants.LIST)		// 列表页
	protected String toView() {
		return helper().getViewUrl();
	}
	
//	@ApiOperation(value="create",notes="新增数据")
//	@PostMapping
	protected ResponseJson create(D dto) {
		try {
			dto.pre(DataScope.C);
			ResponseJson responseJson = doCreate(dto);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}
	
//	@ApiOperation(value="update",notes="根据主键id来更新某条指定的数据信息")
//	@PutMapping("{id}")
	protected ResponseJson update(Serializable id, D dto) {
		try {
			dto.pre(DataScope.U);
			ResponseJson responseJson = doUpdate(id, dto);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}

//	@ApiOperation(value="deleteById",notes="根据主键id来删除指定的数据")
//	@DeleteMapping("{id}")
	protected ResponseJson deleteById(Serializable id) {
		try {
			ResponseJson responseJson = doDeleteById(id);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}
	
//	@ApiOperation(value="deleteBatch",notes="根据主键ids来批量删除指定的数据")
//	@PostMapping("deleteBatch")
	protected ResponseJson deleteByIds(String ids) {
		try {
			ResponseJson responseJson = doDeleteByIds(ids);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}

//	@ApiOperation(value = "findById", notes = "根据id获取指定的菜单")
//	@GetMapping("{id}")
	protected ResponseJson findById(Serializable id) {
		try {
			ResponseJson responseJson = doFindById(id);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}

//	@ApiOperation(value="findAll",notes="获取所有数据")
//	@GetMapping("all")
	protected ResponseJson findAll() {
		try {
			ResponseJson responseJson = doFindAll();
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}
	
//	@ApiOperation(value="findListNoPage",notes="根据查询条件获取数据信息集合，结果不分页！")
//	@GetMapping("noPage")
	protected ResponseJson findListNoPage(D dto) {
		try {
			dto.pre(DataScope.R);
			ResponseJson responseJson = doFindListNoPage(dto);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		}
	}
	
//	@ApiOperation(value="findListWithPage",notes="根据查询条件获取数据信息集合，结果分页！")
//	@PostMapping("page")
	protected ResponseJson findListWithPage(D dto) {
		try {
			dto.pre(DataScope.R);
			ResponseJson responseJson = doFindListWithPage(dto, super.initPage());
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		} 
	}
	
//	@ApiOperation(value="findCount",notes="根据查询条件获取数据记录数！")
//	@PostMapping("page")
	protected ResponseJson findCount(D dto) {
		try {
			dto.pre(DataScope.R);
			ResponseJson responseJson = doFindCount(dto);
			return sendSuccess(responseJson);
		} catch (Exception e) {
			return sendError(e);
		} 
	}

}
