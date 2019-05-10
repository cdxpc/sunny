package com.sunny.module.common.mapper;

import com.sunny.module.dataclean.WhereColumn;
import com.sunny.module.db.table.entity.DbTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommonMapper {
	
	@Select(value = "SELECT t.table_name as tableName, t.table_comment as tableComment FROM information_schema.`TABLES` t WHERE t.table_schema = #{owner}")
	List<DbTable> getTablesFromMysql(@Param("owner") String owner);

	@Select(value = "SELECT c.table_name As tableName, c.column_name as columnName, c.column_comment as columnComment, c.data_type as dataType FROM information_schema.`COLUMNS` c WHERE c.table_schema = #{owner}")
	List<DbTable> getColumnsFromMysql(@Param("owner") String owner);

	@Select(value = "SELECT t.table_name AS tableName, c.comments AS tableComment FROM ALL_TABLES t, ALL_TAB_COMMENTS c WHERE t.owner = c.owner AND t.table_name = c.table_name AND t.owner = #{owner}")
	List<DbTable> getTablesFromOracle(@Param("owner") String owner);

	@Select(value = "SELECT a.table_name AS tableName, a.column_name AS columnName, b.comments AS columnComment, b.data_type AS dataType FROM ALL_TAB_COLS a, ALL_COL_COMMENTS b WHERE a.table_name = b.table_name AND a.column_name = b.column_name AND a.owner = #{owner}")
	List<DbTable> getColumnsFromOracle(@Param("owner") String owner);

	@Delete("delete from ${tableName}")
	int deleteAll(@Param("tableName") String tableName);

	@Delete("delete from ${tableName}")
	int onlyDeleteByCondition(@Param("tableName") String tableName, @Param("list") List<WhereColumn> wcs);

}
