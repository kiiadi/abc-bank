package com.abc.account.transaction;

import com.abc.DateProvider;
import java.math.BigDecimal;
import java.text.NumberFormat;

import java.util.Locale;

public class Transaction {
    
    private BigDecimal amount = null;
    private long transactionDate;
    private TransactionType type = null;

    private Transaction() {
    }
    
    public Transaction(BigDecimal amount) {
        
        int amountZeroCompare = amount.compareTo(BigDecimal.ZERO);
        
        if (0 == amountZeroCompare) {
            throw new IllegalArgumentException("A transaction amount can not be zero.");
        } else if (amountZeroCompare > 0) {
            this.type = TransactionType.CREDIT;
        } else {
            this.type = TransactionType.DEBIT;
        }
        
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    public String statement() {
        
        NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
        StringBuilder statement = new StringBuilder();
        
        statement.append(" ");
        statement.append(type.getDescription());
        statement.append(" ");
        statement.append(usdFormat.format(amount));
        statement.append("\n");
        
        return statement.toString();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public long getTransactionDate() {
        return transactionDate;
    }
    
    public TransactionType getType () {
        return type;
    }

}
