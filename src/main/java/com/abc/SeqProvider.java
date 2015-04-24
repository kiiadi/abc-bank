package com.abc;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class SeqProvider {
	private static volatile SeqProvider instance = null;
	private static ReentrantLock instanceLock = new ReentrantLock();
	private AtomicLong customerIdSeq;

	private SeqProvider() {
		customerIdSeq = new AtomicLong(0);
	}

	public static SeqProvider getInstance() {
		if (instance == null)
			try {
				instanceLock.lock();
				if (instance == null) {
					instance = new SeqProvider();
				}
			} finally {
				instanceLock.unlock();
			}
		return instance;
	}
	
	public Long nextCustomerId() {
		return this.customerIdSeq.incrementAndGet();
	}
}
