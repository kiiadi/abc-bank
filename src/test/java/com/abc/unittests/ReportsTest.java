package com.abc.unittests;

import com.abc.impl.DefaultCustomerManager;
import com.abc.impl.formatter.DefaultReportFormatter;
import com.abc.impl.DefaultAccountManager;
import com.abc.impl.DefaultReportManager;
import com.abc.model.api.AccountManager;
import com.abc.model.api.CustomerManager;
import com.abc.model.api.ReportFormatter;
import com.abc.model.api.ReportManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.CustomerReport;
import static org.junit.Assert.*;

import com.abc.model.entity.CustomersAccountsReport;
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
    private CustomerManager customerManager = new DefaultCustomerManager();
    private Customer customer1;


    @Before
    public void setUp() {

        //dependency injection
        ((DefaultReportManager)reportManager).setCustomerManager(customerManager);

        //create some dummy customers
        customer1 = customerManager.addCustomer("Customer 1");
        Account checkingAccountCustomer1 = accountManager.openCheckingAccount(customer1,"Checking Account 1");
        Account savingsAccountCustomer1 = accountManager.openSavingsAccount(customer1,"Savings Account 1");
        Account maxiSavingsAccountCustomer1 = accountManager.openMaxiSavingsAccount(customer1,"Maxi Savings Account 1");

        accountManager.depositMoneyToAccount(checkingAccountCustomer1,new BigDecimal(100));
        accountManager.depositMoneyToAccount(savingsAccountCustomer1, new BigDecimal(4000));
        accountManager.withdrawMoneyFromAccount(savingsAccountCustomer1, new BigDecimal(200));
        accountManager.depositMoneyToAccount(maxiSavingsAccountCustomer1, new BigDecimal(100.55));

        Customer customer2 = customerManager.addCustomer("Customer 2");
        accountManager.openCheckingAccount(customer2,"Checking Account 2");

        customerManager.addCustomer("Customer 3");

    }

    @Test
    public void generateCustomersAccountsReport() {
        CustomersAccountsReport customersAccountsReport = reportManager.createCustomersAccountsReport();

        assertEquals(customerManager.getAllCustomers().size(),customersAccountsReport.getCustomers().size());
        for(Customer customer : customerManager.getAllCustomers()) {
            Customer customerOnReport = findCustomerOnReport(customer,customersAccountsReport);
            assertNotNull(customerOnReport);
            assertEquals(customer.getAccounts().size(),customerOnReport.getAccounts().size());
        }
    }

    @Test
    public void basicFormatOfCustomersAccountsReport() {
        CustomersAccountsReport customersAccountsReport = reportManager.createCustomersAccountsReport();
        String customersAccountsReportBasicFormat = reportFormatter.
                formatCustomersAccountsReport(customersAccountsReport);

        assertEquals("Customer Summary" + LINE_SEPARATOR +
                     "- Customer 1 (3 accounts)" + LINE_SEPARATOR +
                     "- Customer 2 (1 account)" + LINE_SEPARATOR +
                     "- Customer 3 (0 accounts)"
                , customersAccountsReportBasicFormat);

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
        String customerReportBasicFormat = reportFormatter.formatCustomerReport(customerReport);


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

    private Customer findCustomerOnReport(Customer customerToFind, CustomersAccountsReport customersAccountsReport) {

        for(Customer customer : customersAccountsReport.getCustomers()) {
            if(customer.getName().equals(customerToFind.getName())) {
                return customer;
            }
        }

        return null;
    }

}
