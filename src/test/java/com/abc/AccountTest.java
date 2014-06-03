package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;


/**
 * The Class AccountTest.
 */
public class AccountTest {
	
    /** The Constant DOUBLE_DELTA. */
    private static final double DOUBLE_DELTA = 1e-15;
    private static DateProvider dateProvider = null;
    
    static
    {
    	dateProvider = DateProvider.getInstance();
    }
    
    
    /**
     * test a savings deposit.
     *
     */    
	@Test
    public void testSavingsDeposit() {
		Account account = new Account(Account.SAVINGS);
		int retCode = account.deposit(200.0);
                  
        assertEquals(200.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.SAVINGS, account.getAccountType());
        assertEquals(retCode, 0);
    }
	
	
    /**
     * test a checking deposit.
     *
     */    
	@Test
    public void testCheckingDeposit() {
		Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(300.0);
                  
        assertEquals(300.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.CHECKING, account.getAccountType());
        assertEquals(retCode, 0);
    }
	
	
    /**
     * test a maxi_savings deposit.
     *
     */    
	@Test
    public void testMaxiSavingsDeposit() {
		Account account = new Account(Account.MAXI_SAVINGS);
		int retCode = account.deposit(400.0);
                  
        assertEquals(400.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.MAXI_SAVINGS, account.getAccountType());
        assertEquals(retCode, 0);
    }
	

    /**
     * test a savings withdrawal.
     *
     */    
	@Test
    public void testSavingsWithdrawal() {
		Account account = new Account(Account.SAVINGS);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		
		Date now = dateProvider.now();
		account.withdraw(200.0);                  
		assertEquals(account.getLastWithdrawalDate(), now);
        assertEquals(300.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.SAVINGS, account.getAccountType());
        assertEquals(retCode, 0);                               
    }
	
	
    /**
     * test a checking withdrawal.
     *
     */    
	@Test
    public void testCheckingWithdrawal() {
		Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		
		account.withdraw(300.0);                  
        assertEquals(200.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.CHECKING, account.getAccountType());
        assertEquals(retCode, 0);
    }
	
	
    /**
     * test a maxi_savings withdrawal.
     *
     */    
	@Test
    public void testMaxiSavingsWithdrawal() {
		Account account = new Account(Account.MAXI_SAVINGS);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		
		retCode = account.withdraw(400.0);                  
        assertEquals(100.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.MAXI_SAVINGS, account.getAccountType());
        assertEquals(retCode, 0);
    }
	
	
	/**
     * test statementForSavingsAccount
     *
     */    
	@Test
    public void testStatementForSavingsAccount() {
		Account account = new Account(Account.SAVINGS);
		@SuppressWarnings("unused")
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		
		StringBuffer acctBuffStr = new StringBuffer(); 
		account.statementForAccount(acctBuffStr, false);

		assertEquals("Savings Account\n deposit $500.00\nTotal $500.00 \n", acctBuffStr.toString());
    }
	
	
	/**
     * test statementForCheckingAccount
     *
     */    
	@Test
    public void testStatementForCheckingAccount() {
		Account account = new Account(Account.CHECKING);
		@SuppressWarnings("unused")
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		
		StringBuffer acctBuffStr = new StringBuffer(); 
		account.statementForAccount(acctBuffStr, false);

		assertEquals("Checking Account\n deposit $500.00\nTotal $500.00 \n", acctBuffStr.toString());
    }
	
	
	
	/**
     * test statementForMaxiSavingsAccount
     *
     */    
	@Test
    public void testStatementForMaxiSavingsAccount() {
		Account account = new Account(Account.MAXI_SAVINGS);
		@SuppressWarnings("unused")
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		
		StringBuffer acctBuffStr = new StringBuffer(); 
		account.statementForAccount(acctBuffStr, false);

		assertEquals("Maxi-Savings Account\n deposit $500.00\nTotal $500.00 \n", acctBuffStr.toString());
    }
		

