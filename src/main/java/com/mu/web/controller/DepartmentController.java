package com.mu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mu.bean.Department;
import com.mu.page.PageResult;
import com.mu.query.DepartmentQueryObject;
import com.mu.service.DepartmentSerivce;
import com.mu.util.AjaxResult;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentSerivce departmentSerivce;
	@RequestMapping("/department")
	public String index(){
		return "department";
	}
	
	
	@RequestMapping("/department_queryForEmployee")
	@ResponseBody
	public List<Department> queryForEmployee(){
		return departmentSerivce.queryForEmp();
	}
	
	
	@RequestMapping("department_list")
	@ResponseBody
	public PageResult list(DepartmentQueryObject queryObject){
		
		PageResult pageResult=departmentSerivce.queryForPage(queryObject);
		return pageResult;
	}
	@RequestMapping("/department_add")
	@ResponseBody
	public AjaxResult add(Department depa){
		AjaxResult result=null;
		try {
			departmentSerivce.insert(depa);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
	}
	@RequestMapping("/department_update")
	@ResponseBody
	public AjaxResult update(Department department){
		AjaxResult result=null;
		try {
			departmentSerivce.update(department);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
		
	}

	@RequestMapping("/department_delete")
	@ResponseBody
	public AjaxResult delete(Department department){
		AjaxResult result=null;
		try {
			departmentSerivce.delete(department);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
		
	}
	
	
}
