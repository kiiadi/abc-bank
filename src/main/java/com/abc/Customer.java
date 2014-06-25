package com.abc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private Map<Integer,Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new HashMap<Integer,Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.put(account.getAccountNumber(),account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts.values())
            total = total.add(a.interestEarned());
        return total;
    }

    public Account getAccount(Integer accountNo) {
        return accounts.get(accountNo);
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts.values()) {
            statement += "\n" + statementForAccount(a) + "\n";
            total = total.add(a.getCurrentBalance());
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    public void transferBalance(Account fromAccount, Account toAccount, BigDecimal amount) {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);


    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case CHECKING:
                s += "Checking Account\n";
                break;
            case SAVINGS:
                s += "Savings Account\n";
                break;
            case MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.getTransactionType().equals(TransactionType.DEBIT) ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
        }
        s += "Total " + toDollars(a.getCurrentBalance());
        return s;
    }

    private String toDollars(BigDecimal d){
        return String.format("$%,.2f", abs(d.doubleValue()));
    }
}
