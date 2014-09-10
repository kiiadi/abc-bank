package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
        
        john.openAccount(new MaxiSavingsAccount());
        
        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

    @Test
    public void checkingAccount()  {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        try {
        	Account acct = bill.openAccount(AccountType.CHECKING);
        	acct.deposit(100.0);
        }
        catch(InvalidAccount ex) {
        	ex.printStackTrace();
        } catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        bank.addCustomer(bill);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
    	Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        try {
        	Account acct = bill.openAccount(AccountType.SAVINGS);
        	acct.deposit(1500.0);
        }
        catch(InvalidAccount ex) {
        	ex.printStackTrace();
        } catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        bank.addCustomer(bill);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
    	Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        try {
        	Account acct = bill.openAccount(AccountType.SAVINGS);
        	acct.deposit(3000.0);
        }
        catch(InvalidAccount ex) {
        	ex.printStackTrace();
        } catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        bank.addCustomer(bill);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
