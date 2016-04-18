package com.abc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Calendar;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }
    
    
    @Test
    public void getFirstCustomer(){
    	Bank bank = new Bank(); 
    	Customer anand = new Customer("Anand");
    	Customer sahu = new Customer("Sahu");
    	bank.addCustomer(anand);
    	bank.addCustomer(sahu);
    	
    	assertEquals("Anand", bank.getFirstCustomer());
    }
    
    
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);
        //Testing for annual interest accrued over a period of time i.e. 30 days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -7);
        checkingAccount.setInterestDaysAccrued(cal.getTime());
        checkingAccount.deposit(100.0);

        assertEquals(0.7, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    
    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -10);
        checkingAccount.setInterestDaysAccrued(cal.getTime());
        checkingAccount.deposit(1500.0);

        assertEquals(0.054794520547945205479452, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5);
        checkingAccount.setInterestDaysAccrued(cal.getTime());
        checkingAccount.deposit(3000.0);

        assertEquals(2.0547945205479452054, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
