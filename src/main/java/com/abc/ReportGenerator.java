package com.abc;

import java.util.List;

import static java.lang.Math.abs;

/**
 * Utility class to generate various types of reports and statements across bank, customers and accounts
 */
public class ReportGenerator {

    /**
     * Prints the summary of the customer's accounts
     * @param bank the bank object
     * @return string value of the customer's summary
     */
    public static String generateCustomerSummary(Bank bank) {
        List<Customer> customers = bank.getCustomers();
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    /**
     * Returns the correct plural of the word is created based on the number passed.
     * If number passed is 1, then it returns the word, otherwise adds an 's' at the end
     * @param number number to determine the plural of the word
     * @param word the actual word itself
     * @return the singular or plural value
     */
    private static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     * Generates the statement for the customer including the account types and the balances
     * @param customer the customer for whom the statement is to be generated
     * @return the statement as a string
     */
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

    /**
     * Generates the account statement including the type of account and the transactions in the account
     * @param a the account object
     * @return the account statement as a string
     */
    public static String generateAccountStatement(Account a) {
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
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    /**
     * Formats the dollar value
     * @param d the dollar value as a double
     * @return the formatted value as a string
     */
    private static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
