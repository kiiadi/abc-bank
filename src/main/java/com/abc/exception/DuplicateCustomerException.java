package com.abc.exception;

public class DuplicateCustomerException extends Exception {

	private static final long serialVersionUID = 3851707737321722069L;

	public DuplicateCustomerException(String message) {
		super(message);
	}
}
