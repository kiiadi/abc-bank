package com.abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.abc.Customer.Builder;

public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAccount() {
		Account acct;
		try {
			acct = AccountFactory.newAccount(AccountType.CHECKING);
			assert(acct instanceof CheckingAccount);
			acct = AccountFactory.newAccount(AccountType.SAVINGS);
			assert(acct instanceof SavingsAccount);
			acct = AccountFactory.newAccount(AccountType.MAXI_SAVINGS);
			assert(acct instanceof MaxiSavingsAccount);
			acct = AccountFactory.newAccount(AccountType.SUPER_SAVINGS);
			assert(acct instanceof SuperSavingsAccount);
		} catch (InvalidAccount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testXferTo() {
		try {
			Account chkAcct = new CheckingAccount() ;
			Account savingsAcct = new SavingsAccount() ;
			
			Customer cust = new Customer.Builder("mani").add(chkAcct).add(savingsAcct).build();
			
			chkAcct.deposit(100.0);
			
			cust.xferMoney(chkAcct, savingsAcct, 50.0);
			
			assertEquals(savingsAcct.getCurrBalance(),50.0,DOUBLE_DELTA) ;
			
		} catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testDeposit() {
		Account chkAcct = new CheckingAccount() ;
		try {
			chkAcct.deposit(50.0);
			assertEquals(chkAcct.getCurrBalance(),50.0,DOUBLE_DELTA) ;
		} catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testWithdraw() {
		Account chkAcct = new CheckingAccount() ;
		try {
			chkAcct.deposit(50.0);
			chkAcct.withdraw(25.0);
			assertEquals(chkAcct.getCurrBalance(),25.0,DOUBLE_DELTA) ;
		} catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInterestEarned() {
		Account chkAcct = new CheckingAccount() ;
		try {
			chkAcct.deposit(50.0);
			chkAcct.deposit(25.0);
			assertEquals(chkAcct.interestEarned(),0.025,DOUBLE_DELTA) ;
			/* More cases needed for other accounts */
		} catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCurrBalance() {
		Account chkAcct = new CheckingAccount() ;
		try {
			chkAcct.deposit(50.0);
			chkAcct.withdraw(25.0);
			assertEquals(chkAcct.getCurrBalance(),25.0,DOUBLE_DELTA) ;
		} catch (InvalidAccountTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetType() {
		Account act = new SavingsAccount();
		assertEquals(act.getType(),"Checking");
	}

	@Test
	public void testTransactions() {
		try {
			Account act = new CheckingAccount() ;
			assertEquals(act.transactions().size(),0);
			act.deposit(10.0);
			assertEquals(act.transactions().size(),1);
			act.withdraw(10.0);
			assertEquals(act.transactions().size(),2);
		} catch(Exception ex) {
			
		}
	}

}
