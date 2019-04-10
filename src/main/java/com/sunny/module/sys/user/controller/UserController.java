package com.sunny.module.sys.user.controller;

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
import com.sunny.core.util.StringUtils;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.DictHelper;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.user.dto.UserDto;
import com.sunny.module.sys.user.entity.User;
import com.sunny.module.sys.user.service.UserService;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_USER)
public class UserController extends AbstractInternalController<User, UserDto> {
	
	private UserService userService = SpringContextHolder.getBean(UserService.class);
	private DictHelper dictHelper = SpringContextHolder.getBean(DictHelper.class);

	@Override
	protected ControllerHelper<User, UserDto> helper() {
		return ControllerHelper.all(userService, User.of(), new UserDto(), SysRestApiConstants.REST_API_USER_LIST);
	}

	public String list(){
		return toView();
	}

	@PostMapping(SysRestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(UserDto dto) {
		return findListWithPage(dto);
	}

	@GetMapping(SysRestApiConstants.ADD)
	public String add(Model model) {
		model.addAttribute("user", helper().getDto());
		dict(model);
		return SysRestApiConstants.REST_API_USER_FORM;
	}

	@GetMapping(SysRestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("user", json.getRows());
		}
		dict(model);
		return SysRestApiConstants.REST_API_USER_FORM;
	}
	
	private void dict(Model model) {
		model.addAttribute("userTypes", dictHelper.getValuesByType("dict_user_type"));
    	model.addAttribute("genders", dictHelper.getValuesByType("dict_user_gender"));
	}

	@PostMapping(SysRestApiConstants.SAVE)
	@ResponseBody
	public ResponseJson save(UserDto dto) {
		if(StringUtils.isEmpty(dto.getUserId())) {
			dto.setUserId(UuidUtils.uuid());
			return create(dto);
		}
		return update(dto.getUserId(), dto);
	}

	@PostMapping(SysRestApiConstants.REMOVE)
	@ResponseBody
	public ResponseJson remove(String id) {
		return deleteById(id);
	}

	@PostMapping(SysRestApiConstants.REMOVE_BATCH)
	@ResponseBody
	public ResponseJson removeBatch(String ids) {
		return deleteByIds(ids);
	}
	
//	@RequiresPermissions(value = {"sys:user:edit"})
    @PostMapping(SysRestApiConstants.MODIFY_STATUS)
    @ResponseBody
	public ResponseJson modifyStatus(UserDto dto) { // 启用、禁用操作
    	/**
    	 *  XXX 待完成
    	 *  禁用启用操作需要遵循如下规则：
    	 *  	1、超级管理员用户不允许被禁用
    	 *  	2、该操作必须由管理员权限才行
    	 *  	3、当前登录用户不能操作禁用、启用自己
    	 */
    	return update(dto.getUserId(), dto);
	}
    
    @GetMapping("bindRole/{userId}")
	public String bindRole(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId);
		return "sys/user/bindRole";
	}
    
    @PostMapping("restPwd")
    @ResponseBody
    public ResponseJson restPwd(String userId) {
    	return userService.restPwd(userId);
    }
    
}
