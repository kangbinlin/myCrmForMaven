package com.mu.mapper;

import java.util.List;

import com.mu.bean.Permission;
import com.mu.query.QueryObject;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    Long queryForPageCount(QueryObject queryObject);

    List<Permission> queryForPage(QueryObject queryObject);

    List<String> queryResourceByEid(Long eid);
}