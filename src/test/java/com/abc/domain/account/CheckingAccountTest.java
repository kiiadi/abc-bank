package com.abc.domain.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public class CheckingAccountTest {

    @Test
    public void testInterest() {
        CheckingAccount one = new CheckingAccount();
        CheckingAccount another = new CheckingAccount();
        
        assertThat(one, is(another));
    }
    
	@Test
	public void testBalance() {
		Account account = new CheckingAccount();

		account.deposit(Money.dollars(1), TimePoint.at(2014, 3, 12, 20, 40));
		assertThat(account.balance(), is(Money.dollars(1)));
		
		account.deposit(Money.dollars(10), TimePoint.at(2014, 3, 12, 21, 40));
		assertThat(account.balance(), is(Money.dollars(11)));

		account.withdraw(Money.dollars(5), TimePoint.at(2014, 3, 12, 22, 40));
		assertThat(account.balance(), is(Money.dollars(6)));
		
		assertThat(account.entries().size(), is(3));
	}
	
	@Test(expected=InSufficientAmountException.class)
	public void testWithdrawInsuffienceAmount() throws Exception {
		Account account = new CheckingAccount();
		
		account.deposit(Money.dollars(9), TimePoint.at(2014, 3, 12, 20, 40));
		account.withdraw(Money.dollars(10), TimePoint.at(2014, 3, 12, 21, 40));
	}
}
