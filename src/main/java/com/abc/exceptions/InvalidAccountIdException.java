package com.abc.exceptions;

public class InvalidAccountIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAccountIdException(String message) {
        super(message);
    }
}
