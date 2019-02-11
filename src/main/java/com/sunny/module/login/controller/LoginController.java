package com.sunny.module.login.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sunny.core.ResponseJson;
import com.sunny.core.ServletHelper;
import com.sunny.core.auth.ShiroHelper;
import com.sunny.core.base.BaseController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.AjaxUtils;
import com.sunny.core.util.json.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController extends BaseController<Object> {
	
	@Autowired
	private Producer mathKaptcha;
	@Autowired
	private Producer charKaptcha;
	
	@GetMapping(RestApiConstants.CAPTCHA)
	public void kaptcha(HttpServletResponse response) {
		ServletOutputStream out = null;
		try {
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setContentType("image/jpeg");
			
			String type = ServletHelper.getParameter("type");
			String capStr = null;
			String code = null;
			BufferedImage bi = null;
			if("math".equalsIgnoreCase(type)) {
				// 算术算法方式，生成算术验证码
				String capText = mathKaptcha.createText();
				capStr = capText.substring(0, capText.lastIndexOf("@"));
				code = capText.substring(capText.lastIndexOf("@") + 1);
				bi = mathKaptcha.createImage(capStr);
			} else if("char".equalsIgnoreCase(type)) {
				// 字符方式，生成字符验证码
				capStr = code = charKaptcha.createText();
				bi = charKaptcha.createImage(capStr);
			}
			ShiroHelper.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, code);
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
		} catch (Exception e) {
			log.error("验证码生成出现异常，异常信息为：" + e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				log.error("[ServletOutputStream] close exception: " + e.getMessage());
			}
		}
	}
	
	// 访问登录页面
	@GetMapping(RestApiConstants.LOGIN)
	public String toLogin() {
		if(AjaxUtils.isAjaxRequest()) {
			// 如果是Ajax请求，返回未登录的Json字符串。
			return ServletHelper.renderString(JacksonUtils.getInstance(false).toJson(ResponseJson.noLogin()));
		}
		// 非Ajax请求，直接到登陆页面
		return RestApiConstants.LOGIN;
	}
	
	// 登录操作
//	@Log("登录")  // aop 切面记录登录日志
	@PostMapping(RestApiConstants.LOGIN)
	@ResponseBody
	public ResponseJson login(String loginName, String password, String captcha, Boolean rememberMe) {
		return ShiroHelper.login(loginName, password, captcha, rememberMe);
	}
	
	// 退出操作，返回到登录页面
//	@Log("退出")  // aop 切面记录退出日志
	@GetMapping(RestApiConstants.LOGOUT)
	public String logout() {
		// 记录用户退出日志
		ShiroHelper.logout();
		return RestApiConstants.LOGIN;
	}

}
