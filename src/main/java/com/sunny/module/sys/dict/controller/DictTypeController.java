package com.sunny.module.sys.dict.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.common.AbstractInternalController;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;
import com.sunny.module.sys.dict.service.DictTypeService;

@Controller
@RequestMapping(RestApiConstants.REST_API_DICT_TYPE)
public class DictTypeController extends AbstractInternalController<DictType, DictDto>{
	
	@Autowired
	private DictTypeService dictTypeService;

	@Override
	protected ControllerHelper<DictType, DictDto> helper() {
		return ControllerHelper.all(dictTypeService, DictType.of(), new DictDto(), RestApiConstants.REST_API_DICT_LIST);
	}

//	@RequiresPermissions("sys:dict:type:view")
	protected String list() {			    // 列表页
		return toView();
	}

//	@RequiresPermissions("sys:dict:type:data")
	@PostMapping(RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(DictDto dto) { // 数据列表
		return findListWithPage(dto);
	}
	
//	@RequiresPermissions("sys:dict:type:add")
	@GetMapping(RestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("dictType", helper().getDto());
		return RestApiConstants.REST_API_DICT_TYPE_FORM;
	}

//	@RequiresPermissions(value = {"sys:dict:type:details", "sys:dict:type:edit"}, logical = Logical.OR)
	@GetMapping(RestApiConstants.EDIT + "/{dictTypeId}")
	public String edit(@PathVariable String dictTypeId, Model model) {
		if(StringUtils.isNotEmpty(dictTypeId)) {
			ResponseJson json = findById(dictTypeId);
			model.addAttribute("dictType", json.getRows());
		}
		return RestApiConstants.REST_API_DICT_TYPE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:dict:type:save"})
	@PostMapping(RestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(DictDto dictDto) {
		if(StringUtils.isEmpty(dictDto.getDictTypeId())) {
			dictDto.setDictTypeId(UuidUtils.uuid());
			return create(dictDto);
		} 
		return update(dictDto.getDictTypeId(), dictDto);
	}
	
//	@RequiresPermissions(value = {"sys:dict:type:remove"})
    @PostMapping(RestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String ids) {
		return deleteByIds(ids);
	}
	
}
