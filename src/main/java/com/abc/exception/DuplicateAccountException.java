package com.abc.exception;

public class DuplicateAccountException extends Exception {

	private static final long serialVersionUID = -825391009390808247L;

	public DuplicateAccountException(String message) {
		super(message);
	}

}
