package com.abc;

/**
 * Thrown to indicate that a method attempting to modify an account in any way has failed.
 */
public class AccountModificationException extends Exception {
	public AccountModificationException(String message) {
		super(message);
	}
}
