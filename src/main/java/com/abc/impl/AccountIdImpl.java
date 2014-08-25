package com.abc.impl;

import com.abc.api.AccountId;

public class AccountIdImpl implements AccountId {

    private String id;

    public AccountIdImpl(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountIdImpl accountId = (AccountIdImpl) o;

        if (id != null ? !id.equals(accountId.id) : accountId.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AccountIdImpl{" +
                "id='" + id + '\'' +
                '}';
    }
}
