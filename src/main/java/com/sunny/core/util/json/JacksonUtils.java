package com.sunny.core.util.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private JacksonUtils(@NonNull Include deInclude, Include ...includes) {
		mapper.setSerializationInclusion(deInclude);
		
		if(ArrayUtils.isNotEmpty(includes)) {
			for (Include include : includes) {
				mapper.setSerializationInclusion(include);
			}
		}
		
		// 序列化初始化
		initSerializationConfig();
		
		// 反序列化初始化
		initDeserializationConfig();
		
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
		      .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
		      .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
		      .setTimeZone(TimeZone.getDefault());
	}

	private void initDeserializationConfig() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
		      .registerModules(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>() {
						@Override
						public void serialize(String value, JsonGenerator gen, SerializerProvider provider)
								throws IOException {
							// 字符串处理
							gen.writeString(StringEscapeUtils.escapeHtml4(value));
						}
					})
		       )
		      .getSerializerProvider()
		      .setNullValueSerializer(new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator gen, SerializerProvider provider)
							throws IOException {
						// 空对象使用""代替
						gen.writeString("");
					}
				 }
		      );
	}

	private void initSerializationConfig() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		      .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}
	
	// 有non这个设置，空字符串，空对象，空集合都不会序列化
	public static JacksonUtils getInstance(boolean non, Include ...includes) {
		if(non) {
			return new JacksonUtils(JsonInclude.Include.NON_DEFAULT, includes);
		}
		return new JacksonUtils(JsonInclude.Include.NON_EMPTY, includes);
	}
	
	/**
	 * JavaBean 对象序列化 Json 字符串
	 * @param value
	 * @return
	 */
	public String toJson(Object value) {
		try {
			return mapper//.writerWithDefaultPrettyPrinter()
					     .writeValueAsString(value);
		} catch (Exception e) {
			log.error("Write java bean to json string error: " + value, e);
		}
		return null;
	}
	
	/**
	 * Json 字符串转 JavaBean 对象
	 * @param json
	 * @param cls
	 * @return
	 */
	public <T> T toBean(String json, Class<T> cls) {
		if(StringUtils.isEmpty(json)) return null;
		try {
			return mapper.readValue(json, cls);
		} catch (Exception e) {
			log.error("Parse json string to java bean error: " + json, e);
		}
		return null;
	}
	
	// to jsonp
	public String toJsonp(String func, Object value) {
		return toJson(new JSONPObject(func, value));
	}
}

