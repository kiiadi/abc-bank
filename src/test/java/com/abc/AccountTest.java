package com.abc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class AccountTest {

    @Test
    public void testDeposit() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.deposit(new BigDecimal("1100"));

        assertEquals(new BigDecimal("1600").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(checkingAccount.getBalance()).getAmount());

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositAmountEqualToZero() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.deposit(new BigDecimal("0"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositAmountIsNegative() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.deposit(new BigDecimal("-300"));
    }
    
    @Test
    public void testWithdraw() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("100"));
        assertEquals(new BigDecimal("400").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(checkingAccount.getBalance()).getAmount());

    }
   
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawAmountEqualToZero() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("0"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawAmountIsNegative() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("-300"));
    } 
    
    @Test(expected = IllegalArgumentException.class)
    public void testBalanceIsLessThanAmount() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("700"));
    } 
    
    @Test
    public void testSumTransacrions() {
        BigDecimal startingBalance = new BigDecimal(500);
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("100"));
        checkingAccount.withdraw(new BigDecimal("200"));
        checkingAccount.deposit(new BigDecimal("1100"));
        assertEquals(new BigDecimal("1300").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(checkingAccount.sumTransactions()).getAmount());

    }
 
    @Test
    public void testTransferFromBalance() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("100"));
     
        Account savingsAccount = new SavingsAccount(startingBalance);
        savingsAccount.deposit(new BigDecimal("400.0"));
        savingsAccount.withdraw(new BigDecimal("200.0"));

        checkingAccount.transfer(savingsAccount, new BigDecimal("300.0"));
        
        assertEquals(new BigDecimal("100").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(checkingAccount.sumTransactions()).getAmount());

    }
    
    @Test
    public void testTransferToBalance() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        checkingAccount.withdraw(new BigDecimal("100"));
     
        Account savingsAccount = new SavingsAccount(startingBalance);
        savingsAccount.deposit(new BigDecimal("400.0"));
        savingsAccount.withdraw(new BigDecimal("200.0"));

        checkingAccount.transfer(savingsAccount, new BigDecimal("300.0"));
        
        assertEquals(new BigDecimal("1000").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(savingsAccount.sumTransactions()).getAmount());

    }

    @Test
    public void testMSAInterestEarnedWithdrawPastTenDays() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account maxiSavingsAccount = new MaxiSavingsAccount(startingBalance);
        maxiSavingsAccount.withdraw(new BigDecimal("100"));
        maxiSavingsAccount.deposit(new BigDecimal("400.0"));
        maxiSavingsAccount.withdraw(new BigDecimal("200.0"));
     
        assertEquals(new BigDecimal("0.6").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(maxiSavingsAccount.interestEarned()).getAmount());

    }

    @Test
    public void testMSAInterestEarnedNoWithdrawPastTenDays() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account maxiSavingsAccount = new MaxiSavingsAccount(startingBalance);
        maxiSavingsAccount.deposit(new BigDecimal("400.0"));
     
        assertEquals(new BigDecimal("0.9").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(maxiSavingsAccount.interestEarned()).getAmount());

    }
    
    @Test
    public void testMSAInterestEarnedWithdrawMoreThanTenDays() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account maxiSavingsAccount = new MaxiSavingsAccount(startingBalance, 33);
        maxiSavingsAccount.withdraw(new BigDecimal("100"), 20);
        maxiSavingsAccount.deposit(new BigDecimal("400.0"), 30);
        maxiSavingsAccount.withdraw(new BigDecimal("200.0"), 11);
     
        assertEquals(new BigDecimal("30").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(maxiSavingsAccount.interestEarned()).getAmount());

    }
}
