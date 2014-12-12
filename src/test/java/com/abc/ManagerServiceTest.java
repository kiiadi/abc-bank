package com.abc;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.builder.AccountBuilder;
import com.abc.model.Customer;
import com.abc.model.Report;
import com.abc.service.CustomerService;
import com.abc.service.ManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ManagerServiceTest {
	@Autowired
	final ManagerService managerService = null;
	@Autowired
	final CustomerService service = null;
	
	
	public ManagerServiceTest() {
		super();
	}

	@Test
	public void customer() throws Exception {
		Customer customer = service.createCustomerAndOpenAccount("Ed Erie",
				"Checking Account", AccountBuilder.CHECKING);
		// create all three accounts
		service.openAcccount(customer, "Saving Account", AccountBuilder.SAVINGS);
		assertTrue(customer.getAccounts().size() == 2);
		service.openAcccount(customer, "Max Savings",
				AccountBuilder.MAXI_SAVINGS);
		assertTrue(customer.getAccounts().size() == 3);

	}

	@Test
	public void reportCustomerTest() {
		final Report report = managerService.getCustomerReport();
		System.out.println(managerService.getReportView(report));
	}
	
	@Test
	public void reportInterestTest() {
		final Report report = managerService.getInterestReport();
		System.out.println(managerService.getReportView(report));
	}

}
