package com.abc;

import java.math.BigDecimal;

/**
 * Creator of statements.
 */
public class CustomerStatementCreator {

    public String create(final CustomerImpl customer) {
        String statement = "Statement for " + customer.getName() + "\n";

        BigDecimal total = BigDecimal.ZERO;

        for (Account account : customer.getAccounts()) {
            statement += "\n" + account.getStatement() + "\n";
            total = total.add(account.getBalance());
        }

        statement += "\nTotal In All Accounts " + StringUtils.getDollarString(total);

        return statement;
    }

}
