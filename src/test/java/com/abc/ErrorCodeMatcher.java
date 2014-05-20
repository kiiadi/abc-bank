package com.abc;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import org.junit.Ignore;

import com.abc.exceptions.GLError;

@Ignore
public class ErrorCodeMatcher extends BaseMatcher<Throwable> {

	private final int expectedErrorCode;

	public ErrorCodeMatcher(int expectedErrorCode) {
		this.expectedErrorCode = expectedErrorCode;
	}

	public boolean matches(Object o) {
		return ((GLError) o).getErrorCode() == expectedErrorCode;
	}

	public void describeTo(Description d) {
		d.appendText("Expected error code was" + expectedErrorCode);
	}
}
