package com.abc;


public class UnknownAccountException extends Exception {
    public UnknownAccountException(Customer customer, Account account) {
        super("Customer " + customer.getName() + " doesn't know about account " + account);
    }
}
