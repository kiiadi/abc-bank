package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

/**
 * Unit test for {@link Bank}
 */
public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    /**
	 * Test bank summary info.	
	 */
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    private Account getCustomerWithAccount(Bank bank, String name, AccountType type) {
    	Account account = new Account(type);
    	Customer customer = new Customer(name);
    	customer.openAccount(account);
    	bank.addCustomer(customer);
    	return account;
    }
    
    /**
	 * Test earned interest for a checking account.	
	 * Earned interests are calculated on daily basis.
	 */
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account account = getCustomerWithAccount(bank, "Bill", AccountType.CHECKING); 
        // Checking account has a flat rate of 0.1%.
     	double rate = InterestRates.CHECKING_RATE.getVal() / 365;

        /* 
		 * Test calculation for 1 month.
		 * We added $100 to checking account on January, 1.
		 * We expect amount * 31 days * daily rate.
		 */
        Date d1 = Account.getDateFromString("January 1, 2014");
        Date d2 = Account.getDateFromString("February 1, 2014");
        account.deposit(100.0, d1);
        double expect = 100.0 * 31 * rate;
		double earned = bank.totalInterestPaid(d2);		
		assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
    }

    /**
	 * Test earned interest for a saving account.	
	 * Earned interests are calculated on daily basis.
	 */
    @Test
    public void savingsAccount() {
        Bank bank = new Bank();
        Account account = getCustomerWithAccount(bank, "Bill", AccountType.SAVINGS); 
        // Account has a rate of 0.1% for the first $1,000 then 0.2%.
     	double rate = InterestRates.CHECKING_RATE.getVal() / 365;
     	double rateMax = InterestRates.SAVING_RATE.getVal() / 365;

        /* 
		 * Test calculation for 1 month.
		 * We added $1500 to saving account on January, 1.
		 * We expect amount * 31 days * daily rate for the first $1000
		 * plus amount * 31 days * daily rate max for the remaining balance.
		 */
        Date d1 = Account.getDateFromString("January 1, 2014");
        Date d2 = Account.getDateFromString("February 1, 2014");
        account.deposit(1500.0, d1);
        double expect = 1000.0 * 31 * rate;
        expect += 500.0 * 31 * rateMax;
		double earned = bank.totalInterestPaid(d2);		
		assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
    }

    /**
	 * Test earned interest for a maxi-saving account.	
	 * Earned interests are calculated on daily basis.
	 */
    @Test
    public void maxiSavingsAccount() {
        Bank bank = new Bank();
        Account account = getCustomerWithAccount(bank, "Bill", AccountType.MAXI_SAVINGS); 
        // Account has an interest rate of 5% assuming no withdrawals in the past 10 days
        // otherwise 0.1%.
     	double rateMax = InterestRates.MAXI_SAVING_RATE.getVal() / 365;

     	/* 
		 * Test calculation for 1 month.
		 * We added $3000 to max-saving account on January, 1.
		 * We expect amount * 31 days * daily rate max assuming no withdraws for the in the past 10 days
		 * plus amount * 31 days * daily rate max for the remaining balance.
		 */
        Date d1 = Account.getDateFromString("January 1, 2014");
        Date d2 = Account.getDateFromString("February 1, 2014");
        account.deposit(3000.0, d1);
        double expect = 3000.0 * 31 * rateMax;
		double earned = bank.totalInterestPaid(d2);		
		assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
    }
    
    /**
	 * Test earned interest for a multi-accounts.	
	 * Earned interests are calculated on daily basis.
	 */
    @Test
    public void multiAccount() {
    	Bank bank = new Bank();
        Account checkingAccount = getCustomerWithAccount(bank, "Bill", AccountType.CHECKING); 
        Account savingAccount = getCustomerWithAccount(bank, "Bill", AccountType.SAVINGS); 
        // Checking account has a flat rate of 0.1%.
        // Saving account has a rate of 0.1% for the first $1,000 then 0.2%.
     	double rate = InterestRates.CHECKING_RATE.getVal() / 365;
     	double rateMax = InterestRates.SAVING_RATE.getVal() / 365;
     	
     	/* 
		 * Test calculation for 1 month.
		 * On January, 1 we added $2000 to checking account 
		 * and $3000 to saving account.
		 * We expect result as sum of the following:
		 * - for checking account: $2000 * 31 days * daily rate;
		 * - for first $1000 in saving account: $1000 * 31 days * daily rate;
		 * - for remaining balance in saving account: $2000 * 31 days * daily rate max;
		 */
     	Date d1 = Account.getDateFromString("January 1, 2014");
        Date d2 = Account.getDateFromString("February 1, 2014");
        checkingAccount.deposit(1000.0, d1);
        savingAccount.deposit(3000.0, d1);
        double expect = 1000.0 * 31 * rate;
        expect += 1000.0 * 31 * rate;
        expect += 2000.0 * 31 * rateMax;
		double earned = bank.totalInterestPaid(d2);		
		assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
    }

}
