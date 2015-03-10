package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        // john.openAccount(new Account(AccountInterface.CHECKING));
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        // Account checkingAccount = new Account(AccountInterface.CHECKING);
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        // assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
        assertEquals(scale( new BigDecimal(0.1)), scale( bank.totalInterestPaid()));
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        // Account checkingAccount = new Account(AccountInterface.SAVINGS);
        Account checkingAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        // assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
        assertEquals( scale( new BigDecimal(2.0) ), scale( bank.totalInterestPaid() ));
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        // Account checkingAccount = new Account(AccountInterface.MAXI_SAVINGS);
        Account checkingAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        // assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
        assertEquals( scale( new BigDecimal(170.0 ) ), scale( bank.totalInterestPaid() ));
    }
    
    BigDecimal scale( BigDecimal num) {
    	return num.setScale(2, RoundingMode.HALF_EVEN);
    }

}
