package com.abc.exception;

/**
 * 
 * @author Sanju Thomass
 *
 */
public class TransferFailedException extends ValidationException{

	private static final long serialVersionUID = 1L;
	
	public TransferFailedException(final String message){
		super(message);
	}

}
