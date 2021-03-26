package com.mu.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mu.bean.Employee;
import com.mu.util.PermissionUtil;
import com.mu.util.UserContext;

public class LoginInterceptor implements HandlerInterceptor {
 @Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		  UserContext.set(req);
Employee user=(Employee) req.getSession().getAttribute(UserContext.USER_IN_SESSION);
if(user==null){
	res.sendRedirect("/login.jsp");
	System.out.println("你被拦截了");
	return false;
}
if(handler instanceof HandlerMethod){
	HandlerMethod handlerMethod=(HandlerMethod) handler;
	String function=handlerMethod.getBean().getClass().getName()+":"+handlerMethod.getMethod().getName();
	boolean flag=PermissionUtil.checkPermission(function);
	System.out.println(function);
	if(flag){
		return true;
	}else{
		if(handlerMethod.getMethod().isAnnotationPresent(ResponseBody.class)){
			res.sendRedirect("/noPermission.json");
		}else{
			res.sendRedirect("/noPermission.jsp");
		}
		return false;
	}
	
	
}
	       

	        return true;

	}
	  
	  @Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	  @Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	

}
