package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        try {
        	 Account checkingAccount = AccountFactory.newAccount(AccountType.CHECKING);
             Account savingsAccount = AccountFactory.newAccount(AccountType.SAVINGS);
             
             Customer henry = new Customer.Builder("henry").add(checkingAccount).add(savingsAccount).build();
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
		                "Total In All Accounts $3,900.00", StatementGenerator.print(henry));
        } catch (InvalidAccountTransaction e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } catch (InvalidAccount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
    }

    @Test
    public void testOneAccount(){
		try {
			Customer oscar = new Customer.Builder("Oscar").add(AccountFactory.newAccount(AccountType.SAVINGS)).build();
			assertEquals(1, oscar.getNumberOfAccounts());
		} catch (InvalidAccount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testTwoAccount(){
    	try {
        Customer oscar = new Customer.Builder("Oscar").add(AccountFactory.newAccount(AccountType.SAVINGS)).build();
        oscar.openAccount(AccountFactory.newAccount(AccountType.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    	} catch (InvalidAccount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Ignore
    public void testThreeAcounts() {
    	try {
            Customer oscar = new Customer.Builder("Oscar").add(AccountFactory.newAccount(AccountType.SAVINGS)).build();
            oscar.openAccount(AccountFactory.newAccount(AccountType.CHECKING));
            oscar.openAccount(AccountFactory.newAccount(AccountType.MAXI_SAVINGS));
            assertEquals(3, oscar.getNumberOfAccounts());
        	} catch (InvalidAccount e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
}
}
