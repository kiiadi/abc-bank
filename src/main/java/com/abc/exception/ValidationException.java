package com.abc.exception;

/**
 * 
 * @author Sanju Thom
 *
 */
public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;

	public ValidationException(final String message){
		super(message);
	}
}
