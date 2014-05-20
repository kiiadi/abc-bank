package com.abc.interfaces;

import java.util.Date;

public interface AccountDetail {

	int getAccountType();
 

	double getBalacne();
	JournalEntry createNewTransaction(Date transactionDate, double amount, int transactionType);

}
