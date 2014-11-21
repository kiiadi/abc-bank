package com.abc;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class AccountTest {

	@Test
	public void testInterestEarned() throws Exception {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date start_date = formatter.parse("11/21/13");
		Account z_maxi_savings_acct = new Account(Account.MAXI_SAVINGS, start_date);
		z_maxi_savings_acct.setTransactionList(createTransactions());
		assertEquals(5, (int)z_maxi_savings_acct.interestEarned());
	}

	private List<Transaction> createTransactions() throws Exception{
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction_now = new Transaction(50);
		Transaction ten_day_transaction = new Transaction(50, 11);
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("11/01/14");
		Transaction last_transaction = new Transaction(50, date);
		transactions.add(ten_day_transaction);
		transactions.add(last_transaction);
		return transactions;
	}
	
	@Test
	public void testInterestEarnedDaily() throws Exception{
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date start_date = formatter.parse("11/21/13");
		Account z_checking_acct = new Account(Account.CHECKING, start_date);
		z_checking_acct.deposit(1000);
		assertEquals(1, (int)z_checking_acct.interestEarned());
	}
}
