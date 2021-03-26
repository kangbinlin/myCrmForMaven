package com.mu.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mu.bean.Employee;
import com.mu.bean.Menu;
import com.mu.mapper.EmployeeMapper;
import com.mu.page.PageResult;
import com.mu.query.EmployeeQueryObject;
import com.mu.service.EmployeeSerivce;
import com.mu.service.MenuService;
import com.mu.service.PermissionService;
import com.mu.util.AjaxResult;
import com.mu.util.PermissionUtil;
import com.mu.util.UserContext;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeSerivce empolyeeService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;
	@RequestMapping("/employee")
	public String index(){
		
		return "employee";
	}

	@RequestMapping("/login")
	@ResponseBody
public AjaxResult login(HttpServletRequest request,String username,String password){
		AjaxResult result=null;
		UserContext.set(request);
		Employee user=empolyeeService.getEmployeeForLogin(username,password);
		if(user!=null){
			result=new AjaxResult("登陆成功",true);
			request.getSession().setAttribute(UserContext.USER_IN_SESSION, user);
			List<String>userPermissions=permissionService.queryResourceByEid(user.getId());
			UserContext.get().getSession().setAttribute(UserContext.PERMISSION_IN_SESSION, userPermissions);
			
			List<Menu> menu=menuService.queryForMenu();		
		PermissionUtil.checkMenuPermission(menu);
			request.getSession().setAttribute(UserContext.MENU_IN_SESSION, menu);
		}
		else{
			result=new AjaxResult("登陆失败",false);
			
		}
		return result;
	}
	
@RequestMapping("/employee_list")
@ResponseBody
public PageResult list(EmployeeQueryObject queryObject){
	int temp1=queryObject.getPage();
	int temp2=queryObject.getRows();
	System.out.println("传过来的是页数是："+temp1+",传过来的行数是："+temp2);
	PageResult pageResult=empolyeeService.queryForPage(queryObject);
	return pageResult;
	
}	
@RequestMapping("/employee_add")
@ResponseBody	
public 	AjaxResult add(Employee employee){
	AjaxResult result=null;
	try {
		employee.setAdmin(false);
		employee.setPassword("123");
		employee.setState(true);
		empolyeeService.insert(employee);
		result=new AjaxResult("保存成功",true);
	} catch (Exception e) {
		result=new AjaxResult("保存失败",false);
	}
	
	return result;
}
	
@RequestMapping("/employee_update")
@ResponseBody	
public 	AjaxResult update(Employee employee){
	AjaxResult result=null;
	
	try {
	
		empolyeeService.update(employee);
		result=new AjaxResult("保存成功",true);
	} catch (Exception e) {
		result=new AjaxResult("保存失败",false);
	}
	
	return result;
	
	
}
@RequestMapping("/employee_delete")
@ResponseBody	
public 	AjaxResult delete(Long id){
	AjaxResult result=null;
	
	try {
	
		empolyeeService.updateState(id);
		result=new AjaxResult("删除成功",true);
	} catch (Exception e) {
		result=new AjaxResult("删除失败",false);
	}
	
	return result;
	
	
}

@RequestMapping("/role_queryById")
@ResponseBody
public List role_queryById(Long eid){
return empolyeeService.queryRoleByEid(eid);
}

	@RequestMapping("index")
public String index2(){
	return "index";
}
	
	@RequestMapping("/queryForMenu")
	@ResponseBody
public List<Menu> queryForMenu(){
	return (List<Menu>) UserContext.get().getSession().getAttribute(UserContext.MENU_IN_SESSION);
}
	
}
