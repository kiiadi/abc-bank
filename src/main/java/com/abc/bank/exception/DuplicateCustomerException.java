package com.abc.bank.exception;

public class DuplicateCustomerException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DuplicateCustomerException() {
        super();
    }

    public DuplicateCustomerException(String message) {
        super(message);
    }
}
