package com.sunny.module.sys.org.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.org.dto.OrgDto;
import com.sunny.module.sys.org.entity.Org;
import com.sunny.module.sys.org.service.OrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_ORG)
public class OrgController extends AbstractInternalController<Org, OrgDto> {
	
	private OrgService orgService = SpringContextHolder.getBean(OrgService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);

	@Override
	protected ControllerHelper<Org, OrgDto> helper() {
		return ControllerHelper.all(orgService, Org.of(), new OrgDto(), SysRestApiConstants.REST_API_ORG_LIST);
	}

//	@RequiresPermissions("sys:org:view")
	public String list() {
		return toView();
	}
	
//	@RequiresPermissions("sys:org:data")
	@PostMapping(SysRestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(OrgDto dto) { 	// 数据列表(tree-table)
		return findListNoPage(dto);
	}
	
//	@RequiresPermissions("sys:org:add")
	@GetMapping(SysRestApiConstants.ADD)
	public String add(Model model) {	// 新增操作
		setModel(null, model, false);
		return SysRestApiConstants.REST_API_ORG_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:org:details", "sys:org:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.ADD_SUB + "/{id}")
	public String addSub(@PathVariable String id, Model model) { // 新增子级操作
		setModel(id, model, false);
		return SysRestApiConstants.REST_API_ORG_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:org:details", "sys:org:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.EDIT + "/{orgId}")
	public String edit(@PathVariable String orgId, Model model) { // 编辑操作
		setModel(orgId, model, true);
		return SysRestApiConstants.REST_API_ORG_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:org:save"})
	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(OrgDto dto) {
		if(StringUtils.isEmpty(dto.getOrgId())) {  // 保存操作
			dto.setOrgId(UuidUtils.uuid());
			return create(dto);
		} 
		return update(dto.getOrgId(), dto);
	}
	
//	@RequiresPermissions(value = {"sys:org:remove"})
    @PostMapping(SysRestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String id) {  // 删除操作
		return deleteById(id);
	}
    
//	@RequiresPermissions(value = {"sys:org:remove"})
    @PostMapping(SysRestApiConstants.REMOVE_BATCH)
    @ResponseBody
	public ResponseJson removeBatch(String ids) {  // 删除操作
		return deleteByIds(ids);
	}
    
//	@RequiresPermissions(value = {"sys:org:edit"})
    @PostMapping(SysRestApiConstants.MODIFY_STATUS)
    @ResponseBody
	public ResponseJson modifyStatus(OrgDto dto) { // 启用、禁用操作
    	return update(dto.getOrgId(), dto);
	}
    
    private void setModel(String id, Model model, boolean isEdit) {
    	OrgDto org = null;
    	if(StringUtils.isNotEmpty(id)) {
    		if(isEdit) {
				ResponseJson json = findById(id);
				org = (OrgDto) json.getRows();
				if(org != null) {
					if(org.getParentId() != null) {
						Org parent = orgService.findById(org.getParentId());
						if(parent != null) {
							org.setParentId(parent.getOrgId());
							org.setParentName(parent.getOrgName());
						} 
					} else {
						org.setParentName("无");
					}
				}
    		} else {
    			org = helper().getDto();
    			Org parent = orgService.findById(id);
    			if(parent != null) {
    				org.setParentId(parent.getOrgId());
    				org.setParentName(parent.getOrgName());
    			}
    			org.setTreeNode("2");
    		}
		} else {
			if(!isEdit) {
				org = helper().getDto();
				Org root = orgService.getRoot("g001");
				if(root != null) {
					org.setTreeNode("2");
					org.setParentId(root.getOrgId());
					org.setParentName(root.getOrgName());
				}
			}
		}
    	
    	model.addAttribute("org", org);
    	model.addAttribute("orgMainCategroys", dictHelper.getValuesByType("dict_org_main_categroy"));
    	model.addAttribute("treeNodes", dictHelper.getValuesByType("dict_tree_node"));
    }
    
    @GetMapping(SysRestApiConstants.TREE_VIEW + "/{orgId}")
	public String treeView(@PathVariable String orgId, Model model) { // 展示树
    	model.addAttribute("org", orgService.findById(orgId));
    	return SysRestApiConstants.REST_API_ORG_TREE;
	}
    
    @GetMapping(SysRestApiConstants.TREE_DATA)
    @ResponseBody
	public List<Map<String, Object>> treeData() { // 展示树
    	//model.addAttribute("org", orgService.findById(orgId));
    	return orgService.getTreeData();
	}

}
