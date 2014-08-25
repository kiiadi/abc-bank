package com.abc.impl;

import com.abc.api.Customer;
import com.abc.api.CustomerId;

public class CustomerImpl implements Customer {

    private CustomerId customerId;
    private String firstName;
    private String lastName;

    @Override
    public CustomerId getId() {
        return customerId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerImpl customer = (CustomerImpl) o;

        if (!customerId.equals(customer.customerId)) return false;

        return true;
    }

    public CustomerImpl(CustomerId customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return customerId.hashCode();
    }
}
