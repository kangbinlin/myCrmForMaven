package com.mu.mapper;

import java.util.List;

import com.mu.bean.Department;
import com.mu.bean.Employee;
import com.mu.query.DepartmentQueryObject;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    List<Department> queryForEmp();

    Long queryForPageCount(DepartmentQueryObject queryObject);

    List<Employee> queryForPage(DepartmentQueryObject queryObject);
}