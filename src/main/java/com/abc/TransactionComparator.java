package com.abc;

import java.util.Comparator;

/**
 * Created by dineshram on 2/28/15.
 */
public class TransactionComparator implements Comparator<Transaction>{
    @Override
    public int compare(Transaction transaction1, Transaction transaction2) {
        return transaction1.transactionDate.compareTo(transaction2.transactionDate);
    }
}
