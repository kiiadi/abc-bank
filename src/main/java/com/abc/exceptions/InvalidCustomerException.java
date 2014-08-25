package com.abc.exceptions;

import com.abc.api.CustomerId;

public class InvalidCustomerException extends RuntimeException {
    private CustomerId customerId;
    public InvalidCustomerException(String message, CustomerId customerId) {
        super(message);
        this.customerId = customerId;
    }
}
