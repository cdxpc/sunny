package com.sunny.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.constant.PlatformConstants;
import com.sunny.module.sys.dict.entity.DictType;
import com.sunny.module.sys.dict.entity.DictValue;
import com.sunny.module.sys.dict.service.DictTypeService;
import com.sunny.module.sys.dict.service.DictValueService;

import lombok.extern.slf4j.Slf4j;

/**
 * 字典操作辅助类
 * 
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 *		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月18日
 * @since 1.0.0v
 */
@Service("dict")
@Slf4j
public class DictHelper { 
	
	@Value("${sunny.cache.startUpInit}")
	private boolean startUpInit; // 是否项目启动是就加载数据到缓存中
	@Value("${sunny.cache.useCache}")
	private boolean useCache; // 是否使用缓存
	
	private DictTypeService dictTypeService = SpringContextHolder.getBean(DictTypeService.class);
	private DictValueService dictValueService = SpringContextHolder.getBean(DictValueService.class);
	
	private CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
	private final Cache<String, DictType> typeCaches = cacheManager.getCache("dict-TypeCache");
	private final Cache<String, List<DictValue>> valueCaches = cacheManager.getCache("dict-valueCache");
	
	@PostConstruct
	public void init() {
		if(startUpInit) {
			// 得到所有的字典类型
			List<DictType> types = getTypesFormDb();
			// 得到所有的字典值
			List<DictValue> values = getAllValuesFormDb();
			// 对字典值进行分组，同一类型的字典值放一个数组中，并保存在map缓存中
			if(types != null && types.size() > 0) {
				types.stream().filter(t -> PlatformConstants.STATUS_YES.equals(t.getStatus())).forEach(t -> {
					List<DictValue> dvs = new ArrayList<>();
					typeCaches.put(t.getDictTypeKey(), t);
					values.stream().filter(v -> PlatformConstants.STATUS_YES.equals(v.getStatus())).forEach(v -> {
						if(v.getDictTypeKey().equals(t.getDictTypeKey())) {
							dvs.add(v);
						}
					});
					valueCaches.put(t.getDictTypeKey(), dvs);
				});
			}
			log.info(valueCaches.toString());
		}
	}
	
	private DictType getTypeFormDb(String dictTypeKey) {
		DictType dt = DictType.of();
		dt.setDictTypeKey(dictTypeKey);
		dt.setStatus("1");
		return dictTypeService.findOne(dt);
	}
	
	private List<DictType> getTypesFormDb() {
		DictType dt = DictType.of();
		dt.setStatus("1");
		return dictTypeService.find(dt);
	}
	
	private List<DictValue> getAllValuesFormDb() {
		DictValue dv = DictValue.of();
    	dv.setStatus("1");
		return dictValueService.find(dv);
	}
	
	private List<DictValue> getValuesByTypeFormDb(String type) {
		DictValue dv = DictValue.of();
		dv.setDictTypeKey(type);
    	dv.setStatus("1");
		return dictValueService.find(dv);
	}
	
	public DictType getType(String dictTypeKey) { 
		// 先从缓存中获取看看能不能获取到，如果获取到了，直接返回数据，不用查询数据库
		DictType type = null;
		if(useCache && typeCaches.size() > 0) {
			type = typeCaches.get(dictTypeKey);
		}
		// 如果缓存中没有获取到，则从数据库中获取
		if(type == null) {
			type = getTypeFormDb(dictTypeKey);
	    	if(useCache && type != null) {
	    		typeCaches.put(type.getDictTypeKey(), type);
	    	}
		}
		return type;
	}
	
	public List<DictType> getTypes() { 
		// 先从缓存中获取看看能不能获取到，如果获取到了，直接返回数据，不用查询数据库
		Collection<DictType> types = new ArrayList<>();
		if(useCache && typeCaches.size() > 0) {
			types = typeCaches.values();
		}
		// 如果缓存中没有获取到，则从数据库中获取
		if(types == null || types.size() == 0) {
			types = getTypesFormDb();
	    	if(useCache && types != null && types.size() > 0) {
	    		for (DictType type : types) {
	    			typeCaches.put(type.getDictTypeKey(), type);
				}
	    	}
		}
		return Arrays.asList(types.toArray(new DictType[]{}));
	}
	
	public List<DictValue> getValuesByType(String type) { // 缓存可能造成数据变更无法获取最新的数据，这里需要对缓存进行管理，暂时先这么用，后期要优化
		// 先从缓存中获取看看能不能获取到，如果获取到了，直接返回数据，不用查询数据库
		List<DictValue> list = null;
		if(useCache && valueCaches.size() > 0) {
			list = valueCaches.get(type);
		}
		// 如果缓存中没有获取到，则从数据库中获取
		if(list == null || list.size() == 0) {
	    	list = getValuesByTypeFormDb(type);
	    	if(useCache && list != null && list.size() > 0) {
	    		valueCaches.put(type, list);
	    	}
		}
		return list;
	}

}
