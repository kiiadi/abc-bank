package com.abc.unittests;

import com.abc.impl.formatter.DefaultReportFormatter;
import com.abc.impl.manager.DefaultAccountManager;
import com.abc.impl.manager.DefaultReportManager;
import com.abc.model.api.AccountManager;
import com.abc.model.api.ReportFormatter;
import com.abc.model.api.ReportManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.CustomerReport;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class ReportsTest {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private ReportManager reportManager = new DefaultReportManager();
    private ReportFormatter reportFormatter = new DefaultReportFormatter();
    private AccountManager accountManager = new DefaultAccountManager();
    private Customer customer1;

    @Before
    public void setUpCustomers() {

        customer1 = new Customer("Customer 1");
        Account checkingAccountCustomer1 = accountManager.openCheckingAccount(customer1,"Checking Account 1");
        Account savingsAccountCustomer1 = accountManager.openSavingsAccount(customer1,"Savings Account 1");
        Account maxiSavingsAccountCustomer1 = accountManager.openMaxiSavingsAccount(customer1,"Maxi Savings Account 1");

        accountManager.depositMoneyToAccount(checkingAccountCustomer1,new BigDecimal(100));
        accountManager.depositMoneyToAccount(savingsAccountCustomer1, new BigDecimal(4000));
        accountManager.withdrawMoneyFromAccount(savingsAccountCustomer1, new BigDecimal(200));
        accountManager.depositMoneyToAccount(maxiSavingsAccountCustomer1, new BigDecimal(100.55));

    }

    @Test
    public void generateCustomerReport() {

        CustomerReport customerReport = reportManager.createCustomerReport(customer1);

        assertEquals(customer1.getAccounts().size(), customerReport.getReportItems().size());
        for(Account customerAccount : customer1.getAccounts()) {
            assertTrue(isAccountOnReport(customerAccount, customerReport));
        }

    }

    @Test
    public void basicFormatOfCustomerReport() {

        CustomerReport customerReport = reportManager.createCustomerReport(customer1);
        String customerReportBasicFormat = reportFormatter.formatCustomerReportToBasicFormat(customerReport);


        assertEquals("Statement for Customer 1" + LINE_SEPARATOR +
                LINE_SEPARATOR +
                "Checking Account (Checking Account 1)" + LINE_SEPARATOR +
                "  deposit $100.00" + LINE_SEPARATOR +
                "Total $100.00" + LINE_SEPARATOR +
                LINE_SEPARATOR +
                "Savings Account (Savings Account 1)" + LINE_SEPARATOR +
                "  deposit $4,000.00" + LINE_SEPARATOR +
                "  withdrawal $200.00" + LINE_SEPARATOR +
                "Total $3,800.00" + LINE_SEPARATOR +
                LINE_SEPARATOR +
                "Maxi-Savings Account (Maxi Savings Account 1)" + LINE_SEPARATOR +
                "  deposit $100.55" + LINE_SEPARATOR +
                "Total $100.55" + LINE_SEPARATOR +
                LINE_SEPARATOR +
                "Total In All Accounts $4,000.55", customerReportBasicFormat);
    }


    private boolean isAccountOnReport(Account account, CustomerReport customerReport) {

        for(CustomerReport.CustomerReportItem customerReportItem : customerReport.getReportItems()) {

            Account accountOnReport = customerReportItem.getAccount();
            if(account.getName().equals(accountOnReport.getName())) {
                return true;
            }

        }

        return false;
    }

}
