package com.abc;

import java.math.BigDecimal;

/**
 * A statement of account.
 */
public class AccountStatementCreator {

    public static final String WITHDRAWAL = "withdrawal";
    public static final String DEPOSIT = "deposit";

    public String create(final Account account) {
        final StringBuilder builder = new StringBuilder(account.getAccountType().getTitle()).append("\n");

        for (Transaction transaction : account.getTransactions()) {
            builder
                .append(transaction.amount.compareTo(BigDecimal.ZERO) < 0 ? WITHDRAWAL : DEPOSIT)
                .append(" ")
                .append(StringUtils.getDollarString(transaction.amount))
                .append("\n");
        }

        builder
            .append("Total ")
            .append(StringUtils.getDollarString(account.getBalance()));

        return builder.toString();
    }
}
