package com.sunny.core.base.controller;

import com.github.pagehelper.Page;
import com.sunny.core.ResponseJson;
import com.sunny.core.ServletHelper;
import com.sunny.core.constant.PlatformConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 访问控制层基类
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 *
 * @param <E>
 */
@Slf4j
public class BaseController<E> {
	
	// XXX 是否运行插入或更新空值到数据库，这里需要改为配置来设置
	static final boolean ALLOW_NULL = false;

	@InitBinder
	void initBinder(WebDataBinder binder) {

		// 注册字符串类型
		binder.registerCustomEditor(String.class, StringEditor.of());
		// 注册duble类型
		binder.registerCustomEditor(Double.class, DoubleEditor.of());
		// 注册日期类型
		SimpleDateFormat dateFormat = new SimpleDateFormat(PlatformConstants.DATE_FORMAT);
        dateFormat.setLenient(false);
        // 使用spring自定义的日期编辑器
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

	@NoArgsConstructor(staticName = "of")
	static class StringEditor extends PropertyEditorSupport {
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			setValue(text == null ? "" : StringEscapeUtils.escapeHtml4(text.trim()));
		}

		@Override
		public String getAsText() {
			return getValue() != null ? getValue().toString() : "";
		}
	}
	
	@NoArgsConstructor(staticName = "of")
	static class DoubleEditor extends PropertyEditorSupport {
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			setValue(NumberUtils.toDouble(text));
		}

		@Override
		public String getAsText() {
			return getValue().toString();
		}
	}
	
	/**
	 * 	发生成功的响应体给客户端
	 * @return ResponseJson
	 */
	protected ResponseJson sendSuccess(ResponseJson responseJson){
		if(log.isInfoEnabled()) {
			log.info("返回客户端的数据为：" + responseJson);
		}
		return responseJson;
	}
	
	/**
	 *	系统出现异常情况时，统一返回错误提示
	 * @return ResponseJson
	 */
	protected ResponseJson sendError(Exception e) {
		ResponseJson error = ResponseJson.serverError();
		if(log.isInfoEnabled()) {
			log.info("返回客户端的数据为：" + error);
		}
		log.error(error.getResult().getMessage(), e);
		return error;
	}
	
	// 从请求中获取分页、排序信息
	protected Page<E> initPage() {
		Page<E> page = new Page<>();
		page.setPageNum(ServletHelper.getIntParameter(PlatformConstants.PAGE_NUM));
		page.setPageSize(ServletHelper.getIntParameter(PlatformConstants.PAGE_SIZE));
		page.setOrderBy(ServletHelper.getParameter(PlatformConstants.ORDER_BY_COLUMN));
		page.setOrderByOnly(ServletHelper.getBoolParameter(PlatformConstants.IS_ASC));
		return page;
	}

}
