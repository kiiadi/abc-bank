package com.abc.exceptions;

import com.abc.api.AccountType;

public class InvalidAccountTypeException extends RuntimeException {
    private AccountType accountType;

    public InvalidAccountTypeException(String message, AccountType accountType) {
        super(message);
        this.accountType = accountType;
    }
}
