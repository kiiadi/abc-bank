package com.abc;
import java.math.BigDecimal;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerTest {
    @Test //Testing customer statement generation
    public void testApp(){
        String newSavAccountNumber="293220419";
        String newChkAccountNumber="293220229";
        Account checkingAccount = new CheckingAccount(newChkAccountNumber);
        Account savingsAccount = new SavingAccount(newSavAccountNumber);
        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal("100.0"));
        try {
			checkingAccount.withdraw(new BigDecimal("50.0"));
	        savingsAccount.deposit(new BigDecimal("4000.0"));
	        savingsAccount.withdraw(new BigDecimal("200.0"));

		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertEquals("Statement for Henry\n" +"\n"+"Checking Account\n" +"  deposit $100.00\n" +"  withdrawal ($50.00)\n" +"Total $50.00\n" +
                "\n" +"Savings Account\n" +"  deposit $4,000.00\n" +"  withdrawal ($200.00)\n" +"Total $3,800.00\n" +"\n" +"Total In All Accounts $3,850.00", henry.getStatement());
    }

    @Test //Testing customer transfer from one acc to another
    public void testAccountTransfer(){
        String newSavAccountNumber="113220419";
        String newChkAccountNumber="223220229";
        Account checkingAccount = new CheckingAccount(newChkAccountNumber);
        Account savingsAccount = new SavingAccount(newSavAccountNumber);
        Customer cust = new Customer("Michael").openAccount(checkingAccount).openAccount(savingsAccount);
        checkingAccount.deposit(new BigDecimal("1000.0"));
        savingsAccount.deposit(new BigDecimal("4500.0"));
        try {
			cust.TransferBetweenAccounts(checkingAccount, savingsAccount,new BigDecimal("500.0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
                
        assertEquals("Statement for Michael\n" +"\n" +"Checking Account\n" +"  deposit $1,000.00\n" +"  withdrawal ($500.00)\n" +"Total $500.00\n" +
                "\n" +"Savings Account\n" +"  deposit $4,500.00\n" +"  deposit $500.00\n" +"Total $5,000.00\n" +"\n" +"Total In All Accounts $5,500.00", cust.getStatement());
    }

    @Test //Testing customer transfer from one acc to another
    public void testAccountWithdrawNegativeTestForInsufficientBalance(){
        String newSavAccountNumber="113220419";
        boolean thrown = false;
        
        Account savingsAccount = new SavingAccount(newSavAccountNumber);

        Customer cust = new Customer("Bob").openAccount(savingsAccount);

        savingsAccount.deposit(new BigDecimal("4500.0"));
        BigDecimal expectedBalance = new BigDecimal("4500.0");
        
        try {
			savingsAccount.withdraw(new BigDecimal("5000.0"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			thrown = true;
		}
                
        assertTrue(thrown);
        assertTrue(expectedBalance.compareTo(savingsAccount.sumTransactions()) ==0);
        
    }

    @Test //Testing customer transfer from one acc to another
    public void testAccountTransferNegativeTestIncorrectAmount(){
        String newSavAccountNumber="113220419";
        String newChkAccountNumber="223220229";
        Account checkingAccount = new CheckingAccount(newChkAccountNumber);
        Account savingsAccount = new SavingAccount(newSavAccountNumber);
        boolean thrown = false;
        
        Customer cust = new Customer("Bob").openAccount(savingsAccount).openAccount(checkingAccount);

        savingsAccount.deposit(new BigDecimal("6500.0"));
        checkingAccount.deposit(new BigDecimal("200"));
        
        BigDecimal savExpectedBalance = new BigDecimal("6500.0");
        BigDecimal chkExpectedBalance = new BigDecimal("200.0");
        
        try {
			cust.TransferBetweenAccounts(checkingAccount, savingsAccount, new BigDecimal("5000.0"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			thrown = true;
		}
                
        assertTrue(thrown);
        assertTrue(savExpectedBalance.compareTo(savingsAccount.sumTransactions()) ==0);
        assertTrue(chkExpectedBalance.compareTo(checkingAccount.sumTransactions()) ==0);        
    }
    
    @Test
    public void testOneAccount(){
        String newAccountNumber="293220489";
    	
        Customer oscar = new Customer("Oscar").openAccount(new SavingAccount(newAccountNumber));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        String newSavAccountNumber="293220419";
    	
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(newSavAccountNumber));
        
        String newChkAccountNumber="293220489";
        oscar.openAccount(new CheckingAccount(newChkAccountNumber));
        
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        String newSavAccountNumber="293220419";
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(newSavAccountNumber));
        
        String newChkAccountNumber="293220489";
        oscar.openAccount(new CheckingAccount(newChkAccountNumber));
        
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
