package com.mu.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mu.bean.Role;
import com.mu.mapper.RoleMapper;
import com.mu.page.PageResult;
import com.mu.query.RoleQueryObject;
import com.mu.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
@Autowired
	private RoleMapper roleMapper;

@Override
public int insert(Role per) {
	// TODO Auto-generated method stub
	return roleMapper.insert(per);
}

@Override
public PageResult queryForPage(RoleQueryObject queryObject) {
	Long count=roleMapper.queryForPageCount(queryObject);
	if(count==null){
		return new PageResult(0, Collections.emptyList());
	}
	List roles=roleMapper.queryForPage(queryObject);
	return new PageResult(count.intValue(), roles);
}

@Override
public int update(Role role) {
	// TODO Auto-generated method stub
	return roleMapper.updateByPrimaryKey(role);
}

@Override
public List selectAll() {
	// TODO Auto-generated method stub
	return roleMapper.selectAll();
}
	
}
