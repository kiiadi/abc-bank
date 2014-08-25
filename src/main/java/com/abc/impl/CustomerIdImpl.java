package com.abc.impl;

import com.abc.api.CustomerId;

public class CustomerIdImpl implements CustomerId {

    private String id;

    public CustomerIdImpl(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerIdImpl that = (CustomerIdImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CustomerIdImpl{" +
                "id='" + id + '\'' +
                '}';
    }
}
