package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING,  "Account-1"));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING,  "Account-1");
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    
    
    // missing test  Added by Henry
    @Test
    public void testNoCustomerSummary() {
        Bank bank = new Bank();      
        assertEquals("Customer Summary", bank.customerSummary());
    }
    
    @Test
    public void testNullCustomerBank() {
        Bank bank = new Bank();
        bank.addCustomer(null);      
        assertEquals("Customer Summary", bank.customerSummary());
    }
    
    
    @Test
    public void testGetFirstCustomerName() {
    	String expectedName = "John";
    	Bank bank = new Bank();
        Customer john = new Customer(expectedName);
        john.openAccount(new Account(Account.CHECKING,  "Account-1"));
        bank.addCustomer(john);
        String name = bank.getFirstCustomer();
        
        assertEquals("expected customer name is " + expectedName,  expectedName, name);
        
        Customer mark = new Customer("Mark");
        mark.openAccount(new Account(Account.CHECKING,  "Account-2"));
        bank.addCustomer(mark);
        
        Customer joe = new Customer("Joe");
        joe.openAccount(new Account(Account.CHECKING,  "Account-3"));
        bank.addCustomer(joe);
        
        assertEquals("expected customer name is " + expectedName,  expectedName, name);
        
    }
    
    @Test
    public void testGetFirstCustomerName_EmptyBank() {
    	Bank bank = new Bank();
        
        String name = bank.getFirstCustomer();
        
        assertEquals("expected customer name is null",  null, name);
    }
    
    @Test
    public void testNoAnyAccounts() {
        Bank bank = new Bank();
        bank.addCustomer(new Customer("Bill"));

        assertEquals(0.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testCheckingAccountForNoCustomer() {
        Bank bank = new Bank();
       
        assertEquals(0.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testCheckingAccountForMultipleCustomer() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING,  "Account-1");
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);
        
        checkingAccount = new Account(Account.CHECKING,  "Account-1");
        Customer john = new Customer("John").openAccount(checkingAccount);
        bank.addCustomer(john);
        
        checkingAccount.deposit(100.0);

        assertEquals(0.2, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testSavingsAccountMultipleCustomer() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);
        
        checkingAccount = new Account(Account.SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("John").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(4.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccountFirst1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1000.0);

        assertEquals(1.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testMaxiSavingsAccountFirst1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1000.0);

        assertEquals(20.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testMaxiSavingsAccountSecond1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS,  "Account-1");
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(2000.0);

        assertEquals(70.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testMixedAccounts() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING,  "Account-1");
        Customer customer = new Customer("Bill");
        checkingAccount.deposit(10000.0);
        bank.addCustomer(customer.openAccount(checkingAccount));
        
        Account savingAccount = new Account(Account.SAVINGS,  "Account-2");
        savingAccount.deposit(2000.0);
        customer.openAccount(savingAccount);
        
        Account maxiAccount = new Account(Account.MAXI_SAVINGS,  "Account-3");
        maxiAccount.deposit(3000.0);
        customer.openAccount(maxiAccount);
       
        assertEquals(183.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testCustomerSummaryForMutiplCustomers() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING,  "Account-1"));
        bank.addCustomer(john);
        
        Customer mark = new Customer("Mark");
        mark.openAccount(new Account(Account.CHECKING,  "Account-2"));
        bank.addCustomer(mark);

        assertEquals("Customer Summary\n - John (1 account)\n - Mark (1 account)", bank.customerSummary());
    }
    
    @Test
    public void testCustomerSummaryForMutiplAccounts() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING,  "Account-1")).openAccount(new Account(Account.SAVINGS,  "Account-2"));
        bank.addCustomer(john);
        
      
        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }
    
    @Test
    public void testCustomerSummaryForMutiplCustomersAndMutipleAccounts() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING,  "Account-1")).openAccount(new Account(Account.CHECKING,  "Account-2"));
        bank.addCustomer(john);
        
        Customer mark = new Customer("Mark");
        mark.openAccount(new Account(Account.CHECKING,  "Account-1")).openAccount(new Account(Account.CHECKING,  "Account-2"));;
        bank.addCustomer(mark);

        assertEquals("Customer Summary\n - John (2 accounts)\n - Mark (2 accounts)", bank.customerSummary());
    }
    
    @Test
    public void testTransferParameters(){
    	String errorTxt ="";
    	try {
    		Bank bank = new Bank();
    		bank.transfer("unkown", 5000, "from", "to");
    	} catch (IllegalArgumentException e) {
    		errorTxt = e.getMessage();
    	}
      
    	assertEquals("unkown does not have an account with us.", errorTxt);
    	
    	try {
    		Bank bank = new Bank();
    		Customer john = new Customer("John");
    		bank.addCustomer(john);
    		bank.transfer("John", 5000, "from", "to");
    	} catch (IllegalArgumentException e) {
    		errorTxt = e.getMessage();
    	}
      
    	assertEquals("account from does not exsits", errorTxt);
    	errorTxt ="";
    	
    	try {
    		Bank bank = new Bank();
    		Customer john = new Customer("John");
    		bank.addCustomer(john);
    		Account checking = new Account(Account.CHECKING,  "CHECKING");
    		john.openAccount(checking);
    		bank.transfer("John", 5000, "CHECKING", "to");
    		
    	} catch (IllegalArgumentException e) {
    		errorTxt = e.getMessage();
    	}
      
    	assertEquals("account to does not exsits", errorTxt);
    	errorTxt ="";
    	
    	try {
    		Bank bank = new Bank();
    		Customer john = new Customer("John");
    		bank.addCustomer(john);
    		Account checking = new Account(Account.CHECKING,  "CHECKING");
    		john.openAccount(checking);
    		Account savings = new Account(Account.CHECKING,  "SAVINGS");
    		john.openAccount(savings);
    		bank.transfer("John", -5000, "CHECKING", "SAVINGS");    		
    		
    	} catch (IllegalArgumentException e) {
    		errorTxt = e.getMessage();
    	}
      
    	assertEquals("amount must be greater than zero", errorTxt);
    }
    
    @Test
    public void testTransfer (){
    	Bank bank = new Bank();
        Customer john = new Customer("John");
        Account checking = new Account(Account.CHECKING,  "CHECKING");
        checking.deposit(1000.0);
        Account savings = new Account(Account.CHECKING,  "SAVINGS");
        savings.deposit(1000.0);
        john.openAccount(checking).openAccount(savings);
        
        bank.addCustomer(john);
        
        bank.transfer("John", 500.0, "SAVINGS", "CHECKING");
        
        assertEquals(1500.0, checking.sumTransactions(), DOUBLE_DELTA);
        assertEquals(500.0, savings.sumTransactions(), DOUBLE_DELTA);
    	
    }
    
}
