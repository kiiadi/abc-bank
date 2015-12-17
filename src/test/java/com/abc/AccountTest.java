package com.abc;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yi on 12/17/15.
 */
public class AccountTest {
    private Account checkingAccount;
    private Account savingsAccount;
    private Account maxiSavings;


    @Before
    public void setUp() {
        maxiSavings =  Account.getAccount(Account.MAXI_SAVINGS);
        savingsAccount =  Account.getAccount(Account.SAVINGS);
        checkingAccount =  Account.getAccount(Account.CHECKING);

    }

    @Test(expected=IllegalArgumentException.class)
    public void withdraw_insufficient_balance(){
        checkingAccount.withdraw(BigDecimal.ONE);
    }

    @Test()
    public void withdraw(){
        checkingAccount.deposit(BigDecimal.TEN);
        checkingAccount.withdraw(BigDecimal.ONE);
        assertEquals(new BigDecimal(9).setScale(8, RoundingMode.HALF_UP), checkingAccount.sumTransactions().setScale(8,RoundingMode.HALF_UP));

    }

    @Test
    public void deposit() {
        checkingAccount.deposit(BigDecimal.ONE);
        assertEquals(new BigDecimal(1).setScale(8, RoundingMode.HALF_UP), checkingAccount.sumTransactions().setScale(8,RoundingMode.HALF_UP));
    }

    @Test
    public void transfer(){
        checkingAccount.deposit(BigDecimal.ONE);
        checkingAccount.transfer(BigDecimal.ONE, maxiSavings);
        assertEquals(new BigDecimal(0).setScale(8, RoundingMode.HALF_UP), checkingAccount.sumTransactions().setScale(8,RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(1).setScale(8, RoundingMode.HALF_UP), maxiSavings.sumTransactions().setScale(8, RoundingMode.HALF_UP));

    }

    @Test
    public void checkingAccountInterest() {
        checkingAccount.deposit(new BigDecimal(100.0));
        assertEquals(new BigDecimal(0.1).setScale(8, RoundingMode.HALF_UP), checkingAccount.interestEarned().setScale(8, RoundingMode.HALF_UP));
    }

    @Test
    public void savingsAccountInterest() {
        savingsAccount.deposit(new BigDecimal(1500.0));
        assertEquals(new BigDecimal(2.0).setScale(8, RoundingMode.HALF_UP), savingsAccount.interestEarned().setScale(8, RoundingMode.HALF_UP));
    }

    @Test
    public void maxiSavingsAccountInterest() {
        maxiSavings.deposit(new BigDecimal(3000.0));
        assertEquals(new BigDecimal(170).setScale(8, RoundingMode.HALF_UP), maxiSavings.interestEarned().setScale(8, RoundingMode.HALF_UP));
    }}
