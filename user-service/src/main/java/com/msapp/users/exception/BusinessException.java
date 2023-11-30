package com.msapp.users.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException
{
	public BusinessException(String message)
	{
		super(message);
	}

}
