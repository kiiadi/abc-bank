package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
	
    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        henry.transfer(checkingAccount, savingsAccount, 40.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  transfer out $40.00\n" +
                "Total $60.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "  transfer in $40.00\n" +
                "Total $3,840.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingsAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test(expected=IllegalArgumentException.class) 
    public void testTransfer1() {
    	Account savingAccount = new SavingsAccount();
    	savingAccount.deposit(100);
    	Account checkingAccount = new CheckingAccount();
    	checkingAccount.deposit(40);
    	
    	Customer oscar = new Customer("Oscar").openAccount(savingAccount);
        oscar.openAccount(checkingAccount);
        oscar.transfer(savingAccount, checkingAccount, -50);
    }
    
    @Test(expected=IllegalArgumentException.class) 
    public void testTransfer2() {
    	Account savingAccount = new SavingsAccount();
    	savingAccount.deposit(100);
    	Account checkingAccount = new CheckingAccount();
    	checkingAccount.deposit(40);
    	
    	Customer oscar = new Customer("Oscar").openAccount(savingAccount);
        //oscar.openAccount(checkingAccount);
        oscar.transfer(savingAccount, checkingAccount, -50);
    }
    
    @Test 
    public void testTransfer3() {
    	Account savingAccount = new SavingsAccount();
    	savingAccount.deposit(100);
    	Account checkingAccount = new CheckingAccount();
    	checkingAccount.deposit(40);
    	
    	Customer oscar = new Customer("Oscar").openAccount(savingAccount);
        oscar.openAccount(checkingAccount);
        oscar.transfer(savingAccount, checkingAccount, 50);
    	
        assertEquals(50, savingAccount.calculateTotal(), DOUBLE_DELTA);

        assertEquals(90, checkingAccount.calculateTotal(), DOUBLE_DELTA);
    	
    }
 
}
