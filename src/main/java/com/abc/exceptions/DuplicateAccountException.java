package com.abc.exceptions;

public class DuplicateAccountException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateAccountException(String message) {
        super(message);
    }
}
