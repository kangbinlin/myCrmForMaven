package com.mu.service.impl;



import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mu.bean.Employee;
import com.mu.bean.Role;
import com.mu.mapper.EmployeeMapper;
import com.mu.page.PageResult;
import com.mu.query.EmployeeQueryObject;
import com.mu.service.EmployeeSerivce;
@Service
public class EmployeeServiceImpl implements EmployeeSerivce {
 @Autowired
	private EmployeeMapper employeeMapper;

@Override
public Employee getEmployeeForLogin(String username, String password) {
	// TODO Auto-generated method stub
	return employeeMapper.getEmployeeForLogin(username, password);
}

@Override
public PageResult queryForPage(EmployeeQueryObject queryObject) {
	Long count=employeeMapper.queryForPageCount(queryObject);
	if(count==null){
		return new PageResult(0,Collections.emptyList());
	}
	List<Employee> result=employeeMapper.queryForPage(queryObject);
	
	return new PageResult(count.intValue(), result);
}

@Override
public int insert(Employee employee) {
	int sucess=employeeMapper.insert(employee);
	for(Role role:employee.getRoles()){
		employeeMapper.insertRelation(employee.getId(), role.getId());
		
	}
	return sucess;
}

@Override
public int update(Employee employee) {
	int sucess=employeeMapper.updateByPrimaryKey(employee);
	employeeMapper.deleteRelation(employee.getId());
	for(Role role:employee.getRoles()){
		employeeMapper.insertRelation(employee.getId(), role.getId());
		
	}
	return sucess;
}

@Override
public int updateState(Long id) {
	return employeeMapper.updateState(id);
	
}

@Override
public List queryRoleByEid(Long eid) {
	// TODO Auto-generated method stub
	return employeeMapper.queryRoleByEid(eid);
}


}
