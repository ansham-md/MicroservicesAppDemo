package com.msapp.users.audit;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


@Aspect
@Configuration
public class AuditEventAspect
{
	Logger LOGGER=LogManager.getLogger(AuditEventAspect.class);
	static final String EXCEPTION_HANDLER_POINTCUT="execution(* com.msapp.users.exception.*.*(..))";
	final String SUCCESS_HANDLER_POINTCUT="execution(* com.msapp.users.service.UserService.checker(..))";
	
	public static final String name="Client-name";
	public static final String id="Client-id";
	
	
	String[] Audit_Event_Headers=new String[] {name,id};
	static
	{
		
		List<String> HEADERS =new ArrayList();
		HEADERS.add(name);
		HEADERS.add(id);
		
	}

	public HttpHeaders createAuditEventRequestHeaders(Object partyHeaders)
	{
		HttpHeaders headers=new HttpHeaders();
		for(String headerName : Audit_Event_Headers)
		{
			if(partyHeaders instanceof HttpServletRequest)
			{
				headers.add(headerName, ((HttpServletRequest) partyHeaders).getHeader(headerName));
			}
		}
		//LOGGER.info( "headers are -> "+headers.toString());
		return headers;
	}
	
	
	
	@AfterReturning (pointcut= EXCEPTION_HANDLER_POINTCUT, returning = "responseEntity")
	public void afterReturningErrorResponse(JoinPoint joinpoint, ResponseEntity<String> responseEntity)
	{
		LOGGER.info("in Failure Advice");
		for(Object methodArgs : joinpoint.getArgs())
		{
			if(methodArgs instanceof HttpServletRequest)
			{
				LOGGER.info("response Entity inside HttpSrvReq");
				HttpHeaders headers=createAuditEventRequestHeaders(methodArgs);
				return;
			}
		}
		LOGGER.error(joinpoint.getSignature() + " : Method arguments is missing parameter of type"+ HttpServletRequest.class.getName());
	}
	@AfterReturning(pointcut=SUCCESS_HANDLER_POINTCUT)
	public void afterRetrningResponse(JoinPoint joinPoint)
	{
		LOGGER.info(" in SUCCESS advice");
		
		for(Object methodArgs : joinPoint.getArgs())
		{
			if(methodArgs instanceof HttpServletRequest)
			{
				LOGGER.info("response Entity inside HttpSrvReq");
				HttpHeaders headers=createAuditEventRequestHeaders(methodArgs);
			}
		}
	}
	
	
	
}


/*
String[] Audit_Event_Headers=new String[] {name,id};
static
{
	List<String> HEADERS =Lists.newArrayList();
	HEADERS.add(name);
	HEADERS.add(id);
	
}

private HttpHeaders createAuditEventRequestHeaders(Object partyHeaders)
{
	HTTP headers=new HttpHeaders();
	for(String headerName : Audit_Event_Headers)
	{
		if(partyHeaders instanceof HttpServletRequest)
		{
			headers.add(headerName, (((HttpServletRequest) partyHeaders).getHeader(headerName)));
		}
	}
	LOGGER.info( "headers are -> "+headers.toString());
	return headers;
}

*/