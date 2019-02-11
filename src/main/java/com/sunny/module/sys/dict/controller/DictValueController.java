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
import com.sunny.module.sys.dict.entity.DictValue;
import com.sunny.module.sys.dict.service.DictTypeService;
import com.sunny.module.sys.dict.service.DictValueService;

@Controller
@RequestMapping(RestApiConstants.REST_API_DICT_VALUE)
public class DictValueController extends AbstractInternalController<DictValue, DictDto>{
	
	@Autowired
	private DictTypeService dictTypeService;
	@Autowired
	private DictValueService dictValueService;
	
	@Override
	protected ControllerHelper<DictValue, DictDto> helper() {
		return ControllerHelper.all(dictValueService, DictValue.of(), new DictDto(), RestApiConstants.REST_API_DICT_LIST);
	}

	protected String list() {			    // 列表页
		return toView();
	}
	
	@PostMapping(RestApiConstants.DATA)
    @ResponseBody
	public ResponseJson data(DictDto dto) {
		return findListWithPage(dto);
	}
	
	@GetMapping(RestApiConstants.DATA)
    @ResponseBody
	public ResponseJson dataByDictTypeId(String dictTypeId) {
		DictDto dto = new DictDto();
		dto.setDictTypeId(dictTypeId);
		return findListNoPage(dto);
	}
	
//	@RequiresPermissions("sys:dict:value:add")
	@GetMapping(RestApiConstants.ADD  + "/{dictTypeId}")
	public String add(@PathVariable String dictTypeId, Model model) {
		DictDto dto = helper().getDto();
		DictType dictType = dictTypeService.findById(dictTypeId);
		if(dictType != null) {
			dto.setDictTypeId(dictType.getDictTypeId());
			dto.setDictTypeKey(dictType.getDictTypeKey());
		}
		model.addAttribute("dictValue", dto);
		return RestApiConstants.REST_API_DICT_VALUE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:dict:value:details", "sys:dict:value:edit"}, logical = Logical.OR)
	@GetMapping(RestApiConstants.EDIT + "/{dictValueId}")
	public String edit(@PathVariable String dictValueId, Model model) {
		if(StringUtils.isNotEmpty(dictValueId)) {
			ResponseJson json = findById(dictValueId);
			model.addAttribute("dictValue", json.getRows());
		}
		return RestApiConstants.REST_API_DICT_VALUE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:dict:value:save"})
	@PostMapping(RestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(DictDto dictDto) {
		if(StringUtils.isEmpty(dictDto.getDictValueId())) {
			dictDto.setDictValueId(UuidUtils.uuid());
			return create(dictDto);
		} 
		return update(dictDto.getDictValueId(), dictDto);
	}
	
//	@RequiresPermissions(value = {"sys:dict:value:remove"})
    @PostMapping(RestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String ids) {
		return deleteByIds(ids);
	}

}
