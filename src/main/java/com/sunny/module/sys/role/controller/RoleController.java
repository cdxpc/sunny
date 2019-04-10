package com.sunny.module.sys.role.controller;

import com.github.pagehelper.Page;
import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.persistence.Changer;
import com.sunny.core.util.BeanConvertUtils;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.relation.dto.RelationDto;
import com.sunny.module.sys.role.dto.RoleDto;
import com.sunny.module.sys.role.entity.Role;
import com.sunny.module.sys.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_ROLE)
public class RoleController extends AbstractInternalController<Role, RoleDto> {
	
	private RoleService roleService = SpringContextHolder.getBean(RoleService.class);

	@Override
	protected ControllerHelper<Role, RoleDto> helper() {
		return ControllerHelper.all(roleService, Role.of(), new RoleDto(), SysRestApiConstants.REST_API_ROLE_LIST);
	}
	
	public String list() {
		return toView();
	}
	
//	@RequiresPermissions("sys:role:data")
	@PostMapping(SysRestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(RoleDto dto) { // 数据列表
		return findListWithPage(dto);
	}
	
//	@RequiresPermissions("sys:role:add")
	@GetMapping(SysRestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("role", helper().getDto());
		return SysRestApiConstants.REST_API_ROLE_FORM;
	}

//	@RequiresPermissions(value = {"sys:role:details", "sys:role:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("role", json.getRows());
		}
		return SysRestApiConstants.REST_API_ROLE_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:role:save"})
	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(RoleDto dto) {
		if(StringUtils.isEmpty(dto.getRoleId())) {
			dto.setRoleId(UuidUtils.uuid());
			return create(dto);
		} 
		return update(dto.getRoleId(), dto);
	}
	
//	@RequiresPermissions(value = {"sys:role:remove"})
    @PostMapping(SysRestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String id) {
		return deleteById(id);
	}
    
//	@RequiresPermissions(value = {"sys:role:remove"})
    @PostMapping(SysRestApiConstants.REMOVE_BATCH)
    @ResponseBody
	public ResponseJson removeBath(String ids) {
		return deleteByIds(ids);
	}
    
    @PostMapping("changeSort")
    @ResponseBody // 返回json格式需要的注解
	public ResponseJson changeSort(RoleDto dto) throws Exception {
    	Role r = BeanConvertUtils.DtoNotNull2Entity(helper().getEntity(), dto);
    	Changer changer = BeanConvertUtils.Dto2Entity(new Changer(), dto);
		boolean flag = roleService.changeSort(r, changer);
		if(flag) {
			return ResponseJson.ok();
		} else {
			return ResponseJson.notFound();
		}
	}

    @PostMapping("getRoleInUser")
    @ResponseBody
   	public ResponseJson getRoleInUser(RelationDto rd) {
    	try {
    		Page<Role> page = initPage();
    		Map<String, Object> map = roleService.getRoleInUser(rd, page);
    		return sendSuccess(get(page, map));
		} catch (Exception e) {
			return ResponseJson.notFound();
		}
    }

	@SuppressWarnings("unchecked")
	private ResponseJson get(Page<Role> page, Map<String, Object> map)
			throws Exception {
		ResponseJson responseJson = ResponseJson.noData();
		List<Role> roles = Collections.emptyList();
		int total = (int) map.get("total");
		if(total > 0) {
			responseJson = ResponseJson.ok();
			roles = (List<Role>) map.get("dataList");
			responseJson.setPageNow(page.getPageNum());
			int pageSize = page.getPageSize();
			responseJson.setPageSize(pageSize);
			responseJson.setPages(total / pageSize + ((total % pageSize == 0) ? 0 : 1));
			responseJson.setTotal(total);
		}
		responseJson.setRows(BeanConvertUtils.Entitys2Dtos(new ArrayList<RoleDto>(), RoleDto.class, roles));
		return responseJson;
	}
    
    @PostMapping("getRoleNotInUser")
    @ResponseBody
   	public ResponseJson getRoleNotInUser(RelationDto rd) {
    	try {
    		Page<Role> page = initPage();
    		Map<String, Object> map = roleService.getRoleNotInUser(rd, page);
    		return get(page, map);
		} catch (Exception e) {
			return ResponseJson.notFound();
		}
    }
    
    @GetMapping("bindOrUnbind")
    @ResponseBody
    public ResponseJson bindOrUnbind(String roleId, String userId, String type) {
    	ResponseJson json = ResponseJson.notFound();
    	try {
    		boolean flag = roleService.bindOrUnbind(roleId, userId, type);
    		if(flag) {
    			json = ResponseJson.ok();
    		}
		} catch (Exception e) {
		}
    	return json;
    }
    
}
