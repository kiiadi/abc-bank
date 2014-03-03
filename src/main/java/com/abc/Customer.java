package com.abc;

import com.abc.account.Account;
import com.abc.account.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer {

    private String name;
    private List<Account> accounts;

    private Customer() {
    }
    
    public Customer(String name) {
        
        if (name == null) {
            throw new IllegalArgumentException("Invalid name.");
        }
        
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public void openAccount(Account account) {

        if (account != null && account.getAccountType() != null) {
            accounts.add(account);
        } else {
            throw new IllegalArgumentException("Invalid customer.");
        }

    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {

        BigDecimal total = BigDecimal.ZERO.setScale(2);

        for (Account account : accounts) {
            total = total.add(account.interestEarned());
        }

        return total;
    }

    public String getStatement() {

        BigDecimal total = null;
        NumberFormat usdFormat = null;
        StringBuilder statement = new StringBuilder();

        statement.append("Statement for ");
        statement.append(name);
        statement.append("\n");

        total = BigDecimal.ZERO.setScale(2);

        for (Account account : accounts) {

            statement.append("\n");
            statement.append(account.statement());

            total = total.add(account.balance());
        }

        statement.append("\nTotal In All Accounts ");

        usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
        statement.append(usdFormat.format(total));
        /*
         following can be used for total but thats double iteration.
        
         statement.append(usdFormat.format( totalBalance() ) );
         */

        return statement.toString();

    }

    public BigDecimal totalBalance() {

        BigDecimal total = BigDecimal.ZERO.setScale(2);

        for (Account account : accounts) {
            total = total.add(account.balance());
        }

        return total;
    }
    
    public void balanceTransfer(Account fromAccount, Account toAccount, BigDecimal transferAmount) throws InsufficientFundsException {
        
        if (fromAccount.getId() == toAccount.getId()) {
            throw new IllegalArgumentException("Can not transfer within the same account.");
        }
        
        /*
        --- locking and rollback concerns.
        --- generally one would use a memento pattern here to ensure atomicity incase something goes wrong.
        --- a db tx would abviously make life easier.
        --- basic implementation.
        */
        
        synchronized(this) {
            fromAccount.withdraw(transferAmount);
            toAccount.deposit(transferAmount);
        }
        
    }

    public String getName() {
        return name;
    }
}
