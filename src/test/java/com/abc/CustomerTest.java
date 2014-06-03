package com.abc;

//import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The Class CustomerTest.
 */
public class CustomerTest {
	
    /** The Constant DOUBLE_DELTA. */
    private static final double DOUBLE_DELTA = 1e-15;
    

    /**
     * Test Customer GetStatement with no interest earned shown
     */
    @Test 
    public void testCustomerGetStatement() {

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

        Customer henry = new Customer("Henry", 3);
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        henry.openAccount(maxiSavingsAccount);

        @SuppressWarnings("unused")
		int retCode = checkingAccount.deposit(100.0);
        retCode = savingsAccount.deposit(4000.0);        
        retCode = savingsAccount.withdraw(200.0);
        retCode = maxiSavingsAccount.deposit(5000.0);
        retCode = maxiSavingsAccount.withdraw(1000.0);

        assertEquals("Statement for Henry : 3\n" +
                "\n" +
                "Checking Account\n" +
                " deposit $100.00\n" +
                "Total $100.00 " +
                "\n\n\n" +
                "Savings Account\n" +
                " deposit $4,000.00\n" +
                " withdrawal $200.00\n" +
                "Total $3,800.00 " +
                "\n\n\n" +
                "Maxi-Savings Account\n" +
                " deposit $5,000.00\n" +
                " withdrawal $1,000.00\n" +                
                "Total $4,000.00 \n" +
                "\n\n" +
                "Total In All Accounts $7,900.00\nInterest Earned In All Accounts $10.71", henry.getStatement(false));
    }
    
    
    /**
     * Test Customer GetStatement with interest earned shown
     */
    @Test 
    public void testCustomerGetStatementWithInterestShown() {

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

        Customer henry = new Customer("Henry", 3);
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        henry.openAccount(maxiSavingsAccount);

        @SuppressWarnings("unused")
		int retCode = checkingAccount.deposit(100.0);
        retCode = savingsAccount.deposit(4000.0);        
        retCode = savingsAccount.withdraw(200.0);
        retCode = maxiSavingsAccount.deposit(5000.0);
        retCode = maxiSavingsAccount.withdraw(1000.0);

        assertEquals("Statement for Henry : 3\n" +
                "\n" +
                "Checking Account\n" +
                " deposit $100.00\n" +
                "Total $100.00 " +
                "Interest Earned $0.10" +
                "\n\n\n" +
                "Savings Account\n" +
                " deposit $4,000.00\n" +
                " withdrawal $200.00\n" +
                "Total $3,800.00 " +
                "Interest Earned $6.61" +
                "\n\n\n" +
                "Maxi-Savings Account\n" +
                " deposit $5,000.00\n" +
                " withdrawal $1,000.00\n" +
                "Total $4,000.00 " +
                "Interest Earned $4.00" +
                "\n\n\n" +
                "Total In All Accounts $7,900.00\n" +
                "Interest Earned In All Accounts $10.71", henry.getStatement(true));        
        }
    

    /**
     * Test one account.
     */
    @Test
    public void testOneAccount() { 
        Customer oscar = new Customer("Oscar", 4);
        
        Account checkingAccount = new Account(Account.CHECKING);
        
        oscar.openAccount(checkingAccount);
        
        assertEquals(1, oscar.getNumberOfAccounts());
    }
    

