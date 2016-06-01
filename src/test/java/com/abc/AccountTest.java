package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

import com.abc.util.Utils;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void checkingAccountForYear() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.CHECKING, 1002);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0, Utils.getInstance().getPreviousDate(365).getTime());

        assertEquals(1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccountForYear() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.SAVINGS, 1003);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(100.0, Utils.getInstance().getPreviousDate(365).getTime());

        assertEquals(1.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccountForYear10k() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.SAVINGS, 1003);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(10000.0, Utils.getInstance().getPreviousDate(365).getTime());

        assertEquals(190.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccount() {
        Bank bank = new Bank();
        Account maxiAccount = new Account(AccountType.MAXI_SAVINGS, 1004);
        bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));

        long fiftyDaysAgoTimeMs = Utils.getInstance().getPreviousDate(50).getTime();
        
        maxiAccount.deposit(3000.0, fiftyDaysAgoTimeMs);
        
        long twentyDaysAgoTimeMs = Utils.getInstance().getPreviousDate(20).getTime();
        
        maxiAccount.deposit(2000.0, twentyDaysAgoTimeMs);

        assertEquals(5.21, maxiAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountEarlyWithdraw() {
        Bank bank = new Bank();
        Account maxiAccount = new Account(AccountType.MAXI_SAVINGS, 1005);
        bank.addCustomer(new Customer("Jose").openAccount(maxiAccount));

        maxiAccount.deposit(4000.0, Utils.getInstance().getPreviousDate(100).getTime());

        maxiAccount.withdraw(4000.0, Utils.getInstance().getPreviousDate(9).getTime());


        assertEquals(10.85, maxiAccount.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void maxiSavingsAccountForYear() {
        Bank bank = new Bank();
        Account maxiAccount = new Account(AccountType.MAXI_SAVINGS, 1006);
        bank.addCustomer(new Customer("Mike").openAccount(maxiAccount));

        maxiAccount.deposit(100.0, Utils.getInstance().getPreviousDate(365).getTime());
        
        assertEquals(5.00, maxiAccount.interestEarned(), DOUBLE_DELTA);
    }


}
