package com.abc.exception;


public class InvalidTransactionException extends Exception {
    
	private static final long serialVersionUID = -2904528862705613500L;

	public InvalidTransactionException(String message) {
    	super(message);
    }

}
