package com.mu.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mu.bean.Department;
import com.mu.mapper.DepartmentMapper;
import com.mu.page.PageResult;
import com.mu.query.DepartmentQueryObject;
import com.mu.service.DepartmentSerivce;

@Service
public class DepartmentSerivceImpl implements DepartmentSerivce {
@Autowired
	private DepartmentMapper departmentMapper;

@Override
public List<Department> queryForEmp() {
	// TODO Auto-generated method stub
	return departmentMapper.queryForEmp();
}

@Override
public PageResult queryForPage(DepartmentQueryObject queryObject) {
	Long count=departmentMapper.queryForPageCount(queryObject);
	if(count==null){
		return new PageResult(0, Collections.emptyList());
		
	}
		List department=departmentMapper.queryForPage(queryObject);
	
		return new PageResult(count.intValue(), department);
	
}

@Override
public int insert(Department depa) {
	// TODO Auto-generated method stub
	return departmentMapper.insert(depa);
}

@Override
public int update(Department department) {
	// TODO Auto-generated method stub
	return departmentMapper.updateByPrimaryKey(department);
}

@Override
public int delete(Department department) {
	// TODO Auto-generated method stub
	return 0;
}
}
