package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new CopyOnWriteArrayList<Transaction>();
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount.negate()));
        }
    }

    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions().setScale(8, RoundingMode.HALF_UP);
        BigDecimal thousand = new BigDecimal(1000).setScale(8, RoundingMode.HALF_UP);
        BigDecimal twoThousand = new BigDecimal(2000).setScale(8, RoundingMode.HALF_UP);

        switch (accountType) {
            case SAVINGS:
                if (amount.compareTo(thousand) <= 0)
                    return amount.multiply( new BigDecimal(0.001));
                else
                    return BigDecimal.ONE.add(amount.subtract(thousand).multiply(new BigDecimal(0.002)));  // 1 + (amount - 1000) * 0.002
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount.compareTo(thousand) <= 0)
                    return amount.multiply(new BigDecimal(0.02));
                if (amount.compareTo(twoThousand) <= 0)
                    return new BigDecimal(20).add(amount.subtract(thousand).multiply(new BigDecimal(0.05)));  // 20 + (amount - 1000) * 0.05
                return new BigDecimal(70).add(amount.subtract(twoThousand).multiply(new BigDecimal(0.1))); //70 + (amount -2000) *0.1
            default:
                return amount.multiply(new BigDecimal(0.001));
        }
    }

    public BigDecimal sumTransactions() {
        return checkIfTransactionsExist(true);
    }

    private BigDecimal checkIfTransactionsExist(boolean checkAll) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Transaction t : transactions)
            amount = amount.add(t.amount);
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
