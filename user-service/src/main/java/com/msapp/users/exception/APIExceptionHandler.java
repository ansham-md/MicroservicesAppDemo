package com.msapp.users.exception;


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages= {"com.msapp.users"})
public class APIExceptionHandler 
{
	Logger LOGGER=LogManager.getLogger(APIExceptionHandler.class);
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleException(BusinessException e,HttpServletRequest req)
	{
		LOGGER.error("Error! :",e);
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN) ;
		
	}

}
