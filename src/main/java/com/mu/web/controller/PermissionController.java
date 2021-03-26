package com.mu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mu.bean.Permission;
import com.mu.page.PageResult;
import com.mu.query.PermissionQueryObject;
import com.mu.service.PermissionService;
import com.mu.util.AjaxResult;

@Controller
public class PermissionController {
	@Autowired
	private PermissionService permissionSerivce;
	@RequestMapping("/permission")
	public String index(){
		return "permission";
	}
	
	

	@RequestMapping({"/permission_list","/permission_queryByRid"})
	@ResponseBody
	public PageResult list(PermissionQueryObject queryObject){
		
		PageResult pageResult=permissionSerivce.queryForPage(queryObject);
		return pageResult;
	}
	
	
	
	
	@RequestMapping("/permission_add")
	@ResponseBody
	public AjaxResult add(Permission per){
		AjaxResult result=null;
		try {
			permissionSerivce.insert(per);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
	}
	@RequestMapping("/permission_update")
	@ResponseBody
	public AjaxResult update(Permission Permission){
		AjaxResult result=null;
		try {
			permissionSerivce.update(Permission);
			result=new AjaxResult("保存成功",true);
		} catch (Exception e) {
			result=new AjaxResult("保存失败",false);
		}
		return result;
		
	}
}
