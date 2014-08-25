package com.abc.services;

import com.abc.api.AccountId;
import com.abc.impl.AccountIdImpl;

import java.util.Random;

public class AccountIdGeneratorImpl implements AccountIdGenerator {
    @Override
    public AccountId generateAccountId() {
        return new AccountIdImpl(Integer.toString(new Random().nextInt(10000000))); //todo -- implement this
    }
}
