package com.mu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mu.bean.Role;
import com.mu.query.QueryObject;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int insertRelation(@Param("rid") Long rid, @Param("pid") Long pid);

    Long queryForPageCount(QueryObject queryObject);

    List<Role> queryForPage(QueryObject queryObject);

    int deletePermissionByRid(Long id);
}
