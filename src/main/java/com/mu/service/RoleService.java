package com.mu.service;

import java.util.List;

import com.mu.bean.Role;
import com.mu.page.PageResult;
import com.mu.query.RoleQueryObject;


public interface RoleService {

	int insert(Role per);

	PageResult queryForPage(RoleQueryObject queryObject);

	int update(Role role);

	List selectAll();

}
