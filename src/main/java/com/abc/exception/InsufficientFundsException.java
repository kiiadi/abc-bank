package com.abc.exception;

public class InsufficientFundsException extends InvalidTransactionException {
	private static final long serialVersionUID = 8498595321354634000L;

	public InsufficientFundsException(String message) {
		super(message);
	}
}
