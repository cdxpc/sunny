package com.sunny.module.db.database.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.db.DbRestApiConstants;
import com.sunny.module.db.database.domain.DataBaseDto;
import com.sunny.module.db.database.entity.DataBase;
import com.sunny.module.db.database.service.DataBaseService;

@Controller
@RequestMapping(DbRestApiConstants.REST_API_DATA_BASE)
public class DataBaseController extends AbstractInternalController<DataBase, DataBaseDto> {
	
	private DataBaseService dataBaseService = SpringContextHolder.getBean(DataBaseService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);

	@Override
	protected ControllerHelper<DataBase, DataBaseDto> helper() {
		return ControllerHelper.all(dataBaseService, DataBase.of(), new DataBaseDto(), DbRestApiConstants.REST_API_DATA_BASE_LIST);
	}
	
	public String list() {
		return toView();
	}
	
	@PostMapping(RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(DataBaseDto dto) { 	// 数据列表
		return findListWithPage(dto);
	}
	
	@GetMapping(RestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("dataBase", helper().getDto()); 
		dict(model);
		return DbRestApiConstants.REST_API_DATA_BASE_FORM;
	}
	
	@GetMapping(RestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("dataBase", json.getRows());
		}
		dict(model);
		return DbRestApiConstants.REST_API_DATA_BASE_FORM;
	}
	
	private void dict(Model model) {
		model.addAttribute("dbTypes", dictHelper.getValuesByType("dict_db_type"));
    	model.addAttribute("isDefaults", dictHelper.getValuesByType("dict_is_default"));
	}
	
	@PostMapping(RestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(DataBaseDto dto) {
//		if(StringUtils.isEmpty(domain.getDbId())) {
//			domain.setDbId(UuidUtils.uuid());
//			return create(domain);
//		} 
//		return update(domain.getDbId(), domain);
		
		if(StringUtils.isNotEmpty(dto.getDbId())) {
			// 这里不使用update的方式，改为：先删除，在插入
			deleteById(dto.getDbId());
		}
		dto.setDbId(UuidUtils.uuid());   // XXX 数据表需要随着发送变化
		return create(dto);
	}
	
	@PostMapping(RestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String id) {
		return deleteById(id);
	}

}
