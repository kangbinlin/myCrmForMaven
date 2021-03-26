package com.mu.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mu.bean.Permission;
import com.mu.mapper.PermissionMapper;
import com.mu.page.PageResult;
import com.mu.query.QueryObject;
import com.mu.service.PermissionService;
@Service
public class PermissionSerivceImpl implements PermissionService {
@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public List<Permission> selectAll() {
		// TODO Auto-generated method stub
		return permissionMapper.selectAll();
	}
	@Override
	public List<String> queryResourceByEid(Long id) {
		// TODO Auto-generated method stub
		return permissionMapper.queryResourceByEid(id);
	}
	@Override
	public PageResult queryForPage(QueryObject queryObject) {
		Long count=permissionMapper.queryForPageCount(queryObject);
		if(count==null){
			return new PageResult(0, Collections.emptyList());
			
		}else{
			List departments=permissionMapper.queryForPage(queryObject);
			return new PageResult(count.intValue(), departments);
		}
	}
	@Override
	public int insert(Permission per) {
		// TODO Auto-generated method stub
		return permissionMapper.insert(per);
	}
	@Override
	public int update(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.updateByPrimaryKey(permission);
	}
	

}
