package com.mu.util;

import java.util.List;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mu.bean.Employee;
import com.mu.bean.Menu;
import com.mu.bean.Permission;
import com.mu.service.PermissionService;

@Component
public class PermissionUtil {
	private static PermissionService permissionService;
	@Autowired
	public void setPermissionService(PermissionService permissionService){
		PermissionUtil.permissionService=permissionService;
	}
	
	public static boolean checkPermission(String function){
		Employee user=(Employee)UserContext.get().getSession().getAttribute(UserContext.USER_IN_SESSION);
		if(user.getAdmin()){

			System.out.println("是超级管理员，权限放行:"+function);
			return true;
		}

		if(UserContext.ALL_PERMISSIONS.size()==0){
			List<Permission>permissions=permissionService.selectAll();
			for(Permission permission:permissions){
				UserContext.ALL_PERMISSIONS.add(permission.getResource());
			}
		}
		if(UserContext.ALL_PERMISSIONS.contains(function)){
			List<String> userPermissions=(List<String>) UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
		if(userPermissions.contains(function)){
			return true;
		}else{
			String allPermission =function.split(":")[0]+":"+"all";
		if(userPermissions.contains(allPermission)){
			return true;
			
		}else{
			return false;
		}
		}
		
		}else{
			return true;
		}
		
	}

	 public static void checkMenuPermission(List<Menu> menuList) {
	    	Menu menu=null;
	    	for(int i=menuList.size()-1;i>=0;i--){
	    	menu=menuList.get(i);
	    	if(checkPermission(menu.getFunction())){
	    		if(menu.getChildren()!=null&&menu.getChildren().size()>0){
	    			
	    			checkMenuPermission(menu.getChildren());
	    		}
	    		
	    	}else{
    			menuList.remove(i);
    		}
	    	
	    	}
	    }

	
}
