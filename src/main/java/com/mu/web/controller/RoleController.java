package com.mu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mu.bean.Role;
import com.mu.page.PageResult;
import com.mu.query.RoleQueryObject;
import com.mu.service.RoleService;
import com.mu.util.AjaxResult;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleSerivce;
	@RequestMapping("/role")
	public String index(){
		return "role";
	}
	
	

	@RequestMapping({"/role_list"})
	@ResponseBody
	public PageResult list(RoleQueryObject queryObject){
		
		PageResult pageResult=roleSerivce.queryForPage(queryObject);
		return pageResult;
	}
	
	
	
	
	@RequestMapping("/role_add")
	@ResponseBody
	public AjaxResult add(Role per){
		AjaxResult result=null;
		try {
			roleSerivce.insert(per);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
	}
	@RequestMapping("/role_update")
	@ResponseBody
	public AjaxResult update(Role role){
		AjaxResult result=null;
		try {
			roleSerivce.update(role);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
		
	}
	
	@RequestMapping("/role_queryForEmployee")
	@ResponseBody
	public List queryForEmployee(){
		return roleSerivce.selectAll();
		
	}
}
