package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The Class BankTest.
 */
public class BankTest {
    
    /** The Constant DOUBLE_DELTA. */
    private static final double DOUBLE_DELTA = 1e-15;
    
    
    /**
     * GetBankName
     */
    @Test
    public void testGetBankName() {  
        Bank bank = new Bank("ABC-BANK"); 
        
        assertEquals(bank.getBankName(), "ABC-BANK");
    }
    
    
    /**
     * test valid addCustomer
     */
    @Test
    public void testAddValidCustomer() {  
        Bank bank = new Bank("ABC-BANK");        	
        Customer bill = new Customer("Bill", 2);
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, 0);
    }
    
    
    /**
     * test null addCustomer
     */
    @Test
    public void testAddNullCustomer() {  
        Bank bank = new Bank("ABC-BANK");        	
        Customer bill = null;
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, -1);
    }
    
       
    /**
     * Checking_account.
     */
    @Test
    public void testCheckingAccount() {  
        Bank bank = new Bank("ABC-BANK");        	
        Customer bill = new Customer("Bill", 2);
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, 0);

        Account checkingAccount = new Account(Account.CHECKING);              
        bill.openAccount(checkingAccount);
        checkingAccount.deposit(1500.0);
        
        assertEquals(1.5007481932059363, bank.totalCompoundInterestPaid(), DOUBLE_DELTA);   //This works
        assertEquals("1.50", Account.toTenthes(bank.totalCompoundInterestPaid()));          
    }
    
    
    /**
     * Savings account at the Threshold
     */
    @Test
    public void testSavingsAccountAtTheThreshold() {
    	Bank bank = new Bank("ABC-BANK");
    	Customer oscar = new Customer("Oscar", 4); 
    	int retCode = bank.addCustomer(oscar);
         
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
         
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount); 
         
        assertEquals(retCode, 0);
        double atTheThreshold = Account.SAVINGS_THRESHOLD;
        savingsAccount.deposit(atTheThreshold);
        
        assertEquals(1.0004987954705484, bank.totalCompoundInterestPaid(), DOUBLE_DELTA);   //This works
        assertEquals("1.00", Account.toTenthes(bank.totalCompoundInterestPaid()));
    }
    
    
    /**
     * Savings account at Zero
     */
    @Test
    public void testSavingsAccountAtZero() {
        Bank bank = new Bank("ABC-BANK");
        Customer bill = new Customer("Bill", 3);       
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, 0);
        
        Account savingsAccount = new Account(Account.SAVINGS);
        bill.openAccount(savingsAccount);
                
        savingsAccount.deposit(0.0);

        assertEquals(0.0, bank.totalCompoundInterestPaid(), DOUBLE_DELTA);    //This works
    }
    

    /**
     * Savings account below Threshold
     */
    @Test
    public void testSavingsAccountBelowThreshold() {
        Bank bank = new Bank("ABC-BANK");
        Customer bill = new Customer("Bill", 3);       
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, 0);
        
        Account savingsAccount = new Account(Account.SAVINGS);
        bill.openAccount(savingsAccount);
        
        double belowThreshold = Account.SAVINGS_THRESHOLD - 200.0;
        savingsAccount.deposit(belowThreshold);
       
        assertEquals(0.8003990363764615, bank.totalCompoundInterestPaid(), DOUBLE_DELTA);     //This works
        assertEquals("0.80", Account.toTenthes(bank.totalCompoundInterestPaid()));
    }
    
    
    /**
     * Savings account above Threshold
     */
    @Test
    public void testSavingsAccountAboveThreshold() {
        Bank bank = new Bank("ABC-BANK");
        Customer bill = new Customer("Bill", 3);       
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, 0);
        
        Account savingsAccount = new Account(Account.SAVINGS);
        bill.openAccount(savingsAccount);

        double aboveThreshold = Account.SAVINGS_THRESHOLD + 2000.0;
        savingsAccount.deposit(aboveThreshold);
        
        assertEquals(5.004490482640222, bank.totalCompoundInterestPaid(), DOUBLE_DELTA);    //This works
        assertEquals("5.00", Account.toTenthes(bank.totalCompoundInterestPaid()));
    }
    
    
    /**
     * Max_savings_account.
     */
    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank("ABC-BANK");        
        Customer bill = new Customer("Bill", 4);
        int retCode = bank.addCustomer(bill);
        
        assertEquals(retCode, 0);
        
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        bill.openAccount(maxiSavingsAccount);

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(153.80248940234196, bank.totalCompoundInterestPaid(), DOUBLE_DELTA);    //This Works
        assertEquals("153.80", Account.toTenthes(bank.totalCompoundInterestPaid()));
    }
    
    
    /**
     * Customer summary More than one Account.
     */
    @Test
    public void testCustomerSummaryMoreThanOneAccount() {
        Bank bank = new Bank("ABC-BANK");
        Customer john = new Customer("John", 4);
        int retCode = bank.addCustomer(john);
        
        assertEquals(retCode, 0);
        
        Account savingsAccount = new Account(Account.SAVINGS);
        Account checkingAccount = new Account(Account.CHECKING);        
        
        john.openAccount(checkingAccount);
        john.openAccount(savingsAccount);
              
        assertEquals("Customer Summary\n - customerName: John : 4 (2 accounts)\nTotal Interest earned: 0.0", bank.customerSummary());
    }
    
    
    /**
     * Customer summary One Account.
     */
    @Test
    public void testCustomerSummaryOneAccount() {
        Bank bank = new Bank("ABC-BANK");
        Customer john = new Customer("John", 4);
        int retCode = bank.addCustomer(john);
        
        assertEquals(retCode, 0);
        
        Account checkingAccount = new Account(Account.CHECKING);
        john.openAccount(checkingAccount);        
        
        assertEquals("Customer Summary\n - customerName: John : 4 (1 account)\nTotal Interest earned: 0.0", bank.customerSummary());

    }


}
