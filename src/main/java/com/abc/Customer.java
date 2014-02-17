package com.abc;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer implements Comparable<Customer>{
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Account openAccount(int accountType) {
        Account acc =  new Account(accountType);
        accounts.add(acc);
        return acc;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.getAccountBalance();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions()) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    public void deposit(long accountNumber, double amount){
//        TODO now this is not the best way, HashMap would be better , need refactoring
        for(Account acc:accounts){
            if(accountNumber == acc.getAccountNumber()) acc.deposit(amount);
        }
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    @Override
    public boolean equals(Object customer){
        return compareTo((Customer)customer)==0;
    }

    @Override
    public int hashCode(){
      return 9*name.hashCode();
    }

    public int compareTo(Customer customer){
        return name.compareTo(customer.name);
    }
}
