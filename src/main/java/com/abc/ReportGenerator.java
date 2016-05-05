package com.abc;

import java.util.List;

import static java.lang.Math.abs;

public class ReportGenerator {

    public static String generateCustomerSummary(Bank bank) {
        List<Customer> customers = bank.getCustomers();
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public static String generateCustomerStatement(Customer customer) {
        String name = customer.getName();
        List<Account> accounts = customer.getAccounts();
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + generateAccountStatement(a) + "\n";
            total += a.getAccountBalance();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private static String generateAccountStatement(Account a) {
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

    private static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
