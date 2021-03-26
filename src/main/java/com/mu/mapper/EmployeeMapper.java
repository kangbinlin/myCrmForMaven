package com.mu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mu.bean.Employee;
import com.mu.query.EmployeeQueryObject;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(@Param("username") String username, @Param("password") String password);

    Long queryForPageCount(EmployeeQueryObject queryObject);

    List<Employee> queryForPage(EmployeeQueryObject queryObject);

    int updateState(Long id);

    void insertRelation(@Param("eid")Long eid, @Param("rid")Long rid);

    List<Long> queryRoleByEid(Long eid);

    int deleteRelation(Long eid);
}