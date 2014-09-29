package com.abc.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mockit.Deencapsulation;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.interest.calculator.IInterestCalculator;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestInterestBehavior {
	
	@Injectable
	private IInterestCalculator maxSavingsAccountInterestCalculator;
	
	@Injectable
	private IInterestCalculator savingsAccountInterestCalculator;
	
	@Injectable
	private IInterestCalculator checkingAccountInterestCalculator;
	
	private IAccount checkingAccount;
	
	private IAccount savingsAccount;
	
	private IAccount maxSavingsAccount;
	
	@Before
	public void setUp(){
		this.checkingAccount = new CheckingAccount();
		this.savingsAccount = new SavingsAccount();
		this.maxSavingsAccount = new MaxSavingsAccount();
		Deencapsulation.setField(checkingAccount, "interestCalculator", checkingAccountInterestCalculator);
		Deencapsulation.setField(savingsAccount, "interestCalculator", savingsAccountInterestCalculator);
		Deencapsulation.setField(maxSavingsAccount, "interestCalculator", maxSavingsAccountInterestCalculator);
	}
	
	@Test
	public void shouldGetEarnedInterestOfCheckingAccount(){
		new NonStrictExpectations() {{
			checkingAccountInterestCalculator.calculate((IAccount) any);
			returns(1.00);
		}};
		
		assertEquals(1.00, checkingAccount.getInterestEarned(), 0);
		
		new Verifications() {{
			IAccount account;
			checkingAccountInterestCalculator.calculate(account = withCapture());
			assertTrue(account instanceof CheckingAccount);
			assertEquals(0.0, account.getBalance(), 0);
		}};
	}
	
	
	@Test
	public void shouldGetEarnedInterestOfSavingsAccount(){
		new NonStrictExpectations() {{
			savingsAccountInterestCalculator.calculate((IAccount) any);
			returns(10.00);
		}};
		
		assertEquals(10.00, savingsAccount.getInterestEarned(), 0);
		
		new Verifications() {{
			IAccount account;
			savingsAccountInterestCalculator.calculate(account = withCapture());
			assertTrue(account instanceof SavingsAccount);
			assertEquals(0.0, account.getBalance(), 0);
		}};
	}
	
	@Test
	public void shouldGetEarnedInterestOfMaxSavingsAccount(){
		new NonStrictExpectations() {{
			maxSavingsAccountInterestCalculator.calculate((IAccount) any);
			returns(111.00);
		}};
		
		assertEquals(111.00, maxSavingsAccount.getInterestEarned(), 0);
		
		new Verifications() {{
			IAccount account;
			maxSavingsAccountInterestCalculator.calculate(account = withCapture());
			assertTrue(account instanceof MaxSavingsAccount);
			assertEquals(0.0, account.getBalance(), 0);
		}};
	}

}
