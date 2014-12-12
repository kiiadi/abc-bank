package com.abc;

import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.controller.BankController;
import com.abc.model.Bank;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankControllerTest {
	
	@Autowired
	final BankController controller = null;
	
	@Test
	public void getBank() {
		Bank bank = null;
		try {
			bank = controller.getBank();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(bank);
	}
}
