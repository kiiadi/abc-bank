package com.abc;

import static java.lang.Math.abs;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abc.interfaces.CustomerDetail;

public class Customer implements CustomerDetail{
    private String name;
    private Map<Object, Account> m_accounts;

    public Customer(String name) {
        this.name = name;
        this.m_accounts = new LinkedHashMap<Object, Account>();
    }
    
    public Map<Object, Account>getAccounts(){
    	return m_accounts;
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        m_accounts.put(account.getAccountType()+"", account);
        return this;
    }

    public int getNumberOfAccounts() {
        return m_accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : m_accounts.values())
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : m_accounts.values()) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
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
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
