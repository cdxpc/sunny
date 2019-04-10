package com.sunny.module.sys.role.mapper;

import com.sunny.core.base.BaseMapper;
import com.sunny.module.sys.role.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.IdsMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role>, IdsMapper<Role> {
	
	String Sql = "SELECT DISTINCT r.role_id as roleId, r.role_code as roleCode, r.role_name as roleName, r.* ";
	String SqlCount = "SELECT COUNT(r.role_id) ";
	
	String in = " FROM ft_sys_role r LEFT JOIN ft_sys_user_role ur ON r.role_id = ur.role_id WHERE ur.user_id = #{userId} ";
	String notIn = " FROM ft_sys_role r WHERE r.role_id NOT IN (SELECT DISTINCT role_id FROM ft_sys_user_role ur WHERE ur.user_id = #{userId}) ";
	
	String where = "<if test=\"roleCode !=null and roleCode !=''\"> AND r.role_code = #{roleCode}</if><if test=\"roleName !=null and roleName !=''\"> AND r.role_name like concat('%', #{roleName}, '%')</if>";
	
	String page = "<if test=\"start >=0\"> limit #{start}</if><if test=\"start >=0 and end >0\"> , #{end}</if>";

	@Select({"<script>", Sql, in ,where , page, "</script>"})
	List<Role> getRoleInUser(@Param("roleCode") String roleCode, @Param("roleName") String roleName, @Param("userId") String userId, @Param("start") int start, @Param("end") int end);
	
	@Select({"<script>", SqlCount, in ,where , "</script>"})
	int getRoleInUserCount(@Param("roleCode") String roleCode, @Param("roleName") String roleName, @Param("userId") String userId);

	@Select({"<script>", Sql, notIn ,where , page, "</script>"})
	List<Role> getRoleNotInUser(@Param("roleCode") String roleCode, @Param("roleName") String roleName, @Param("userId") String userId, @Param("start") int start, @Param("end") int end);

	@Select({"<script>", SqlCount, notIn ,where , "</script>"})
	int getRoleNotInUserCount(@Param("roleCode") String roleCode, @Param("roleName") String roleName, @Param("userId") String userId);

}
