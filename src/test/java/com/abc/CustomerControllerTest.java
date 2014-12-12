package com.abc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.controller.CustomerController;
import com.abc.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest {
	
	@Autowired
	final CustomerController controller = null;
	
	@Test
	public void get() {
		final Customer act1 = this.controller.add("Ed");
		try {
			final Customer act = this.controller.get(act1.getUid());
			assertNotNull(act);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
	
	@Test
	public void add() {
		final Customer act1 = this.controller.add("Ed");
		assertNotNull(act1);
	}
	
	@Test
	public void delete() {
		final Customer act1 = this.controller.add("Ed");
		try {
			this.controller.delete(act1.getUid());
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
	
	@Test
	public void getAll() {
		try {
			this.controller.add("Test Account");
			
			final Collection<Customer> acts = this.controller.getAll();
			
			assertTrue(acts.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
		
	}
	
	@Test
	public void contains() {
		try {
			final Customer act = this.controller.add("Ed");
			
			assertTrue(this.controller.contains(act.getUid()));
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
}
