package com.mu.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mu.bean.Employee;
import com.mu.bean.Log;
import com.mu.service.LogService;

public class LogUtil {
	@Autowired
private LogService logService;
	
	public void writeLog(JoinPoint joinPoint){
		if(joinPoint.getTarget() instanceof LogService){
			return;
			
		}
		Log log=new Log();
		log.setOptime(new Date());
		HttpServletRequest req=UserContext.get();
		log.setOpip(req.getRemoteAddr());
		Employee user=(Employee) req.getSession().getAttribute(UserContext.USER_IN_SESSION);
	
log.setOpuser(user);
String className=joinPoint.getTarget().toString();
String functionName=joinPoint.getSignature().toString();
log.setFunction(className+":"+functionName);
ObjectMapper tempMapper=new ObjectMapper();
try {
	String param=tempMapper.writeValueAsString(joinPoint.getArgs());
log.setParams(param);
}
catch (Exception e) {
	e.printStackTrace();
}
logService.insert(log);
	
	}

}
