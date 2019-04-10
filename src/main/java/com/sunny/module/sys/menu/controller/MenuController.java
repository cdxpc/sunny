package com.sunny.module.sys.menu.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.menu.dto.MenuDto;
import com.sunny.module.sys.menu.entity.Menu;
import com.sunny.module.sys.menu.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_MENU)
public class MenuController extends AbstractInternalController<Menu, MenuDto>{
	
	private MenuService menuService = SpringContextHolder.getBean(MenuService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);

	@Override
	protected ControllerHelper<Menu, MenuDto> helper() {
		return ControllerHelper.all(menuService, Menu.of(), new MenuDto(), SysRestApiConstants.REST_API_MENU_LIST);
	}
	
	public String list() {
		return toView();
	}
	
	@PostMapping(SysRestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(MenuDto dto, Model model) {
		return findListNoPage(dto);
	}
	
//	@RequiresPermissions("sys:menu:add")
	@GetMapping(SysRestApiConstants.ADD)
	public String add(Model model) {	// 新增操作
		setModel(null, model, false);
		return SysRestApiConstants.REST_API_MENU_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:menu:details", "sys:menu:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.ADD_SUB + "/{id}")
	public String addSub(@PathVariable String id, Model model) { // 新增子级操作
		setModel(id, model, false);
		return SysRestApiConstants.REST_API_MENU_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:menu:details", "sys:menu:edit"}, logical = Logical.OR)
	@GetMapping(SysRestApiConstants.EDIT + "/{menuId}")
	public String edit(@PathVariable String menuId, Model model) { // 编辑操作
		setModel(menuId, model, true);
		return SysRestApiConstants.REST_API_MENU_FORM;
	}
	
//	@RequiresPermissions(value = {"sys:menu:save"})
	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(MenuDto dto) {
		if(StringUtils.isEmpty(dto.getMenuId())) {  // 保存操作
			dto.setMenuId(UuidUtils.uuid());
			return create(dto);
		} 
		return update(dto.getMenuId(), dto);
	}
	
//	@RequiresPermissions(value = {"sys:menu:remove"})
    @PostMapping(SysRestApiConstants.REMOVE)
    @ResponseBody
	public ResponseJson remove(String id) {  // 删除操作
		return deleteById(id);
	}
    
//	@RequiresPermissions(value = {"sys:menu:remove"})
    @PostMapping(SysRestApiConstants.REMOVE_BATCH)
    @ResponseBody
	public ResponseJson removeBatch(String ids) {  // 删除操作
		return deleteByIds(ids);
	}
    
//	@RequiresPermissions(value = {"sys:menu:edit"})
    @PostMapping(SysRestApiConstants.MODIFY_STATUS)
    @ResponseBody
	public ResponseJson modifyStatus(MenuDto dto) { // 启用、禁用操作
    	return update(dto.getMenuId(), dto);
	}
    
    private void setModel(String id, Model model, boolean isEdit) {
    	MenuDto menu = null;
    	if(StringUtils.isNotEmpty(id)) {
    		if(isEdit) {
				ResponseJson json = findById(id);
				menu = (MenuDto) json.getRows();
				if(menu != null) {
					if(menu.getParentId() != null) {
						Menu parent = menuService.findById(menu.getParentId());
						if(parent != null) {
							menu.setParentId(parent.getMenuId());
							menu.setParentName(parent.getMenuName());
						} 
					} else {
						menu.setParentName("无");
					}
				}
    		} else {
    			menu = helper().getDto();
    			Menu parent = menuService.findById(id);
    			if(parent != null) {
    				menu.setParentId(parent.getMenuId());
    				menu.setParentName(parent.getMenuName());
    			}
    			menu.setTreeNode("2");
    			menu.setVisible("1");
    		}
		} else {
			if(!isEdit) {
				menu = helper().getDto();
				Menu root = menuService.getRoot("1");
				if(root != null) {
					menu.setTreeNode("2");
					menu.setVisible("1");
					menu.setMenuType("R");
					menu.setParentId(root.getMenuId());
					menu.setParentName(root.getMenuName());
				}
			}
		}
    	
    	model.addAttribute("menu", menu);
    	model.addAttribute("menuTypes", dictHelper.getValuesByType("dict_menu_type"));
    	model.addAttribute("visibles", dictHelper.getValuesByType("dict_menu_visible"));
    	model.addAttribute("treeNodes", dictHelper.getValuesByType("dict_tree_node"));
    }
    
    @GetMapping(SysRestApiConstants.TREE_VIEW + "/{menuId}")
	public String treeView(@PathVariable String menuId, Model model) { // 展示树
    	model.addAttribute("menu", menuService.findById(menuId));
    	return SysRestApiConstants.REST_API_MENU_TREE;
	}
    
    @GetMapping(SysRestApiConstants.TREE_DATA)
    @ResponseBody
	public List<Map<String, Object>> treeData() { // 展示树
    	//model.addAttribute("menu", menuService.findById(menuId));
    	return menuService.getTreeData();
	}

}
