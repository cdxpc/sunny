package com.sunny.module.sys.dict.controller;

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
import com.sunny.core.persistence.Changer;
import com.sunny.core.util.BeanConvertUtils;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;
import com.sunny.module.sys.dict.entity.DictValue;
import com.sunny.module.sys.dict.service.DictTypeService;
import com.sunny.module.sys.dict.service.DictValueService;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_DICT_VALUE)
public class DictValueController extends AbstractInternalController<DictValue, DictDto>{
	
	private DictTypeService dictTypeService = SpringContextHolder.getBean(DictTypeService.class);
	private DictValueService dictValueService = SpringContextHolder.getBean(DictValueService.class);
	
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);
	
	@Override
	protected ControllerHelper<DictValue, DictDto> helper() {
		return ControllerHelper.all(dictValueService, DictValue.of(), new DictDto(), SysRestApiConstants.REST_API_DICT_LIST);
	}

	public String list() {	// 列表页
		return toView();
	}
	
	@GetMapping(SysRestApiConstants.DATA)
    @ResponseBody
	public ResponseJson dataByDictTypeKey(String dictTypeKey) {
		if(StringUtils.isNotEmpty(dictTypeKey)) {
			ResponseJson json = ResponseJson.ok();
			json.setRows(dictHelper.getValuesByType(dictTypeKey));
			return json;
		}
		return ResponseJson.noData();
	}
	
//	@RequiresPermissions("sys:dict:value:add")
	@GetMapping(SysRestApiConstants.ADD  + "/{dictTypeKey}")
	public String add(@PathVariable String dictTypeKey, Model model) {
		DictDto dto = helper().getDto();
		DictType dictType = dictHelper.getType(dictTypeKey);
		if(dictType != null) {
			dto.setDictTypeId(dictType.getDictTypeId());
			dto.setDictTypeKey(dictType.getDictTypeKey());
		}
		model.addAttribute("dictValue", dto);
		return SysRestApiConstants.REST_API_DICT_VALUE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:dict:value:details", "sys:dict:value:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("dictValue", json.getRows());
		}
		return SysRestApiConstants.REST_API_DICT_VALUE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:dict:value:save"})
	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(DictDto dto) {
		if(StringUtils.isEmpty(dto.getDictValueId())) {
			dto.setDictValueId(UuidUtils.uuid());
			// 先进行创建字段值
			ResponseJson json = create(dto);
			if(json.getResult().getCode() == 201) {
				// 如果创建成功，则需要修改字典类型hashValues字段的值为1
				DictType dictType = dictTypeService.findById(dto.getDictTypeId());
				// 如果第一次创建成功了，则后面的就不用再进行update操作了
				if(dictType != null && !dictType.getHasValues().equals("1")) {
					dictType.setHasValues("1");
					dictTypeService.update(dictType, false);
				}
			}
		} 
		return update(dto.getDictValueId(), dto);
	}
	
//	@RequiresPermissions(value = {"sys:dict:value:remove"})
    @PostMapping(SysRestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String id) {
    	DictValue value = dictValueService.findById(id);
    	String dictTypeId = value.getDictTypeId();
    	ResponseJson json = deleteById(id);
    	if(json.getResult().getCode() == 200) {
			// 如果创建成功，则需要修改字典类型hashValues字段的值为1
    		DictValue dv = DictValue.of();
    		dv.setDictTypeId(dictTypeId);
			int count = dictValueService.findCount(dv);
			// 如果第一次创建成功了，则后面的就不用再进行update操作了
			if(count == 0) {
				DictType dt = DictType.of();
				dt.setDictTypeId(dictTypeId);
				dt.setHasValues("2");
				dictTypeService.update(dt, false);
			}
		}
    	return json;
	}
    
//	@RequiresPermissions(value = {"sys:dict:value:remove"})
    @PostMapping(SysRestApiConstants.REMOVE_BATCH)
    @ResponseBody
	public ResponseJson removeBath(String ids) {
		return deleteByIds(ids);
	}
    
    @PostMapping("changeSort")
    @ResponseBody // 返回json格式需要的注解
	public ResponseJson changeSort(DictDto dto) throws Exception {
    	DictValue dv = BeanConvertUtils.DtoNotNull2Entity(helper().getEntity(), dto);
    	Changer changer = BeanConvertUtils.Dto2Entity(new Changer(), dto);
		boolean flag = dictValueService.changeSort(dv, changer);
		if(flag) {
			return ResponseJson.ok();
		} else {
			return ResponseJson.notFound();
		}
	}

}
