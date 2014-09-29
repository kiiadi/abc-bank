package com.abc.customer;

import static org.junit.Assert.assertEquals;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.IAccount;
import com.abc.account.SavingsAccount;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestIndividualCustomer {
	
	private ICustomer individualCustomer;
	
	@Injectable
	private IAccount mockSavingsAccount;
	
	@Injectable
	private IAccount mockCheckingAccount;

	@Before
	public void setUp(){
		this.individualCustomer = new IndividualCustomer("C1");
	}

	@Test
	public void shouldGetCustomerId(){
		assertEquals("C1", individualCustomer.getId());
	}
	
	@Test
	public void shouldInitializeAccounts(){
		assertEquals(0, individualCustomer.getNumberOfAccounts());
		assertEquals(0, individualCustomer.getTotalInterestEarned(), 0);
	}
	
	@Test
	public void shouldSetIndividualInformation(){
		((IndividualCustomer) individualCustomer).setIndividualInformation(new IndividualInformation("firstName", "lastName"));
		assertEquals("lastName, firstName", individualCustomer.getDisplayName()); 
	}
	
	@Test
	public void shouldOpenAccount(){
		final IAccount savingsAccount = new SavingsAccount("S1");
		individualCustomer.openAccount(savingsAccount);
		assertEquals(1, individualCustomer.getNumberOfAccounts());
	}
	
	@Test
	public void shouldGetTotalInterestEarned(){
		new NonStrictExpectations(){{
			mockSavingsAccount.getInterestEarned();
			returns(100.00);
			mockCheckingAccount.getInterestEarned();
			returns(10.00);
		}};
		
		individualCustomer.openAccount(mockSavingsAccount);
		individualCustomer.openAccount(mockCheckingAccount);
		assertEquals(110.00, individualCustomer.getTotalInterestEarned(), 0);
		
		new Verifications() {{
			mockSavingsAccount.getInterestEarned();
			mockSavingsAccount.getInterestEarned();
		}};
	}
}
