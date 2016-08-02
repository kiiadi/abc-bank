package com.abc;

/**
 * Created by ashishsharma on 8/2/16.
 */
public class AbcBankException extends Exception {

    private static final long serialVersionUID = 4664456874499611218L;
    private String errorCode="Unknown_Exception";

    public AbcBankException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }

    public String getErrorCode(){

        return this.errorCode;

    }
}
