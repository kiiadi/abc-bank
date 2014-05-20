package com.abc.interfaces;

import java.util.Date;

public interface JournalEntry {

	Date getTransactionDate();

	int getTransactionType();

	double getAmount();
	double getTransactionAmount();
}
