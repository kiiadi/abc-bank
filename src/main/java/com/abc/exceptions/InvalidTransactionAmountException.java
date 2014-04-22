package com.abc.exceptions;

public class InvalidTransactionAmountException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidTransactionAmountException(String message) {
        super(message);
    }
}
