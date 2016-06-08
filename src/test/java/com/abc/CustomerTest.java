package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

    	BaseAccount checkingAccount = new  CheckingAccount(1005);
    	BaseAccount savingsAccount = new SavingsAccount(1006);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0,TransactionType.DEPOSIT);
        savingsAccount.deposit(4000.0,TransactionType.DEPOSIT);
        savingsAccount.withdraw(200.0,TransactionType.DEPOSIT);

  /*      assertEquals("Statement for Henry\n" +
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
                "Total In All Accounts $3,900.00", henry.getStatement());*/
        
       
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount(1009));
      //  assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount(1010));
        oscar.openAccount(new CheckingAccount(1011));
     //   assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount(1012));
        oscar.openAccount(new CheckingAccount(1014));
      //  assertEquals(3, oscar.getNumberOfAccounts());
    }
}
