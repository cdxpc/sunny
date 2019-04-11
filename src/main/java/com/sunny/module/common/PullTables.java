package com.sunny.module.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sunny.core.SpringContextHolder;
import com.sunny.core.datasource.dynamic.DataSourceContextHolder;
import com.sunny.core.datasource.model.DataSourceBean;
import com.sunny.core.util.UuidUtils;
import com.sunny.module.common.mapper.CommonMapper;
import com.sunny.module.db.database.entity.DataBase;
import com.sunny.module.db.database.mapper.DataBaseMapper;
import com.sunny.module.db.table.entity.DbTable;
import com.sunny.module.db.table.mapper.DbTableMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 将其他数据源的数据库表结构信息拉取过来，将其保存在默认的数据源表中，方便使用
 * 项目每次启动的时候就进行表拉取操作，拉取的时候需要对比数据表的表名是否发生了变化
 * 
 * @author tlbank
 *
 */
@Component
@Lazy(false)
@Slf4j
public class PullTables implements ApplicationListener<ContextRefreshedEvent> {

	private CommonMapper commonMapper;
	private DbTableMapper dbTableMapper;
	private DataBaseMapper dataBaseMapper;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			log.info("application context : " + SpringContextHolder.getApplicationContext());
			this.commonMapper = SpringContextHolder.getBean(CommonMapper.class);
			this.dbTableMapper = SpringContextHolder.getBean(DbTableMapper.class);
			this.dataBaseMapper = SpringContextHolder.getBean(DataBaseMapper.class);
			doExecute();
		}
	}

	private void doExecute() {
		// 先将默认数据源中之前拉取过来的表结构信息记录全部删除掉，拉取最新的进行重新插入
		dbTableMapper.delete(null);
		// 从默认的数据源库中获取其他所有的数据源配置
		List<DataBase> list = dataBaseMapper.select(DataBase.of());
		log.error("list: " + list);
		final Map<String[], List<DbTable>> tablesMap = new HashMap<>();
		final Map<String[], List<DbTable>> columnsMap = new HashMap<>();
		final Map<String, Map<String[], List<DbTable>>> all = new HashMap<>();
		
		long time = System.currentTimeMillis();
		CompletableFuture.runAsync(() -> {
			list.stream().forEach(info -> {
				getFromDynamicDataSource(tablesMap, columnsMap, all, info);
				log.error("db ip => " + info.getDbIp() + ", db name => " + info.getDbName());
			});
		}).thenRunAsync(()->{
			// 数据拉取完毕之后，集中插入到默认数据源中
			// 操作完成之后，恢复使用默认数据源
			DataSourceContextHolder.toDefault();
			// 进行插入
			insertToDefaultDataSource(all);
		}).whenComplete((t,u) -> {
			log.info("数据库异步操作总耗时:" + (System.currentTimeMillis() - time + "毫秒"));
		});
	}

	// 从动态数据源原中获取数据表结构信息
	private Map<String, Map<String[], List<DbTable>>> getFromDynamicDataSource(Map<String[], List<DbTable>> tablesMap,
			Map<String[], List<DbTable>> columnsMap, Map<String, Map<String[], List<DbTable>>> all, DataBase info) {
		DataSourceBean bean = SpringContextHolder.getBean(DataSourceBean.class);
		bean.setSpringBeanName(info.getDbId());
		bean.setUrl(info.getDbType(), info.getDbIp(), info.getDbName());
		bean.setDriverClassName(info.getDbType());
		bean.setUsername(info.getUsername());
		bean.setPassword(info.getPassword());
		// 设置动态访问的数据源
		DataSourceContextHolder.setDynamicDataSource(bean);
		// 进行操作，获取该数据源下的数据库表结构信息集合
		String[] idAndName = { info.getDbId(), info.getDbName() };
		List<DbTable> tables = null;
		List<DbTable> columns = null;
		// 拉取数据
		if("1".equals(info.getDbType())) {
			tables = commonMapper.getTablesFromMysql(info.getOwner());
			columns = commonMapper.getColumnsFromMysql(info.getOwner());
		}else if("2".equals(info.getDbType())) {
			tables = commonMapper.getTablesFromOracle(info.getOwner());
			columns = commonMapper.getColumnsFromOracle(info.getOwner());
		}
		
		if(tables!= null && tables.size() > 0) {
			tablesMap.put(idAndName, tables);
			all.put("table", tablesMap);
		}
		if(columns!= null && columns.size() > 0) {
			columnsMap.put(idAndName, columns);
			all.put("column", columnsMap);
		}
		return all;
	}

	// 将拉取过来的表信息插入到默认数据源库中
	private void insertToDefaultDataSource(Map<String, Map<String[], List<DbTable>>> all) {
		if (all != null && all.size() > 0) {
			for (Map.Entry<String, Map<String[], List<DbTable>>> entry : all.entrySet()) {
				String key = entry.getKey();
				Map<String[], List<DbTable>> values = entry.getValue();
				if(values != null && values.size() > 0) {
					for (Map.Entry<String[], List<DbTable>> et : values.entrySet()) {
						String[] idAndName = et.getKey();
						List<DbTable> vs = et.getValue();
						for (DbTable ct : vs) {
							if("column".equals(key)) {
								ct.setIsColumn("1");  // 1 - 是
							} else {
								ct.setIsColumn("2");  // 2 -否
							}
							ct.setId(UuidUtils.uuid());
							ct.setDbId(idAndName[0]);
							ct.setDbName(idAndName[1]);
							ct.setCreateTime(new Date());
							ct.setStatus("1");
							dbTableMapper.insert(ct);
						}
					}
				}
			}
		}
	}

}
