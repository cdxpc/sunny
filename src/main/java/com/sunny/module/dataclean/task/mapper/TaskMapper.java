package com.sunny.module.dataclean.task.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.dataclean.task.entity.Task;

public interface TaskMapper extends BaseMapper<Task> {
	
	// String Sql = "SELECT DISTINCT t.task_id as taskId,t.task_name as taskName,t.task_group as taskGroup,t.run_status as runStatus, t.task_method as taskMethod,t.db_id as dbId,t.table_name as tableName, t.rule_id as ruleId, t.*, b.`db_name` as dbName, r.`rule_name` as ruleName ";
	// String SqlCount = "SELECT COUNT(t.task_id) ";
	//
	// String from = " FROM t_data_clean_task t LEFT JOIN t_db_data_base b ON t.`db_id` = b.`db_id` LEFT JOIN t_data_clean_rule r ON t.`rule_id` = r.`rule_id` ";
	//
	// String where = "<where><if test=\"taskName !=null and taskName !=''\"> AND t.task_name = #{taskName}</if><if test=\"dbId !=null and dbId !=''\"> AND t.db_id = dbId</if><if test=\"ruleId !=null and ruleId !=''\"> AND t.rule_id = ruleId</if></where>";
	//
	// String page = "<if test=\"start >=0\"> limit #{start}</if><if test=\"start >=0 and end >0\"> , #{end}</if>";
	//
	// @Select({"<script>", Sql, from ,where , page, "</script>"})
	// List<TaskDto> findList2(@Param("taskName")String taskName, @Param("dbId")String dbId, @Param("ruleId")String ruleId, @Param("start")int start, @Param("end")int pageSize);
	//
	// @Select({"<script>", SqlCount, from ,where , "</script>"})
	// int findCount2(@Param("taskName")String taskName, @Param("dbId")String dbId, @Param("ruleId")String ruleId);

}
