


package com.abc;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountActivityTest {

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
	public void testGetCurrBalance() {
		AccountActivity act = new AccountActivity(0.0) ;
		assertEquals(act.getCurrBalance(),0.0,DOUBLE_DELTA) ;
		act.processTransaction(TransactionType.Deposit, 10);
		assertEquals(act.getCurrBalance(),10.0,DOUBLE_DELTA) ;
		act.processTransaction(TransactionType.Withdrawal, 10);
		assertEquals(act.getCurrBalance(),0.0,DOUBLE_DELTA) ;
		
	}

	@Test
	public void testAccountActivity() {
		fail("Not yet implemented");
	}

	@Test
	public void testProcessTransaction() {
		AccountActivity act = new AccountActivity(0.0) ;
		assertEquals(act.transactions().size(),0);
		act.processTransaction(TransactionType.Deposit, 10);
		assertEquals(act.transactions().size(),1);
		act.processTransaction(TransactionType.Withdrawal, 10);
		assertEquals(act.transactions().size(),2);
	}

	@Test
	public void testTransactions() {
		AccountActivity act = new AccountActivity(0.0) ;
		act.processTransaction(TransactionType.Deposit, 10);
		assertEquals(act.transactions().size(),1);
	}
	
	@Test
	public void testParallelUpdate() {
		Account acct = new CheckingAccount() ;
		
		int noOfTherads = 10 ;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(noOfTherads);
		
		class LatchedThread implements Runnable {
			CountDownLatch startSignal ;
			CountDownLatch endSignal ;
			Account acct ;
			public LatchedThread(CountDownLatch start, CountDownLatch end, Account acct) {
				super();
				// TODO Auto-generated constructor stub
				this.startSignal = start ;
				this.endSignal = end ;
				this.acct = acct ;
			}
			public void run() {
				// TODO Auto-generated method stub
				try {
					startSignal.await();
					for ( int i = 0 ; i < 1000 ; i++ ) {
						if ( (i % 2) == 0 ) {
							acct.deposit(i-1);
						} else {
							acct.withdraw(i);
						}
					}
					endSignal.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidAccountTransaction e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
			
			for ( int i = 0 ; i < noOfTherads ; i++  ) {
				Runnable r = new LatchedThread(startSignal, endSignal, acct);
			}
			
			startSignal.countDown();
			endSignal.await();
			
			assertEquals(acct.transactions().size(),1000*noOfTherads);
			assertEquals(acct.getCurrBalance(),0.0,DOUBLE_DELTA);
			
		}
		
		
		
		
	}

}
