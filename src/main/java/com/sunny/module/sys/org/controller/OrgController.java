package com.sunny.module.sys.org.controller;

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
import com.sunny.module.sys.org.dto.OrgDto;
import com.sunny.module.sys.org.entity.Org;
import com.sunny.module.sys.org.service.OrgService;

@Controller
@RequestMapping(RestApiConstants.REST_API_ORG)
public class OrgController extends AbstractInternalController<Org, OrgDto> {
	
	@Autowired
	private OrgService orgService;

	@Override
	protected ControllerHelper<Org, OrgDto> helper() {
		return ControllerHelper.all(orgService, Org.of(), new OrgDto(), RestApiConstants.REST_API_ORG_LIST);
	}

//	@RequiresPermissions("sys:org:view")
	protected String list() {
		return toView();
	}
	
//	@RequiresPermissions("sys:org:data")
	@PostMapping(RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(OrgDto dto) { 	// 数据列表(tree-table)
		return findListNoPage(dto);
	}
	
//	@RequiresPermissions("sys:org:data")
	@PostMapping(RestApiConstants.TREE)
	@ResponseBody
	public ResponseJson tree(OrgDto dto) { 	// 树(tree)
		return findListWithPage(dto);
	}
	
//	@RequiresPermissions("sys:org:add")
	@GetMapping(RestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("org", helper().getDto());
		return RestApiConstants.REST_API_ORG_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:org:details", "sys:org:edit"}, logical = Logical.OR)
	@GetMapping(RestApiConstants.EDIT + "/{orgId}")
	public String edit(@PathVariable String orgId, Model model) {
		if(StringUtils.isNotEmpty(orgId)) {
			ResponseJson json = findById(orgId);
			model.addAttribute("org", json.getRows());
		}
		return RestApiConstants.REST_API_ORG_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:org:save"})
	@PostMapping(RestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(OrgDto dto) {
		if(StringUtils.isEmpty(dto.getOrgId())) {
			dto.setOrgId(UuidUtils.uuid());
			return create(dto);
		} 
		return update(dto.getOrgId(), dto);
	}
	
//	@RequiresPermissions(value = {"sys:org:remove"})
    @PostMapping(RestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String ids) {
		return deleteByIds(ids);
	}

}
