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
import com.sunny.core.persistence.DataScope;
import com.sunny.core.util.BeanConvertUtils;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.dict.dto.DictDto;
import com.sunny.module.sys.dict.entity.DictType;
import com.sunny.module.sys.dict.service.DictTypeService;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_DICT_TYPE)
public class DictTypeController extends AbstractInternalController<DictType, DictDto>{
	
	private DictTypeService dictTypeService = SpringContextHolder.getBean(DictTypeService.class);

	@Override
	protected ControllerHelper<DictType, DictDto> helper() {
		return ControllerHelper.all(dictTypeService, DictType.of(), new DictDto(), SysRestApiConstants.REST_API_DICT_LIST);
	}

//	@RequiresPermissions("sys:dict:type:view")
	public String list() {	// 列表页
		return toView();
	}

//	@RequiresPermissions("sys:dict:type:data")
	@PostMapping(SysRestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(DictDto dto) { // 数据列表
		return findListWithPage(dto);
	}
	
//	@RequiresPermissions("sys:dict:type:add")
	@GetMapping(SysRestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("dictType", helper().getDto());
		return SysRestApiConstants.REST_API_DICT_TYPE_FORM;
	}

//	@RequiresPermissions(value = {"sys:dict:type:details", "sys:dict:type:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("dictType", json.getRows());
		}
		return SysRestApiConstants.REST_API_DICT_TYPE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:dict:type:save"})
	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(DictDto dto) {
		if(StringUtils.isEmpty(dto.getDictTypeId())) {
			dto.setDictTypeId(UuidUtils.uuid());
			return create(dto);
		} 
		return update(dto.getDictTypeId(), dto);
	}
	
//	@RequiresPermissions(value = {"sys:dict:type:remove"})
    @PostMapping(SysRestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String id) {
		return deleteById(id);
	}
    
//	@RequiresPermissions(value = {"sys:dict:type:remove"})
    @PostMapping(SysRestApiConstants.REMOVE_BATCH)
    @ResponseBody
	public ResponseJson removeBath(String ids) {
		return deleteByIds(ids);
	}
    
    @PostMapping("changeSort")
    @ResponseBody // 返回json格式需要的注解
	public ResponseJson changeSort(DictDto dto) throws Exception {
    	DictType dt = BeanConvertUtils.DtoNotNull2Entity(helper().getEntity(), dto);
    	Changer changer = BeanConvertUtils.Dto2Entity(new Changer(), dto);
		boolean flag = dictTypeService.changeSort(dt, changer);
		if(flag) {
			return ResponseJson.ok();
		} else {
			return ResponseJson.notFound();
		}
	}
    
    @PostMapping("checkExist")// 唯一性字段校验
    @ResponseBody
    public Long checkExist(DictDto dto) { // status 是否需要为有效的数据？  这个可以定制化
    	// 编辑的时候需要刨除自己
    	if(StringUtils.isNotEmpty(dto.getDictTypeId())) {
    		dto.pre(DataScope.R);
    		return dictTypeService.findCountExSelf(dto);
    	}
    	// 新增的时候全表查询
    	return findCount(dto).getTotal();
    }
    
    // TODO 1、模糊查询       

}
