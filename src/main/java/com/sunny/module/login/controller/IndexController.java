package com.sunny.module.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sunny.core.auth.ShiroHelper;
import com.sunny.core.base.controller.BaseController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.BeanConvertUtils;
import com.sunny.module.sys.menu.MenuTree;
import com.sunny.module.sys.menu.dto.MenuDto;
import com.sunny.module.sys.menu.entity.Menu;
import com.sunny.module.sys.menu.service.MenuService;
import com.sunny.module.sys.user.dto.UserDto;
import com.sunny.module.sys.user.entity.User;
import com.sunny.module.sys.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController extends BaseController<Object> {
	
	@Autowired
	private UserService userService;
//	@Autowired
//	private OrgService orgService;
	@Autowired
	private MenuService menuService;
	
	// 系统首页
    @GetMapping(RestApiConstants.INDEX)
    public String index(Model model) {
    	// 获取当前的登陆用户
    	String userId = ShiroHelper.getLoginUserId(); // 这里直接还有User，也可以转为UserDto
    	// 获取用户信息
    	User user = userService.findById(userId);
    	// 获取用户机构信息
//    	Org org = orgService.findOrgByUserId(userId);
    	
    	UserDto ud = new UserDto();
    	
    	try {
    		ud = BeanConvertUtils.Entity2Dto(ud, user);
//    		ud.setOrgName(org.getOrgName());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    	
    	// 通过用户获取菜单
    	List<Menu> menus = menuService.findMenusByUserId(userId);
    	List<MenuDto> menuDtos = new ArrayList<>();
		try {
			menuDtos = BeanConvertUtils.Entitys2Dtos(menuDtos, MenuDto.class, menus);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		menuDtos = MenuTree.getChildPerms(menuDtos, "0");
//		System.out.println(JacksonUtils.getInstance(false).toJson(menuDtos));
    	model.addAttribute("user", ud);
    	model.addAttribute("menus", menuDtos);
    	model.addAttribute("copyrightYear", "2019");
    	return RestApiConstants.DASHBOARD + RestApiConstants.INDEX;
    }
    
    // 欢迎页
    @GetMapping(RestApiConstants.WELCOME)
    public String welcome(Model model) {
    	model.addAttribute("version", "1.0.0");
//    	int i = 1/0; // 这个测试 500错误是否会到500.html页面
    	return RestApiConstants.DASHBOARD + RestApiConstants.WELCOME;
    }

}
