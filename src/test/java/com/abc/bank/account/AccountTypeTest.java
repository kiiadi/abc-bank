package com.abc.bank.account;

import org.junit.Assert;
import org.junit.Test;

import com.abc.bank.account.AccountType;

public class AccountTypeTest {

    @Test
    public void testSavingsAccountTypeToString() {
        Assert.assertTrue(AccountType.savings.toString().equals("Savings Account"));
    }
    
    @Test
    public void testCheckingAccountTypeToString() {
        Assert.assertTrue(AccountType.checking.toString().equals("Checking Account"));
    }
    
    @Test
    public void testMaxiSavingsAccountTypeToString() {
        Assert.assertTrue(AccountType.maxiSavings.toString().equals("Maxi Savings Account"));
    }

}
