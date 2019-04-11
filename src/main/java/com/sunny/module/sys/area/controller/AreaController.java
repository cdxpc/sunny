package com.sunny.module.sys.area.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.area.dto.AreaDto;
import com.sunny.module.sys.area.entity.Area;
import com.sunny.module.sys.area.service.AreaService;

//@Controller
//@RequestMapping(SysRestApiConstants.REST_API_AREA)
public class AreaController extends AbstractInternalController<Area, AreaDto> {
	
	private AreaService areaService = SpringContextHolder.getBean(AreaService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);

	@Override
	protected ControllerHelper<Area, AreaDto> helper() {
		return ControllerHelper.all(areaService, Area.of(), new AreaDto(), SysRestApiConstants.REST_API_AREA_LIST);
	}

	//	@RequiresPermissions("sys:area:view")
	public String list() {
		return toView();
	}

	//	@RequiresPermissions("sys:area:data")
	@PostMapping(SysRestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(AreaDto dto) { 	// 数据列表(tree-table)
		return findListNoPage(dto);
	}

	//	@RequiresPermissions("sys:area:add")
	@GetMapping(SysRestApiConstants.ADD)
	public String add(Model model) {	// 新增操作
		setModel(null, model, false);
		return SysRestApiConstants.REST_API_AREA_FORM;
	}

	//	@RequiresPermissions(value = {"sys:area:details", "sys:area:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.ADD_SUB + "/{id}")
	public String addSub(@PathVariable String id, Model model) { // 新增子级操作
		setModel(id, model, false);
		return SysRestApiConstants.REST_API_AREA_FORM;
	}

	//	@RequiresPermissions(value = {"sys:area:details", "sys:area:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) { // 编辑操作
		setModel(id, model, true);
		return SysRestApiConstants.REST_API_AREA_FORM;
	}

	//	@RequiresPermissions(value = {"sys:area:save"})
	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(AreaDto dto) {
		if(StringUtils.isEmpty(dto.getAreaId())) {  // 保存操作
			dto.setAreaId(UuidUtils.uuid());
			return create(dto);
		}
		return update(dto.getAreaId(), dto);
	}

	//	@RequiresPermissions(value = {"sys:area:remove"})
	@PostMapping(SysRestApiConstants.REMOVE)
	@ResponseBody
	public ResponseJson remove(String id) {  // 删除操作
		return deleteById(id);
	}

	//	@RequiresPermissions(value = {"sys:area:remove"})
	@PostMapping(SysRestApiConstants.REMOVE_BATCH)
	@ResponseBody
	public ResponseJson removeBatch(String ids) {  // 删除操作
		return deleteByIds(ids);
	}

	//	@RequiresPermissions(value = {"sys:area:edit"})
	@PostMapping(SysRestApiConstants.MODIFY_STATUS)
	@ResponseBody
	public ResponseJson modifyStatus(AreaDto dto) { // 启用、禁用操作
		return update(dto.getAreaId(), dto);
	}

	private void setModel(String id, Model model, boolean isEdit) {
		AreaDto area = null;
		if(StringUtils.isNotEmpty(id)) {
			if(isEdit) {
				ResponseJson json = findById(id);
				area = (AreaDto) json.getRows();
				if(area != null && area.getParentId() != null) {
					Area parent = areaService.findById(area.getParentId());
					if(parent != null) {
						area.setParentId(parent.getAreaId());
						area.setParentName(parent.getAreaName());
					} else {
						area.setParentName("无");
					}
				}
			} else {
				area = helper().getDto();
				Area parent = areaService.findById(id);
				if(parent != null) {
					area.setParentId(parent.getAreaId());
					area.setParentName(parent.getAreaName());
				}
				area.setTreeNode("2");
			}
		} else {
			if(!isEdit) {
				area = helper().getDto();
				Area root = areaService.getRoot();
				if(root != null) {
					area.setTreeNode("2");
					area.setParentId(root.getAreaId());
					area.setParentName(root.getAreaName());
				}
			}
		}

		model.addAttribute("area", area);
		model.addAttribute("areaTypes", dictHelper.getValuesByType("dict_area_type"));
    	model.addAttribute("treeNodes", dictHelper.getValuesByType("dict_tree_node"));
	}

	@GetMapping(SysRestApiConstants.TREE_VIEW + "/{areaId}")
	public String treeView(@PathVariable String areaId, Model model) { // 展示树
		model.addAttribute("area", areaService.findById(areaId));
		return SysRestApiConstants.REST_API_AREA_TREE;
	}

	@GetMapping(SysRestApiConstants.TREE_DATA)
	@ResponseBody
	public ResponseJson treeData(@PathVariable String areaId, Model model) { // 展示树
		model.addAttribute("area", areaService.findById(areaId));
		return null;
	}

}
