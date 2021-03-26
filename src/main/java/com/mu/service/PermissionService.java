package com.mu.service;

import java.util.List;

import com.mu.bean.Permission;
import com.mu.page.PageResult;
import com.mu.query.QueryObject;

public interface PermissionService {

	List<Permission> selectAll();

	List<String> queryResourceByEid(Long id);

	PageResult queryForPage(QueryObject queryObject);

	int insert(Permission per);

	int update(Permission permission);

	

}
