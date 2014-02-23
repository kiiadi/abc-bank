package com.abc;

/**
 * A pseudo-unique integer ID generator.
 * As implemented currently, it is not multi-threaded.
 */
public class IdGenerator {
	private static IdGenerator instance = null;
	private static long currentId = 1;

	private IdGenerator() {
	}

	public static IdGenerator getInstance() {
		if (instance == null)
			instance = new IdGenerator();
		return instance;
	}

	public long getId() {
		return currentId++;
	}
}
