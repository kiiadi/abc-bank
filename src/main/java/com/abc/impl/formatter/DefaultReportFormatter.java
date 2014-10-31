package com.abc.impl.formatter;

import com.abc.model.api.ReportFormatter;
import com.abc.model.entity.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by alexandr koller on 31/10/2014.
 */

//this is a good candidate for further refactor so the different reports are not so mingled
public class DefaultReportFormatter implements ReportFormatter {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String formatInterestAmountPaidReport(InterestAmountPaidReport interestAmountPaidReport) {
        return "Total interest paid across all accounts: " +
                amountToReportableAmount(interestAmountPaidReport.getTotalInterestPaid());
    }

    @Override
    public String formatCustomersAccountsReport(CustomersAccountsReport customersAccountsReport) {
        String formattedReport = "";

        formattedReport += createCustomersAccountsReportHeader();
        formattedReport += LINE_SEPARATOR;
        formattedReport += createCustomersAccountsReportBody(customersAccountsReport);

        return formattedReport;
    }

    private String createCustomersAccountsReportHeader() {
        return "Customer Summary";
    }

    private String createCustomersAccountsReportBody(CustomersAccountsReport customersAccountsReport) {
        String body = "";

        List<Customer> customers = customersAccountsReport.getCustomers();
        for(int i = 0;i < customers.size();i++) {
            body += createCustomersAccountsReportItem(customers.get(i));
            body += i < customers.size() - 1 ? LINE_SEPARATOR : "";
        }

        return body;
    }

    private String createCustomersAccountsReportItem(Customer customer) {
        int numberOfAccounts = customer.getAccounts().size();
        return String.format("- %s (%d account%s)",customer.getName(),numberOfAccounts,numberOfAccounts == 1 ? "" : "s");
    }

    @Override
    public String formatCustomerReport(CustomerReport customerReport) {
        String formattedReport = "";

        formattedReport += createCustomerReportHeader(customerReport);
        formattedReport += LINE_SEPARATOR;
        formattedReport += LINE_SEPARATOR;
        formattedReport += createCustomerReportBody(customerReport);
        formattedReport += LINE_SEPARATOR;
        formattedReport += createCustomerReportFooter(customerReport);


        return formattedReport;
    }

    private String createCustomerReportHeader(CustomerReport customerReport) {
        return "Statement for " + customerReport.getCustomerName();
    }

    private String createCustomerReportBody(CustomerReport customerReport) {

        String body = "";

        List<CustomerReport.CustomerReportItem> customerReportItems = customerReport.getReportItems();
        for(int i = 0;i < customerReportItems.size();i++) {
            CustomerReport.CustomerReportItem customerReportItem = customerReportItems.get(i);
            body += createCustomerReportItem(customerReportItem);
            body += LINE_SEPARATOR + ((i < customerReportItems.size() - 1) ? LINE_SEPARATOR : "");
        }

        return body;
    }

    private String createCustomerReportItem(CustomerReport.CustomerReportItem customerReportItem) {
        String reportItem = createAccountTitleLine(customerReportItem) + LINE_SEPARATOR;

        for(Transaction transaction : customerReportItem.getAccount().getTransactions()) {
            reportItem += "  " + createTransactionDescription(transaction);
            reportItem += LINE_SEPARATOR;
        }

        reportItem += createAccountClosingLine(customerReportItem);

        return reportItem;
    }

    private String createAccountTitleLine(CustomerReport.CustomerReportItem customerReportItem) {

        return customerReportItem.getAccount().getAccountType() + " (" + customerReportItem.getAccount().getName() + ")";

    }

    private String createTransactionDescription(Transaction transaction) {
        String transactionType;

        switch (transaction.getType()) {
            case CREDIT:
                transactionType = "deposit";
                break;
            case DEBIT:
                transactionType = "withdrawal";
                break;
            case INTEREST:
                transactionType = "interest";
                break;
            default:
                throw new IllegalArgumentException(transaction.getType().toString());
        }

        return transactionType + " " + amountToReportableAmount(transaction.getAmount());
    }

    private String createCustomerReportFooter(CustomerReport customerReport) {
        return "Total In All Accounts " + amountToReportableAmount(calculateTotalAmountInAllAccounts(customerReport));
    }

    private String createAccountClosingLine(CustomerReport.CustomerReportItem customerReportItem) {
        return "Total " + amountToReportableAmount(customerReportItem.getAccount().getBalance());
    }

    private String amountToReportableAmount(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount);
    }

    private BigDecimal calculateTotalAmountInAllAccounts(CustomerReport customerReport) {
        BigDecimal totalInAllAccounts = new BigDecimal("0");
        for(CustomerReport.CustomerReportItem customerReportItem : customerReport.getReportItems()) {
            totalInAllAccounts = totalInAllAccounts.add(customerReportItem.getAccount().getBalance());
        }
        return totalInAllAccounts;
    }

}
