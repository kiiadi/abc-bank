package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

/**
 * Unit test for {@link Customer}
 */
public class CustomerTest {
	private static final double DOUBLE_DELTA = 1e-15;

	/**
	 * Test customer statement generation.	
	 */
    @Test
    public void statement() {
        Account checkingAccount = new Account(AccountType.CHECKING);
        Account savingsAccount = new Account(AccountType.SAVINGS);

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        Date d1 = Account.getDateFromString("January 1, 2014");
		Date d2 = Account.getDateFromString("February 1, 2014");
		Date d3 = Account.getDateFromString("March 1, 2014");
        checkingAccount.deposit(100.0, d1);
        savingsAccount.deposit(4000.0, d2);
        savingsAccount.withdraw(200.0, d3);
        
        StringBuilder sb = new StringBuilder("Statement for Henry\n\n");
        sb.append("Checking Account (1 transaction)\n");
        sb.append( "  01/01/2014 deposit $100.00\n");
        sb.append("Total $100.00\n\n");
        sb.append("Saving Account (2 transactions)\n");
        sb.append("  02/01/2014 deposit $4,000.00\n");
        sb.append("  03/01/2014 withdrawal $200.00\n");
        sb.append("Total $3,800.00\n\n");
        sb.append("Total In All Accounts $3,900.00\n");
        
        assertTrue(sb.toString().equals(henry.getStatement()));
    }
    
    /**
	 * Test total interest earned  by customer.	
	 * Earned interests are calculated on daily basis.
	 */
    @Test
    public void totalInterestEarned(){
        Account checkingAccount = new Account(AccountType.CHECKING);
        Account savingsAccount = new Account(AccountType.SAVINGS);
        
        // Checking account has a flat rate of 0.1%.
     	double rate = InterestRates.CHECKING_RATE.getVal() / 365;
     	// Saving account has a rate of 0.1% for the first $1,000 then 0.2%.
     	double rateMax = InterestRates.SAVING_RATE.getVal() / 365;

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        
        /* 
		 * Test calculation for 1 month.
		 * We added $1000 to checking account on January, 1.
		 * We expect amount * 31 days * daily rate.
		 */
        Date d1 = Account.getDateFromString("January 1, 2014");
        Date d2 = Account.getDateFromString("February 1, 2014");
        checkingAccount.deposit(1000.0, d1);
        double expect = 1000.0 * 31 * rate;
		double earned = henry.totalInterestEarned(d2);		
		assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
		
		/* 
		 * Test calculation for 2 months.
		 * We added $4000 to saving account on February, 1.
		 * Earning is calculated with increased rate for balance above $1,000.
		 * We expect result as sum of the previous January and new February:
		 * - for Feb (checking account): 1000 * 28 days * daily rate;		
		 * - for Feb (saving account first 1000): 1000 * 28 days * daily rate;
		 * - for Feb (saving account above 1000): 3000 * 31 days * daily rate max;
		 * There can be some small dispersion in calculation results.
		 */        
		Date d3 = Account.getDateFromString("March 1, 2014");
        savingsAccount.deposit(4000.0, d2);
        expect += 1000.0 * 28 * rate;
        expect += 1000.0 * 28 * rate;
        expect += 3000.0 * 28 * rateMax;
        earned = henry.totalInterestEarned(d3);	
		assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
		
		/* 
		 * Test calculation for 3 months.
		 * We withdraw $2000 from saving account on March, 1.
		 * Earning is calculated with increased rate for balance above $1,000.
		 * We expect result as sum of the previous January and February plus new March:
		 * - for Mar (checking account): 100 * 31 days * daily rate;
		 * - for Mar (saving account first 1000): 1000 * 31 days * daily rate;
		 * - for Mar (saving account above 1000): 1000 * 31 days * daily rate max;
		 * There can be some small dispersion in calculation results.
		 */ 
		Date d4 = Account.getDateFromString("April 1, 2014");        
        savingsAccount.withdraw(2000.0, d3);
        expect += 1000.0 * 31 * rate;
        expect += 1000.0 * 31 * rate;
        expect += 1000.0 * 31 * rateMax;
        earned = henry.totalInterestEarned(d4);	
        assertTrue(Math.abs(earned - expect) <= DOUBLE_DELTA);
    }
    
    /**
     * Test transfer balances between two accounts.
     */
    @Test
    public void transfer() {    	
    	Account checkingAccount = new Account(AccountType.CHECKING);
        Account savingsAccount = new Account(AccountType.SAVINGS);
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        
        // Deposit $1000 to checking account and then transfer $500 to saving account.
        // We expect both account balances to be equal.
        checkingAccount.deposit(1000.0);
        oscar.transfer(500.0, checkingAccount, savingsAccount);
        assertTrue(checkingAccount.sumTransactions() == savingsAccount.sumTransactions());
        
    }
    
    /**
     * Test transfer balances between two accounts with wrong amount.
     */
    @Test(expected=IllegalArgumentException.class)
    public void badTransfer() {    	
    	Account checkingAccount = new Account(AccountType.CHECKING);
        Account savingsAccount = new Account(AccountType.SAVINGS);
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        
        // Deposit $1000 to checking account and then transfer $500 to saving account.
        // Try to transfer negative amount or amount that excess balance of checking account.
        // We expect error about overdraft protection be raised.
        checkingAccount.deposit(1000.0);
        oscar.transfer(-500.0, checkingAccount, savingsAccount); 
        oscar.transfer(500.0, checkingAccount, savingsAccount); 
        oscar.transfer(1500.0, checkingAccount, savingsAccount);
    }
    
    private void checkCustomer(Customer customer, String name, int number) {
    	assertTrue(name.equals(customer.getName()));
    	assertTrue(number == customer.getNumberOfAccounts());
    }

    /**
	 * Test customer creating and opening one account.
	 */
    @Test
    public void oneAccount(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new Account(AccountType.SAVINGS));
        checkCustomer(oscar, "Oscar", 1);
    }

    /**
	 * Test customer creating and opening two accounts.
	 */
    @Test
    public void twoAccount(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new Account(AccountType.SAVINGS));
        oscar.openAccount(new Account(AccountType.CHECKING));
        checkCustomer(oscar, "Oscar", 2);
    }

    /**
	 * Test customer creating and opening three accounts.
	 */
    @Ignore @Test 
    public void threeAcounts() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new Account(AccountType.SAVINGS));
        oscar.openAccount(new Account(AccountType.CHECKING));
        oscar.openAccount(new Account(AccountType.MAXI_SAVINGS));
        checkCustomer(oscar, "Oscar", 3);
    }
}
