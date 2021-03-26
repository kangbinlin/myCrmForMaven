package com.mu.service;

import java.util.List;

import com.mu.bean.Department;
import com.mu.page.PageResult;
import com.mu.query.DepartmentQueryObject;

public interface DepartmentSerivce {

	List<Department> queryForEmp();

	PageResult queryForPage(DepartmentQueryObject queryObject);

	int insert(Department depa);

	int update(Department department);

	int delete(Department department);

}
