package com.abc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(AccountType.CHECKING, 2001);
        Account savingsAccount = new Account(AccountType.SAVINGS, 2002);

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
        Customer oscar = new Customer("Oscar").openAccount(new Account(AccountType.SAVINGS, 2003));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.SAVINGS, 2004));
        oscar.openAccount(new Account(AccountType.CHECKING, 2005));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new Account(AccountType.SAVINGS, 2006));
        oscar.openAccount(new Account(AccountType.CHECKING, 2007));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

	@Test
	public void testTransferFunds() {
        Account savingAc = new Account(AccountType.SAVINGS, 2008);
        Account checkingAc = new Account(AccountType.CHECKING, 2009);
        
		Customer guss = new Customer("Guss");
        guss.openAccount(savingAc);
		guss.openAccount(checkingAc);
		
		checkingAc.deposit(2000);
		
		try {
			guss.transferFunds(checkingAc.getAccountNumber(), savingAc.getAccountNumber(), 1000.00);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(1000, (int)savingAc.sumTransactions());
		
    }

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	public void testTransferFundsFail() {
        Account savingAc = new Account(AccountType.SAVINGS, 2010);
        Account checkingAc = new Account(AccountType.CHECKING, 2011);
        
		Customer kai = new Customer("Kai");
        kai.openAccount(savingAc);
		kai.openAccount(checkingAc);
		
		checkingAc.deposit(2000);
		
		try {
			int wrongAcNumber = 5010;
			exception.expect(Exception.class);
			kai.transferFunds(checkingAc.getAccountNumber(), wrongAcNumber, 1000.00);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(1000, (int)savingAc.sumTransactions());
		
    }
}
