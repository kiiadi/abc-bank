package com.abc.bank.exception;

public class NoSuchAccountException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NoSuchAccountException() {
        super();
    }

    public NoSuchAccountException(String message) {
        super(message);
    }

}
