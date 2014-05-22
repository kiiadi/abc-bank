package com.abc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class BankTest {

    @Test
    public void customerSummaryOneCustomer() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        john.openAccount(checkingAccount);
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummaryTwoCustomers() {
        Bank bank = new Bank();
        BigDecimal startingBalance = new BigDecimal("500");
        
        Customer john = new Customer("John");
        Account checkingAccount = new CheckingAccount(startingBalance);
        john.openAccount(checkingAccount);
        bank.addCustomer(john);

        Customer carl = new Customer("Carl");
        Account savingsAccount = new SavingsAccount(startingBalance);
        carl.openAccount(savingsAccount);
        bank.addCustomer(carl);
        
        assertEquals("Customer Summary\n - John (1 account)\n - Carl (1 account)", 
        		bank.customerSummary());
    }
    
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(new BigDecimal("100.0"));

        assertEquals(new BigDecimal("0.60").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(bank.totalInterestPaid()).getAmount());
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        BigDecimal startingBalance = new BigDecimal("500");
        Account savingsAccount = new SavingsAccount(startingBalance);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(new BigDecimal("1500.0"));

        assertEquals(new BigDecimal(3.0).setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(bank.totalInterestPaid()).getAmount());
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        BigDecimal startingBalance = new BigDecimal("500");
        Account maxiSavingsAccount = new MaxiSavingsAccount(startingBalance);
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));
        maxiSavingsAccount.deposit(new BigDecimal("3000.0"));
        assertEquals(new BigDecimal("3.5").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(bank.totalInterestPaid()).getAmount());
    }

    @Test(expected = NullPointerException.class)
    public void testFirstCustomer() {
        Bank bank = new Bank();
        bank.getFirstCustomer();
    }
    
}
