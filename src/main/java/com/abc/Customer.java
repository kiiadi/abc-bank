package com.abc;

import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * A customer object with accounts and related convenience methods.
 *
 */
public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Instantiates a customer with the specifed name and as an owner of the provided accounts.
     * @param name
     * @param accounts
     */
    public Customer(String name, Account... accounts) {
        this(name);
        for (Account toAdd : accounts){
            openAccount(toAdd);
        }
    }

    public String getName() {
        return name;
    }

    /**
     * Opens an account of the specified type.
     * @param accountType
     * @return
     */
    public Customer openAccount(Account.AccountType accountType) {
        openAccount(accountType, 0.00);
        return this;
    }

    public Customer openAccount(Account toOpen) {
        if (toOpen!=null){
            accounts.add(toOpen);
        }
        return this;
    }

    /**
     * Opens an account of the specified type with the provided initial deposit.
     * @param accountType - type of the account to open.
     * @param initialDeposit - initial balance of the new account.
     * @return
     */
    public Customer openAccount(Account.AccountType accountType, double initialDeposit){
        switch (accountType) {
           default:
           case CHECKING:
               accounts.add(new CheckingAccount(initialDeposit));

               break;

           case SAVINGS:
               accounts.add(new SavingsAccount(initialDeposit));

               break;

           case MAXI_SAVINGS:
               accounts.add(new MaxiSavingsAccount(initialDeposit));

               break;
        }
        return this;
    }

    public int getNumberOfAccounts() {
        if(accounts != null){
            return accounts.size();
        }
        return 0;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Convenient access to inter account transfers.
     *
     * @param fromAccount - the account to withdraw from
     * @param toAccount - the account to deposit to.
     * @param amount - the amount to transfer.
     */
    public void transfer(Account fromAccount, Account toAccount, double amount){
        if(!fromAccount.equals(toAccount)){
            toAccount.transferFrom(fromAccount, amount);
        }
        throw new IllegalArgumentException("Accounts must be distinct from each other");
    }

    /**
     * The total amount of interest earned from all accounts.
     * @return
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.availableBalance();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account account) {
        StringBuilder output = new StringBuilder(account.accountType().getDesc()).append("\n");
        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : account.transactions()) {
            output.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }

        output.append("Total ").append(toDollars(total));
        return output.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