    /**
     * Test two account.
     */
    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar", 4);
        
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    
    /**
     * Test three accounts.
     */
    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar", 4); 
        
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount); 
        
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    
    /**
     * Test totalCompoundInterestEarned.
     */
    @Test
    public void testTotalCompoundInterestEarned() {
        Customer oscar = new Customer("Oscar", 4); 
        
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount); 
        
        @SuppressWarnings("unused")
		int retCode = checkingAccount.deposit(2000.0);
        retCode = checkingAccount.deposit(2000.0);
        retCode = checkingAccount.deposit(2000.0);
        
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(6000.0, oscar.getBalance(), DOUBLE_DELTA);
        
        assertEquals(6.002992772823745, oscar.totalCompoundInterestEarned(), DOUBLE_DELTA);   //This works
        assertEquals("6.00", Account.toTenthes(oscar.totalCompoundInterestEarned()));
    }
    
    
    /**
     * Test valid transfer from one account to another account.
     */
    @Test
    public void testValidTransfer() {
        Customer oscar = new Customer("Oscar", 4); 
        
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount);
        
        checkingAccount.deposit(1100);
        savingsAccount.deposit(900);
        maxiSavingsAccount.deposit(1000);
        
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(1100.0, checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(900.0, savingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, maxiSavingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(3000.0, (checkingAccount.getBalance() + savingsAccount.getBalance() + maxiSavingsAccount.getBalance()), DOUBLE_DELTA);
        assertEquals(3000.0, oscar.getBalance(), DOUBLE_DELTA);
        
        oscar.transfer(Account.CHECKING, Account.SAVINGS, 100.0);
        
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(1000.0, checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, savingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, maxiSavingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(3000.0, (checkingAccount.getBalance() + savingsAccount.getBalance() + maxiSavingsAccount.getBalance()), DOUBLE_DELTA); 
        assertEquals(3000.0, oscar.getBalance(), DOUBLE_DELTA);
    }
    
    
    /**
     * Test invalid transfer amount
     */
    @Test
    public void testInValidAmountTransfer() {
        Customer oscar = new Customer("Oscar", 4); 
        
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount);
        
        checkingAccount.deposit(1100);
        savingsAccount.deposit(900);
        maxiSavingsAccount.deposit(1000);
        
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(1100.0, checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(900.0, savingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, maxiSavingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(3000.0, (checkingAccount.getBalance() + savingsAccount.getBalance() + maxiSavingsAccount.getBalance()), DOUBLE_DELTA);
        assertEquals(3000.0, oscar.getBalance(), DOUBLE_DELTA);
        
        int retCode = oscar.transfer(Account.CHECKING, Account.SAVINGS, -1000.0);
        
        assertEquals(-1, retCode);
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(1100.0, checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(900.0, savingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, maxiSavingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(3000.0, (checkingAccount.getBalance() + savingsAccount.getBalance() + maxiSavingsAccount.getBalance()), DOUBLE_DELTA); 
        assertEquals(3000.0, oscar.getBalance(), DOUBLE_DELTA);
    }
    
    
    /**
     * Test transfer from one account to another account, with insufficient funds in Account1.
     */
    @Test
    public void testInsufficientFundsTransfer() {
        Customer oscar = new Customer("Oscar", 4); 
        
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount);
        
        checkingAccount.deposit(1100);
        savingsAccount.deposit(900);
        maxiSavingsAccount.deposit(1000);
        
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(1100.0, checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(900.0, savingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, maxiSavingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(3000.0, (checkingAccount.getBalance() + savingsAccount.getBalance() + maxiSavingsAccount.getBalance()), DOUBLE_DELTA);
        assertEquals(3000.0, oscar.getBalance(), DOUBLE_DELTA);
        
        int retCode = oscar.transfer(Account.CHECKING, Account.SAVINGS, 10000.0);
        
        assertEquals(-2, retCode);
        assertEquals(3, oscar.getNumberOfAccounts());
        assertEquals(1100.0, checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(900.0, savingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(1000.0, maxiSavingsAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(3000.0, (checkingAccount.getBalance() + savingsAccount.getBalance() + maxiSavingsAccount.getBalance()), DOUBLE_DELTA); 
        assertEquals(3000.0, oscar.getBalance(), DOUBLE_DELTA);
    }
    
    
    /**
     * test GetCustomerName
     */
    @Test
    public void testCustomerName() {  
        Customer customer = new Customer("Frank", 893); 
        
        assertEquals(customer.getCustomerName(), "Frank");
    }
    
    
    /**
     * test GetCustomerId
     */
    @Test
    public void testCustomerId() {  
        Customer customer = new Customer("Frank", 893); 
        
        assertEquals(customer.getCustomerId(), 893);
    }
    
    
    /**
     * test checkIfTransactionsExist is false
     */
    @Test
    public void testCheckIfTransactionsExistIsFalse() {  
        Customer customer = new Customer("Frank", 893); 
        
        assertFalse(customer.checkIfTransactionsExist());
    }
    
    
    /**
     * test checkIfTransactionsExist is true
     */
    @Test
    public void testCheckIfTransactionsExistIsTrue() {  
        Customer customer = new Customer("Frank", 893);
        
        Account savingsAccount = new Account(Account.SAVINGS);
        customer.openAccount(savingsAccount);
        
        savingsAccount.deposit(230.00);
        
        assertTrue(customer.checkIfTransactionsExist());
    }
    
  }
