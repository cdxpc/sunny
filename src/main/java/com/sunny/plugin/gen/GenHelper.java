//package com.sunny.plugin.gen;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.sunny.core.SpringContextHolder;
//import com.sunny.core.util.UuidUtils;
//import com.sunny.plugin.gen.entity.DbColumn;
//import com.sunny.plugin.gen.entity.Column;
//import com.sunny.plugin.gen.entity.Table;
//import com.sunny.plugin.gen.entity.DbTable;
//import com.sunny.plugin.gen.service.ColumnService;
//import com.sunny.plugin.gen.service.GenService;
//import com.sunny.plugin.gen.service.TableService;
//
//@Service("gen")
//public class GenHelper {
//
//	private GenService genService = SpringContextHolder.getBean(GenService.class);
//	private TableService tableService = SpringContextHolder.getBean(TableService.class);
//	private ColumnService columnService = SpringContextHolder.getBean(ColumnService.class);
//
//	@Value("${sunny.ex.table}")
//	private String[] exTable;
//	@Value("${sunny.ex.column}")
//	private String[] exColumn;
//
//	//@PostConstruct
//	public void init() {
//		columnService.deleteAll();
//		List<DbTable> dts = genService.getTables();
//		if(dts != null && dts.size() > 0) {
//			dts.stream().filter(dt -> !Arrays.asList(exTable).contains(dt.getTableName())).forEach(dt -> {
//				Table t = Table.of();
//				t.setTableName(dt.getTableName()); // XXX 表名或列名改了怎么对比，无法对比？？？
//				Table one = tableService.findOne(t);
//				Date date = new Date();
//				final String tableId;
//				if(one == null) {
//					tableId = UuidUtils.uuid();
//					t.setId(tableId);
//					t.setTableComment(dt.getTableComment());
//					t.setProduced("2");
//					t.setStatus("1");
//					t.setCreateBy("init");
//					t.setCreateTime(date);
//					t.setLastUpdateBy("init");
//					t.setLastUpdateTime(date);
//					tableService.create(t, false);
//				} else {
//					tableId = one.getId(); // XXX 表名或列名改了怎么对比，无法对比？？？
//					if(!dt.getTableName().equals(one.getTableName()) || !dt.getTableComment().equals(one.getTableComment())) {
//						one.setTableName(dt.getTableName());
//						one.setTableComment(dt.getTableComment());
//						one.setLastUpdateTime(date);
//						one.setProduced("3");
//						tableService.update(one, false);
//					}
//				}
//				List<DbColumn> dcs = genService.getColumns(dt.getTableName()); // XXX 表名或列名改了怎么对比，无法对比？？？
//				if(dcs != null && dcs.size() > 0) {
//					dcs.stream().filter(dc -> !Arrays.asList(exColumn).contains(dc.getColumnName())).forEach(dc -> {
//						Column c = Column.of();
//						c.setId(UuidUtils.uuid());
//						c.setTableId(tableId);
//						c.setTableName(dt.getTableName());
//						c.setColumnName(dc.getColumnName());
//						c.setColumnComment(dc.getColumnComment());
//						c.setDataType(dc.getDataType());
//						c.setIsPk("2");
//						c.setStatus("1");
//						c.setCreateBy("init");
//						c.setCreateTime(date);
//						c.setLastUpdateBy("init");
//						c.setLastUpdateTime(date);
//						columnService.create(c, false);
//					});
//				}
//			});
//		}
//	}
//
//}
