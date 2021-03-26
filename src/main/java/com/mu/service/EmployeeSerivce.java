package com.mu.service;

import java.util.List;

import com.mu.bean.Employee;
import com.mu.bean.Menu;
import com.mu.page.PageResult;
import com.mu.query.EmployeeQueryObject;
import com.mu.query.QueryObject;

public interface EmployeeSerivce {

	Employee getEmployeeForLogin(String username, String password);


	PageResult queryForPage(EmployeeQueryObject queryObject);


	int insert(Employee employee);


	int update(Employee employee);


	int updateState(Long id);


	List queryRoleByEid(Long eid);



}
