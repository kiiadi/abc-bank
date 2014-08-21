package com.abc;

/**
 * Created with IntelliJ IDEA.
 * User: bradharper
 * Date: 8/17/14
 * Time: 9:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String s) {
        super(s);
    }

}
