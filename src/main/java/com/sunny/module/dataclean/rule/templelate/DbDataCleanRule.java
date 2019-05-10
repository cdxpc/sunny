package com.sunny.module.dataclean.rule.templelate;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.annotation.RuleClass;
import com.sunny.core.annotation.RuleMethod;
import com.sunny.core.datasource.dynamic.DataSourceContextHolder;
import com.sunny.core.datasource.model.DataSourceBean;
import com.sunny.core.util.json.JacksonUtils;
import com.sunny.module.common.mapper.CommonMapper;
import com.sunny.module.dataclean.ParamsModel;
import com.sunny.module.dataclean.TableAndColumn;
import com.sunny.module.dataclean.WhereColumn;
import com.sunny.module.dataclean.rule.RuleStartupInitHelper;
import com.sunny.module.db.database.entity.DataBase;
import com.sunny.module.db.database.mapper.DataBaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述： 数据库数据清理规则定义类，该类会在程序启动是通过反射来构建一些规则数据
 *          {@link RuleStartupInitHelper}
 *          {@link RuleClass }
 *          {@link RuleClass }
 *
 * @author keivn.chen <crsfyc-9@163.com>
 * @date 2019-05-07 16:02
 * @since 1.0.0v
 */
@Component
@RuleClass(comment = "数据库数据清理规则")
@Slf4j
public class DbDataCleanRule {

	private DataBaseMapper dataBaseMapper = SpringContextHolder.getBean(DataBaseMapper.class);
	private CommonMapper commonMapper = SpringContextHolder.getBean(CommonMapper.class);

    @RuleMethod(comment = "删除某张表的全部数据", dynamicDataSource = true)
    public void deleteAll(JobDataMap params) {
    	String json = params.getString("json");
    	log.error("deleteAll - 删除某张表的全部数据开始..."  + json);
    	// 这里面有任务执行是所需的全部参数信息
        ParamsModel pm = JacksonUtils.getInstance(true).toBean(json, ParamsModel.class);
        if(validateData(pm, "deleteAll")) {
        	changDataSource(pm.getSources()[0]);
			commonMapper.deleteAll(pm.getTableAndColumn().getTableName());
			log.error("deleteAll - 删除某张表的全部数据结束...");
        }else{
        	log.error("deleteAll - 删除某张表的全部数据发生了异常...");
			throw new RuntimeException("请求参数设置不符合要求，请检查参数配置！");
        }
    }

    @RuleMethod(comment = "按指定条件删除某张表的数据", dynamicDataSource = true)
    public void onlyDeleteByCondition(JobDataMap params) {
        String json = params.getString("json");
        log.error("onlyDeleteByCondition - 按指定条件删除某张表的数据开始..." + json);
    	// 这里面有任务执行是所需的全部参数信息
		ParamsModel pm = JacksonUtils.getInstance(true).toBean(json, ParamsModel.class);
        if(validateData(pm, "onlyDeleteByCondition")) {
        	changDataSource(pm.getSources()[0]);
			commonMapper.onlyDeleteByCondition(pm.getTableAndColumn().getTableName(), pm.getTableAndColumn().getWhereColumns());
			log.error("onlyDeleteByCondition - 按指定条件删除某张表的数据结束...");
        }else{
        	log.error("onlyDeleteByCondition - 按指定条件删除某张表的数据发生了异常...");
        	throw new RuntimeException("请求参数设置不符合要求，请检查参数配置！");
        }
    }

	private void changDataSource(String dbId) {
		DataBase info = dataBaseMapper.selectByPrimaryKey(dbId);
		DataSourceBean bean = SpringContextHolder.getBean(DataSourceBean.class);
		bean.setSpringBeanName(info.getDbId());
		bean.setUrl(info.getDbType(), info.getDbIp(), info.getDbName());
		bean.setDriverClassName(info.getDbType());
		bean.setUsername(info.getUsername());
		bean.setPassword(info.getPassword());
		// 设置动态访问的数据源
		DataSourceContextHolder.setDynamicDataSource(bean);
	}

    @RuleMethod(comment = "按指定条件先备份，再删除某张表的数据", dynamicDataSource = true, multipleDataSource = true)
    public void backupAfterDeleteByCondition(JobDataMap params) {
//        String json = params.getString("json");
//        // 这里面有任务执行是所需的全部参数信息
//        TaskParams tp = JacksonUtils.getInstance(true).toBean(json, TaskParams.class);
//        log.error("backupAfterDeleteByCondition - 按指定条件先备份，再删除某张表的数据..." + json);
    }
    
    private boolean validateData(ParamsModel pm, String methodName) {
    	if(log.isDebugEnabled()) {
    		log.debug("定时动态任务执行时的参数为：" + JacksonUtils.getInstance(true).toJson(pm));
    	}
    	String[] sources = pm.getSources();
    	if(sources == null || sources.length == 0) {
    		log.error("需要连接的数据源配置不能为空！");
    		return false;
    	} else {
    		if("backupAfterDeleteByCondition".equals(methodName) && sources.length != 2) {
    			log.error("数据备份操作是需要同时指定源数据源和目标数据源，二者缺一不可");
    			return false;
    		}
    	}
    	TableAndColumn tableAndColumn = pm.getTableAndColumn();
    	String tableName = tableAndColumn.getTableName();
    	if(!StringUtils.isNotEmpty(tableName)) {
    		log.error("未指定要操作的数据库表！");
    		return false;
    	}
    	
    	List<WhereColumn> columns = tableAndColumn.getWhereColumns();
    	if(columns == null || columns.size() == 0) {
    		if("deleteAll".equals(methodName) && sources.length != 2) {
    			log.error("非删除表全部数据的情况下未指定条件参数");
    			return false;
    		}
    	}else {
    		for (WhereColumn wc : columns) {
				if(!StringUtils.isNotEmpty(wc.getColumnValue())) {
					log.error("未指定条件参数的值");
	    			return false;
				}
			}
    	}
    	return true;
    }

}
