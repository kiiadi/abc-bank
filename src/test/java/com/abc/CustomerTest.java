package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Ignore;
import org.junit.Test;


/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */



public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        // Account checkingAccount = new Account(AccountInterface.CHECKING);
        Account checkingAccount = new CheckingAccount();
        // Account savingsAccount = new Account(AccountInterface.SAVINGS);
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount( new SavingsAccount() ); // new Account(AccountInterface.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new   SavingsAccount() );  //Account(AccountInterface.SAVINGS));
        oscar.openAccount(new  CheckingAccount() ); 	// Account(AccountInterface.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }
    
    

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount( new SavingsAccount() ); 	// new Account(AccountInterface.SAVINGS));
        oscar.openAccount(new  CheckingAccount() );  // Account(AccountInterface.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test //Test customer statement generation
    public void testTransfer() {

        // Account checkingAccount = new Account(AccountInterface.CHECKING);
        Account checkingAccount = new CheckingAccount();
        // Account savingsAccount = new Account(AccountInterface.SAVINGS);
        Account savingsAccount = new SavingsAccount();

        Customer alex = new Customer("Alex").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        // savingsAccount.withdraw(200.0);
        
        try {
			alex.tansfer( savingsAccount, checkingAccount ,  1000.0 );
		
			assertEquals( scale( new BigDecimal( 1100.0)) , scale(checkingAccount.sumTransactions()) );
			assertEquals(scale( new BigDecimal(3000 )), scale( savingsAccount.sumTransactions()));
        } catch (Exception e) {
			// e.printStackTrace();
		}
        
        try {
 			alex.tansfer( checkingAccount, savingsAccount, 2000.0);
 			fail("This is not correct");
         } catch (Exception e) {
 			assert(true);
 			
 		} 
    }
    
    BigDecimal scale( BigDecimal num) {
    	return num.setScale(2, RoundingMode.HALF_EVEN);
    }
}