    /**
     * test a negative withdrawal amount
     * 
     */   
    @Test
    public void testNegativeWithdrawalAmount() {
		Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		
		retCode = account.withdraw(-300.0);                  
		assertEquals(retCode, Account.AMOUNT_MUST_BE_GREATER_THAN_ZERO_ERROR);
		assertNull(account.getLastWithdrawalDate());
		
        assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.CHECKING, account.getAccountType());        
    }
    
    
    /**
     * test a negative deposit amount
     * 
     */   
    @Test
    public void testNegativeDepositAmount() {
		Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		
		retCode = account.deposit(-300.0);                  
		assertEquals(retCode, Account.AMOUNT_MUST_BE_GREATER_THAN_ZERO_ERROR);
		
        assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.CHECKING, account.getAccountType());        
    }
    
    
    /**
     * test a withdrawal against insufficient funds request
     * 
     */   
    @Test
    public void testWithdrawalAgainstInsufficientFunds() {
		Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		
		retCode = account.withdraw(600.0);                  
		assertEquals(retCode, Account.FUNDS_NOT_AVAILABLE_ERROR);
		
        assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
        assertEquals(Account.CHECKING, account.getAccountType());        
    }
    
    
    /**
     * test checking account compoundInterestEarned.
     */
    @Test
    public void testCheckingAccount() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(1500.0);
                
        assertEquals(1.5007481932059363, checkingAccount.compoundInterestEarned(), DOUBLE_DELTA);   //This works
        assertEquals("1.50", Account.toTenthes(checkingAccount.compoundInterestEarned()));        
    }
    
    
    /**
     * test Savings account compoundInterestEarned at the Threshold
     */
    @Test
    public void testSavingsAccountAtTheThreshold() {
        Account savingsAccount = new Account(Account.SAVINGS);
        
        double atTheThreshold = Account.SAVINGS_THRESHOLD;
        savingsAccount.deposit(atTheThreshold);
        
        assertEquals(1.0004987954705484, savingsAccount.compoundInterestEarned(), DOUBLE_DELTA);   //This works
        assertEquals("1.00", Account.toTenthes(savingsAccount.compoundInterestEarned()));
    }
    
    
    /**
     * test Savings account compoundInterestEarned when Zero
     */
    @Test
    public void testSavingsAccountAtZero() {
        
        Account savingsAccount = new Account(Account.SAVINGS);
                
        savingsAccount.deposit(0.0);

        assertEquals(0.0, savingsAccount.compoundInterestEarned(), DOUBLE_DELTA);    //This works
    }
    

    /**
     * test Savings account compoundInterestEarned below Threshold
     */
    @Test
    public void testSavingsAccountBelowThreshold() {
        
        Account savingsAccount = new Account(Account.SAVINGS);
        
        double belowThreshold = Account.SAVINGS_THRESHOLD - 200.0;
        savingsAccount.deposit(belowThreshold);
       
        assertEquals(0.8003990363764615, savingsAccount.compoundInterestEarned(), DOUBLE_DELTA);     //This works
        assertEquals("0.80", Account.toTenthes(savingsAccount.compoundInterestEarned()));
    }
    
    
    /**
     * test Savings account compoundInterestEarned above Threshold
     */
    @Test
    public void testSavingsAccountAboveThreshold() {
        
        Account savingsAccount = new Account(Account.SAVINGS);

        double aboveThreshold = Account.SAVINGS_THRESHOLD + 2000.0;
        savingsAccount.deposit(aboveThreshold);
        
        assertEquals(5.004490482640222, savingsAccount.compoundInterestEarned(), DOUBLE_DELTA);    //This works
        assertEquals("5.00", Account.toTenthes(savingsAccount.compoundInterestEarned()));
    }
    
    
    /**
     * test Max_savings_account compoundInterestEarned.
     */
    @Test
    public void testMaxiSavingsAccount() {
        
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(153.80248940234196, maxiSavingsAccount.compoundInterestEarned(), DOUBLE_DELTA);    //This Works
        assertEquals("153.80", Account.toTenthes(maxiSavingsAccount.compoundInterestEarned()));
    }
    
 	
    /**
     * Rebuild the balance after a few deposit transactions.
     *
     */   
    @Test
    public void testRebuildBalanceFromDepositTransactions() {
    	Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(500.0);
		retCode = account.deposit(200.0);
		retCode = account.deposit(100.0);
		assertEquals(800.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(800.0, account.rebuildBalanceFromTransactions(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		assertEquals(Account.CHECKING, account.getAccountType());   
        assertEquals(account.checkIfTransactionsExist(), true);        
    }
    
    
    /**
     * Rebuild the balance after a few withdrawal transactions.
     *
     */   
    @Test
    @SuppressWarnings("static-access")
    public void testRebuildBalanceFromWithdrawalTransactions() {		
    	Account account = new Account(Account.CHECKING);
    	int retCode = account.deposit(900.0);
		retCode = account.withdraw(400.0);
		retCode = account.withdraw(200.0);
    	Date now = dateProvider.now();          
		retCode = account.withdraw(100.0); 
		
		assertEquals(dateProvider.makeDateTimeStr(account.getLastWithdrawalDate()), dateProvider.makeDateTimeStr(now));
		assertEquals(200.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(200.0, account.rebuildBalanceFromTransactions(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		assertEquals(Account.CHECKING, account.getAccountType());   
        assertEquals(account.checkIfTransactionsExist(), true);        
    }
	

    /**
     * Check if checking transactions exist.
     *
     */
    @Test
    public void testIfCheckingTransactionsExist() {
		Account account = new Account(Account.CHECKING);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		assertEquals(Account.CHECKING, account.getAccountType());   
        assertEquals(account.checkIfTransactionsExist(), true);        
    }
    
    
    /**
     * Check if savings transactions exist.
     *
     */
    @Test
    public void testIfSavingsTransactionsExist() {
		Account account = new Account(Account.SAVINGS);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		assertEquals(Account.SAVINGS, account.getAccountType());   
        assertEquals(account.checkIfTransactionsExist(), true);        
    }
    
    
    /**
     * Check if maxi_savings transactions exist.
     *
     */
    @Test
    public void testIfMaxiSavingsTransactionsExist() {
		Account account = new Account(Account.MAXI_SAVINGS);
		int retCode = account.deposit(500.0);
		assertEquals(500.0, account.getBalance(), DOUBLE_DELTA);
		assertEquals(retCode, 0);
		assertEquals(Account.MAXI_SAVINGS, account.getAccountType());   
        assertEquals(account.checkIfTransactionsExist(), true);        
    }
    
    
    /**
     * Check toDollars
     *
     */
    @Test
    public void testToDollars() {
		String dollars = Account.toDollars(950.0);   
        assertEquals(dollars, "$950.00");        
    }
    
    
    /**
     * Round to nearest tenthes.
     *
     * @param d the d
     * @return the string representation of the input
     */
    public void testToTenthes() {
        String valStr1 = Account.toTenthes(90.001); 
        assertEquals(valStr1, "90.00");
        
        String valStr2 = Account.toTenthes(90.009); 
        assertEquals(valStr2, "90.01");
        
        String valStr3 = Account.toTenthes(99.999); 
        assertEquals(valStr3, "100.00");
    }
 

}
