package com.abc.impl.formatter;

import com.abc.model.api.ReportFormatter;
import com.abc.model.entity.Account;
import com.abc.model.entity.CustomerReport;
import com.abc.model.entity.Transaction;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultReportFormatter implements ReportFormatter {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String formatCustomerReportToBasicFormat(CustomerReport customerReport) {
        String formattedReport = "";

        formattedReport += createHeader(customerReport);
        formattedReport += LINE_SEPARATOR;
        formattedReport += LINE_SEPARATOR;
        formattedReport += createBody(customerReport);
        formattedReport += LINE_SEPARATOR;
        formattedReport += createFooter(customerReport);


        return formattedReport;
    }

    private String createHeader(CustomerReport customerReport) {
        return "Statement for " + customerReport.getCustomerName();
    }

    private String createBody(CustomerReport customerReport) {

        String body = "";

        List<CustomerReport.CustomerReportItem> customerReportItems = customerReport.getReportItems();
        for(int i = 0;i < customerReportItems.size();i++) {
            CustomerReport.CustomerReportItem customerReportItem = customerReportItems.get(i);
            body += createReportItem(customerReportItem);
            body += LINE_SEPARATOR + ((i < customerReportItems.size() - 1) ? LINE_SEPARATOR : "");
        }

        return body;
    }

    private String createReportItem(CustomerReport.CustomerReportItem customerReportItem) {
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
            default:
                throw new IllegalArgumentException(transaction.getType().toString());
        }

        return transactionType + " " + amountToReportableAmount(transaction.getAmount());
    }

    private String createFooter(CustomerReport customerReport) {
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
