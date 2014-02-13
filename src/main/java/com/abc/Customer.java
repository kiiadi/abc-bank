package com.abc;

import java.util.List;

public class Customer {
    public String getName() {
        return name;
    }

    private String name;
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    Customer(String name, List<Account> accounts) {

        this.name = name;
        this.accounts = accounts;
    }


    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double getTotalInterestEarned() {
        double totalInterestEarned = 0.0;
        for (Account account : accounts) {

            totalInterestEarned += account.getInterestEarned();
        }

        return totalInterestEarned;

    }


    private void getStatementForAllAccounts() {
        System.out.println("Customer Name : " + getName());
        double totalBalance = 0.0;
        for (Account account : accounts) {

            account.getStatement();
            totalBalance += account.getBalance();
        }
        System.out.println("Total Balance = " + totalBalance);

    }


}
