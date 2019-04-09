package com.sunny.core;

import java.io.Serializable;

import com.sunny.core.util.json.JacksonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 响应体json对象
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class ResponseJson implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	private R result;
	private Object rows;
	private int pageNow;
	private int pageSize;
	private int pages;
	private long total;

	@Override
	public String toString() {
		return "ResponseJson : " + JacksonUtils.getInstance(false).toJson(this);
	}

	/**
	 * 响应结果 result
	 *
	 * @author cdxpc <cdxpc2018@163.com>, <br/>
	 * 		   kevin.chen <crsfyc-9@163.com>
	 * @date 2019年2月13日
	 * @since 1.0.0v
	 */
	@Data
	@AllArgsConstructor(staticName = "of")
	public static class R {

		// 响应码值
		private int code;
		// 响应结果信息
		private String message;

	}
	
	///////////////////////////
	
	public static ResponseJson ok() {
		return ResponseJson.of(R.of(200, "操作成功！"));
	}

	public static ResponseJson noData() {
		return ResponseJson.of(R.of(200, "查询成功，但查询无数据！"));
	}
	
	public static ResponseJson restOk() {
		return ResponseJson.of(R.of(200, "密码重置成功！"));
	}

	public static ResponseJson created() {
		return ResponseJson.of(R.of(201, "创建成功！"));
	}

	public static ResponseJson captchaInvalid() {
		return ResponseJson.of(R.of(401, "验证码已失效！"));
	}

	public static ResponseJson captchaError() {
		return ResponseJson.of(R.of(401, "验证码输入有误！"));
	}

	public static ResponseJson authcPasswordRetryOut() {
		return ResponseJson.of(R.of(401, "密码输入次数超过5次，10分钟之内账户无法登陆！"));
	}
	
	public static ResponseJson authcFail() {
		return ResponseJson.of(R.of(401, "用户名或密码输入有误！"));
	}

	public static ResponseJson accountLocked() {
		return ResponseJson.of(R.of(401, "账号已被锁定，请联系管理员！"));
	}
	
	public static ResponseJson noLogin() {
		return ResponseJson.of(R.of(401, "未登录或登录超时，请重新登录！"));
	}

	public static ResponseJson noPerm() {
		return ResponseJson.of(R.of(401, "操作权限受限！"));
	}

	public static ResponseJson notFound() {
		return ResponseJson.of(R.of(404, "请求路径有误！"));
	}

	public static ResponseJson serverError() {
		return ResponseJson.of(R.of(500, "请求出现异常！请稍后访问..."));
	}
	
}

