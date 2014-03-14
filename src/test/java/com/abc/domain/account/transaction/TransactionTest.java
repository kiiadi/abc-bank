package com.abc.domain.account.transaction;

import org.junit.Before;

import com.abc.domain.sub.time.Clock;
import com.abc.domain.sub.time.TimePoint;

public class TransactionTest {

	Clock mockClock;
	
	@Before
	public void setUp() throws Exception {
		this.mockClock = new Clock() {
			@Override
			public TimePoint now() {
				return TimePoint.at(2014, 3, 11, 21, 30);
			}
		};
	}
}