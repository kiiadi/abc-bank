package com.abc.bank.exception;

public class InsufficuentFundsException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InsufficuentFundsException() {
        super();
    }

    public InsufficuentFundsException(String message) {
        super(message);
    }

}
