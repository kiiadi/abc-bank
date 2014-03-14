package com.abc.domain.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.TimePoint;

public class MaxiSavingAccountTest {

	@Test
	public void testInterestNeverWithdraw() throws Exception {
		MaxiSavingsAccount account = new MaxiSavingsAccount();
		account.deposit(Money.dollars(100), TimePoint.now());
		
		assertThat(account.interest(), is(Money.dollars(5)));
	}
	
	@Test
	public void testInterestWithdraw11DaysAgo() throws Exception {
		MaxiSavingsAccount account = new MaxiSavingsAccount();
		account.deposit(Money.dollars(110), TimePoint.at(2014, 3, 1, 9, 0));
		account.withdraw(Money.dollars(10), TimePoint.at(2014, 3, 1, 10, 0));
		
		account.now = TimePoint.at(2014, 3, 12, 10, 0);
		
		assertThat(account.balance(), is(Money.dollars(100)));
		assertThat(account.interest(), is(Money.dollars(5)));
	}
	
	@Test
	public void testInterestWithdraw10DaysAgo() throws Exception {
		MaxiSavingsAccount account = new MaxiSavingsAccount();
		account.deposit(Money.dollars(110), TimePoint.at(2014, 3, 1, 10, 0));
		account.withdraw(Money.dollars(10), TimePoint.at(2014, 3, 2, 10, 0));
		
		account.now = TimePoint.at(2014, 3, 12, 10, 0);
		
		assertThat(account.balance(), is(Money.dollars(100)));
		assertThat(account.interest(), is(Money.dollars(0.1)));
	}
	
	@Test
	public void testFindLastWithdrawal() throws Exception {
		MaxiSavingsAccount maxi = new MaxiSavingsAccount();
		maxi.deposit(Money.dollars(25), TimePoint.at(2014, 3, 8, 10, 0));
		maxi.withdraw(Money.dollars(15), TimePoint.at(2014, 3, 9, 10, 0));
		maxi.withdraw(Money.dollars(5), TimePoint.at(2014, 3, 10, 10, 0));
		maxi.deposit(Money.dollars(15), TimePoint.at(2014, 3, 11, 10, 0));
		
		assertThat(maxi.getLastWithdrawalDay(), is(DayOfYear.at(2014, 3, 10)));
	}
}
